package com.studylab.study_lab_realwear.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.studylab.study_lab_realwear.R;
import com.studylab.study_lab_realwear.databinding.FragmentProblemBinding;
import com.studylab.study_lab_realwear.viewmodel.ProblemViewModel;

public class ProblemFragment extends Fragment {
    private ProblemViewModel problemViewModel;
    private FragmentProblemBinding binding;
    private ImageView iv_problem;
    private Button bt_one;
    private Button bt_two;
    private Button bt_three;
    private Button bt_four;
    private Button bt_five;

    public ProblemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        problemViewModel = new ViewModelProvider(this).get(ProblemViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProblemBinding.inflate(inflater, container, false);
        iv_problem = binding.problemIvProblem;
        bt_one = binding.problemBtOne;
        bt_two = binding.problemBtTwo;
        bt_three = binding.problemBtThree;
        bt_four = binding.problemBtFour;
        bt_five = binding.problemBtFive;
        init();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //region Observer
        problemViewModel.isImageLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoaded) {
                if(isLoaded){
                    iv_problem.setImageDrawable(problemViewModel.getProblemImage());
                }
            }
        });
        //endregion

        //region Listener
        bt_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(problemViewModel.sendAnswer("1")){
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_correctFragment2);
                }else{
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_wrongFragment);
                }
            }
        });
        bt_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(problemViewModel.sendAnswer("2")){
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_correctFragment2);
                }else{
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_wrongFragment);
                }
            }
        });
        bt_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(problemViewModel.sendAnswer("3")){
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_correctFragment2);
                }else{
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_wrongFragment);
                }
            }
        });
        bt_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(problemViewModel.sendAnswer("4")){
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_correctFragment2);
                }else{
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_wrongFragment);
                }
            }
        });
        bt_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(problemViewModel.sendAnswer("5")){
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_correctFragment2);
                }else{
                    NavHostFragment.findNavController(ProblemFragment.this).navigate(R.id.action_problemFragment_to_wrongFragment);
                }
            }
        });
        //endregion
    }

    private void init(){
        problemViewModel.loadProblemImage();
        problemViewModel.loadAnswer();
    }
}