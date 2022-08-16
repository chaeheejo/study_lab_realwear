package com.studylab.study_lab_realwear;

import android.app.Application;


public class App extends Application {

    public static String getProblemImagePath() {
        return "problemImages/problem_" + "todayQuestion" + ".jpg";
    }

}
