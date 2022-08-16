package com.studylab.study_lab_realwear.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.studylab.study_lab_realwear.R;
import com.studylab.study_lab_realwear.databinding.FragmentWrongBinding;

public class WrongFragment extends Fragment {
    private FragmentWrongBinding binding;
    private Button bt_home;

    public WrongFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWrongBinding.inflate(inflater, container, false);
        bt_home = binding.wrongBtHome;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //region Listener
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(WrongFragment.this).navigate(R.id.action_wrongFragment_to_homeFragment);
            }
        });
        //endregion
    }
}