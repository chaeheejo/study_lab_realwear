package com.studylab.study_lab_realwear;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.studylab.study_lab_realwear.datasource.CompletedCallback;
import com.studylab.study_lab_realwear.datasource.FirebaseDataSource;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;

public class FileService extends Service {

    private final IBinder binder = new LocalBinder();
    private File imageStorageDir;
    private Executor executor;
    private FirebaseDataSource firebaseDataSource;

    public FileService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        imageStorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void getImageDrawable(String filePath, FileServiceCallback<Result<Drawable>> callback) {
        executor.execute(() ->
        {
            File file = new File(imageStorageDir, filePath);
            if (file.exists()) {
                Drawable d = Drawable.createFromPath(file.getAbsolutePath());
                callback.onComplete(new Result.Success<Drawable>(d));
            } else {
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    firebaseDataSource.downloadFile(filePath, file, new CompletedCallback<Result>() {
                        @Override
                        public void onComplete(Result result) {
                            if (result instanceof Result.Success) {
                                Log.d("DEBUG", "FileService : getImageDrawable() : " + filePath + " was downloaded");
                                File toReturn = ((Result.Success<File>) result).getData();
                                callback.onComplete(new Result.Success<Drawable>(Drawable.createFromPath(toReturn.getAbsolutePath())));
                            } else {
                                Log.d("DEBUG", "FileService : getImageDrawable() : " + filePath + " failed to download");
                                Log.d("DEBUG", ((Result.Error) result).getError().getMessage());
                                file.delete();
                                callback.onComplete(result);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setFirebaseDataSource(FirebaseDataSource fds) {
        this.firebaseDataSource = fds;
    }

    public void setExecutor(Executor e) {
        executor = e;
    }

    public interface FileServiceCallback<T> {
        void onComplete(T result);
    }

    public class LocalBinder extends Binder {
        FileService getService() {
            return FileService.this;
        }
    }

}
