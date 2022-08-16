package com.studylab.study_lab_realwear;

import android.graphics.drawable.Drawable;

import com.studylab.study_lab_realwear.datasource.CompletedCallback;
import com.studylab.study_lab_realwear.datasource.DataSource;

import java.util.concurrent.Executor;

public class ProblemRepository {
    private static volatile ProblemRepository INSTANCE = new ProblemRepository();
    private DataSource dataSource;
    private FileService fileService;
    protected Executor executor;

    public static ProblemRepository getInstance(){return INSTANCE;}

    public void loadProblemImage(CompletedCallback<Result> callback){
        fileService.getImageDrawable(App.getProblemImagePath(), new FileService.FileServiceCallback<Result<Drawable>>() {
            @Override
            public void onComplete(Result<Drawable> result) {

                callback.onComplete(result);
            }
        });
    }

    public void getAnswer(CompletedCallback<Result<String>> callback){
        dataSource.getAnswer(callback::onComplete);
    }

    public void setExecutor(Executor exec) {
        this.executor = exec;
    }

    public void setFileService(FileService fs) {
        this.fileService = fs;
    }

    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }



}
