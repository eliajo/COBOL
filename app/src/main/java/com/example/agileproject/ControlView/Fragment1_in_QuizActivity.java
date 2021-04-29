package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDate;

/**
 * @author Pegah Amanzadeh
 */

// Fragment 1 in QuizActivity


public class Fragment1_in_QuizActivity extends Fragment {

    NavController navController;
    ChipGroup EnerigiLevel;
    Chip selectedChip;
    private NumberAnswer question1;
    private NumberAnswer question2;
    private NumberAnswer question3;
    private NumberAnswer question4;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        EnerigiLevel = view.findViewById(R.id.chipGroupEnergiLevel);

            // switching to fragment 2

        view.findViewById(R.id.next_button_q2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_questions2);
            }
        });

        EnerigiLevel.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                selectedChip = view.findViewById(group.getCheckedChipId());
             question1 = new NumberAnswer( Integer.valueOf(selectedChip.getText().toString()),1, LocalDate.now().toString());

            }
        });
    }
}