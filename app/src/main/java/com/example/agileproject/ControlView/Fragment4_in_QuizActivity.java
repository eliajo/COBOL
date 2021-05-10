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
 * @author Pegah Amanzadeh
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

    ChipGroup ChipGroupAlcohol;




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
                    AddingToList(question131);
                }
                String text =   Events.getText().toString();
                if(!text.equals("")){
                    TextAnswer question14 = new TextAnswer(text,14,LocalDate.now().toString());
                    AddingToList(question14);
                }
            String allQuizAnswers = fileFormatter.format(QuizActivity.AnswerHolder.QuizAnswers);
            fileHandler.write(allQuizAnswers,getContext(),"Answers.txt");



            }
        });

        view.findViewById(R.id.chipYesExercise).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Exercise.setVisibility(View.VISIBLE);
             /*  question131 = new TextAnswer(Exercise.getText().toString(),131,LocalDate.now().toString());
                AddingToList(question131);*/
            }
        });

        view.findViewById(R.id.chipNoExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise.setVisibility(View.GONE);
                RemoveComplementaryQuestion();


            }
        });

       chipGroupAlcohol.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               Boolean YesOrNo = getBooleanValue(selectedChip.getText().toString());
               BooleanAnswer question11 = new BooleanAnswer(YesOrNo,11, LocalDate.now().toString());
               AddingToList(question11);
           }
       });


       chipGroupObsession.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               Boolean YesOrNo = getBooleanValue(selectedChip.getText().toString());
               BooleanAnswer question12 = new BooleanAnswer(YesOrNo,12, LocalDate.now().toString());
               AddingToList(question12);
           }
       });

       chipGroupExercise.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               Boolean YesOrNo =  getBooleanValue(selectedChip.getText().toString());
               BooleanAnswer question13 = new BooleanAnswer(YesOrNo,13,LocalDate.now().toString());
               AddingToList(question13);
           }
       });



      /*Events.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @RequiresApi(api = Build.VERSION_CODES.O)
          @Override
          public void onFocusChange(View v, boolean hasFocus) {
              if (!hasFocus) {
              String text = Events.getText().toString();
              TextAnswer question14 = new TextAnswer(text, 14, LocalDate.now().toString());
              AddingToList(question14);
               }
          }
      });*/

      /*  Exercise.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus ){
                    question131 = new TextAnswer(Exercise.getText().toString(),131,LocalDate.now().toString());
                    AddingToList(question131);
                }
            }
        }); */

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

    private boolean getBooleanValue(String answer){
        Boolean YesOrNo = false;
        String Yes = "Ja ";
        if(answer.equals(Yes)){
            YesOrNo = true;
        }
        return YesOrNo;
    }

    private void RemoveComplementaryQuestion(){
        if(QuizActivity.AnswerHolder.QuizAnswers.contains(  question131)){
            QuizActivity.AnswerHolder.QuizAnswers.remove(question131);
        }
    }


}

