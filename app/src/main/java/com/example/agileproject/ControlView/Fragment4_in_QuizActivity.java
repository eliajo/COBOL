package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.R;

import com.example.agileproject.Utils.FileFormatter;

import com.example.agileproject.Utils.FileHandler;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;




import com.google.android.material.chip.ChipGroup;


/**
 * @author Pegah Amanzadeh, Edenia Isaac
 */

// Fragment 4 in QuizActivity


public class Fragment4_in_QuizActivity extends Fragment {
    NavController navController;
    FileFormatter fileFormatter = new FileFormatter();
    FileHandler fileHandler = new FileHandler();
    ChipGroup chipGroupAlcohol;
    ChipGroup chipGroupObsession;
    ChipGroup chipGroupExercise;
    EditText Events;
    EditText Exercise;
    TextAnswer question131;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment4, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
       Exercise = view.findViewById(R.id.textInputExercise);
        Events = view.findViewById(R.id.textInputEditText);
        chipGroupAlcohol = view.findViewById(R.id.chipGroup8);
        chipGroupObsession = view.findViewById(R.id.chipGroup7);
        chipGroupExercise = view.findViewById(R.id.chipGroup11);

                // Switching to fragment  doneQuestion

        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String ExerciseText  = Exercise.getText().toString();
                if(!ExerciseText.equals("")){
                    question131 = new TextAnswer(ExerciseText,131,LocalDate.now().toString());
                   QuizActivity.AnswerHolder.AddingToList(question131);
                }
                String text =   Events.getText().toString();
                if(!text.equals("")){
                    TextAnswer question14 = new TextAnswer(text,14,LocalDate.now().toString());
                 QuizActivity.AnswerHolder.AddingToList(question14);
                }
            String allQuizAnswers = fileFormatter.format(QuizActivity.AnswerHolder.QuizAnswers);
            fileHandler.write(allQuizAnswers,getContext(),"Answers.txt");

            }
        });



       chipGroupAlcohol.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               if(selectedChip != null) {
                   Boolean YesOrNo = QuizActivity.AnswerHolder.getBooleanValue(selectedChip.getText().toString());
                   BooleanAnswer question11 = new BooleanAnswer(YesOrNo, 11, LocalDate.now().toString());
                 QuizActivity.AnswerHolder. AddingToList(question11);
               }
           }
       });


       chipGroupObsession.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               if(selectedChip != null) {
                   Boolean YesOrNo = QuizActivity.AnswerHolder.getBooleanValue(selectedChip.getText().toString());
                   BooleanAnswer question12 = new BooleanAnswer(YesOrNo, 12, LocalDate.now().toString());
                 QuizActivity.AnswerHolder.AddingToList(question12);

               }
           }
       });

       chipGroupExercise.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               if(selectedChip != null) {
                   Boolean YesOrNo = QuizActivity.AnswerHolder.getBooleanValue(selectedChip.getText().toString());
                   BooleanAnswer question13 = new BooleanAnswer(YesOrNo, 13, LocalDate.now().toString());
                   QuizActivity.AnswerHolder.AddingToList(question13);
                   if(YesOrNo){
                       Exercise.setVisibility(View.VISIBLE);
                   }else{
                       Exercise.setVisibility(View.GONE);
                       Exercise.setText("");

                   }
               }
           }
       });

    }



}

