package com.example.agileproject.ControlView;

import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;



import android.preference.EditTextPreference;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.agileproject.Model.Answerable;

import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.internal.ThemeEnforcement;

import org.w3c.dom.Text;

import java.time.LocalDateTime;

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
  private  int counter =0;


    BooleanAnswer question11;
    BooleanAnswer question12;
    BooleanAnswer question13;
    TextAnswer question14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment4, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

       Exercise = view.findViewById(R.id.textInputExercise);
        Events = view.findViewById(R.id.textInputEditText);
        chipGroupAlcohol = view.findViewById(R.id.chipGroup8);
        chipGroupObsession = view.findViewById(R.id.chipGroup7);
        chipGroupExercise = view.findViewById(R.id.chipGroup11);

        TextView error = (TextView) view.findViewById(R.id.textView21);


        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
           
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
               counter ++;
                String ExerciseText  = Exercise.getText().toString();
                if(!ExerciseText.equals("")){
                    question131 = new TextAnswer(ExerciseText,131,LocalDate.now().toString());
                   QuizActivity.AnswerHolder.AddingToList(question131);
                }
                String text = Events.getText().toString();
                if(!text.equals("")){
                     question14 = new TextAnswer(text,14,LocalDate.now().toString());
                 QuizActivity.AnswerHolder.AddingToList(question14);
                }

                 if(counter == 1){
                     if ((question11 == null || question12 == null || question13 == null || question14 == null)) {
                         error.setVisibility(View.VISIBLE);

                     } else {
                        // navController.navigate(R.id.action_question4_to_doneQuestions);
                     }
                 } else{
                    // navController.navigate(R.id.action_question4_to_doneQuestions);
                 }
                String allQuizAnswers = fileFormatter.format(QuizActivity.AnswerHolder.QuizAnswers);
                fileHandler.write(allQuizAnswers,getContext(),"Answers.txt");

                changeActivity();
            }
        });

        chipGroupAlcohol.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               if(selectedChip != null) {
                   Boolean YesOrNo = QuizActivity.AnswerHolder.getBooleanValue(selectedChip.getText().toString());
                   question11 = new BooleanAnswer(YesOrNo, 11, LocalDate.now().toString());
                 QuizActivity.AnswerHolder. AddingToList(question11);
               }else{
                   RemoveComplementaryQuestion(question11);
                   question11 = null;
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
                    question12 = new BooleanAnswer(YesOrNo, 12, LocalDate.now().toString());
                 QuizActivity.AnswerHolder.AddingToList(question12);

               }else{
                   RemoveComplementaryQuestion(question12);
                   question12 = null;
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
                   question13 = new BooleanAnswer(YesOrNo, 13, LocalDate.now().toString());
                   QuizActivity.AnswerHolder.AddingToList(question13);
                   if(YesOrNo){
                       Exercise.setVisibility(View.VISIBLE);
                   }else{
                       Exercise.setVisibility(View.GONE);
                       Exercise.setText("");

                   }
               }else{
                   RemoveComplementaryQuestion(question13);
                   question13 = null;
               }
           }
       });
    }

    private void RemoveComplementaryQuestion( Answerable question) {
        if (QuizActivity.AnswerHolder.QuizAnswers.contains(question)) {
            QuizActivity.AnswerHolder.QuizAnswers.remove(question);
        }
    }

    private void changeActivity(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}


