package com.studylab.study_lab_realwear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.studylab.study_lab_realwear.datasource.FirebaseDataSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    FileService fileService;
    ProblemRepository problemRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FirebaseDataSource ds = new FirebaseDataSource();
        problemRepository = ProblemRepository.getInstance();
        problemRepository.setDataSource(ds);
        problemRepository.setExecutor(executorService);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                FileService.LocalBinder binder = (FileService.LocalBinder) service;
                fileService = binder.getService();
                fileService.setFirebaseDataSource(ds);
                problemRepository.setFileService(fileService);
                fileService.setExecutor(executorService);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent = new Intent(this,FileService.class);
        startService(intent);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);


        setContentView(R.layout.activity_main);
    }
}