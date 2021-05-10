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
import java.time.LocalTime;

import java.util.List;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Pegah Amanzadeh
 */

// Fragment 1 in QuizActivity


public class Fragment1_in_QuizActivity extends Fragment {

    NavController navController;

    ChipGroup EnergyLevel;
    ChipGroup Hallucinations;
    ChipGroup Delusions;
    ChipGroup Anxiety;





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
        EnergyLevel = view.findViewById(R.id.EnergyLevel);
        Hallucinations = view.findViewById(R.id.chipGroup10);
        Delusions = view.findViewById(R.id.chipGroup15);
        Anxiety = view.findViewById(R.id.chipGroup16);



        // switching to fragment 2

        view.findViewById(R.id.next_button_q2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_questions2);
            }
        });






        EnergyLevel.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip selectedChip;
                selectedChip = view.findViewById(group.getCheckedChipId());
            NumberAnswer question1 = new NumberAnswer( Integer.valueOf(selectedChip.getText().toString()),1, LocalDate.now().toString());
             AddingToList(question1);
            }

        });



        Hallucinations.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip selectedChip;
                selectedChip = view.findViewById(group.getCheckedChipId());
              NumberAnswer  question2 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),2, LocalDate.now().toString());
                AddingToList(question2);
            }

        });


        Hallucinations.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip selectedChip;
                selectedChip = view.findViewById(group.getCheckedChipId());
              NumberAnswer  question2 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),2, LocalDate.now().toString());
              AddingToList(question2);
            }
        });


        Delusions.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip selectedChip;
                selectedChip = view.findViewById(group.getCheckedChipId());
              NumberAnswer  question3 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),3,LocalDate.now().toString());
                AddingToList(question3);

            }
        });

        Anxiety.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip selectedChip;
                selectedChip = view.findViewById(group.getCheckedChipId());

             NumberAnswer question4 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),4,LocalDate.now().toString());
                AddingToList(question4);
            }

        });


    }



    private void AddingToList(Answerable answerable) {
        List<Answerable> answerables = new ArrayList<>(QuizActivity.AnswerHolder.QuizAnswers);

        for (Answerable a : answerables) {
            if (a.getQuestionId() == answerable.getQuestionId()) {
                QuizActivity.AnswerHolder.QuizAnswers.remove(a);
            }


        }
        QuizActivity.AnswerHolder.QuizAnswers.add(answerable);

    }


}