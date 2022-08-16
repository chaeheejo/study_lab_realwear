package com.studylab.study_lab_realwear.viewmodel;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.studylab.study_lab_realwear.Result;
import com.studylab.study_lab_realwear.ProblemRepository;

public class ProblemViewModel extends ViewModel {
    private ProblemRepository problemRepository = ProblemRepository.getInstance();
    private MutableLiveData<Boolean> imageLoaded = new MutableLiveData<>(false);
    private Drawable problemImage;
    private String answer;

    public void loadProblemImage(){
        problemRepository.loadProblemImage(result -> {
           if(result instanceof Result.Success){
               problemImage = ((Result.Success<Drawable>)result).getData();
               imageLoaded.postValue(true);
           } else{
               imageLoaded.postValue(false);
           }
        });
    }

    public void loadAnswer(){
        problemRepository.getAnswer(result -> {
            if(result instanceof Result.Success){
                answer = ((Result.Success<String>)result).getData();
            }
        });
    }

    public boolean sendAnswer(String userAnswer){
        if(userAnswer.equals(answer)){
            return true;
        }else{
            return false;
        }
    }

    public Drawable getProblemImage(){
        return problemImage;
    }

    public LiveData<Boolean> isImageLoaded(){return imageLoaded;}
}
