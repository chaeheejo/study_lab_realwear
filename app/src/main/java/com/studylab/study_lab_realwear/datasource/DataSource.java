package com.studylab.study_lab_realwear.datasource;

import com.studylab.study_lab_realwear.Result;

public interface DataSource {
    void getAnswer(CompletedCallback<Result<String>> callback);
}
