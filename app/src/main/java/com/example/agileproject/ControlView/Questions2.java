package com.example.agileproject.ControlView;

/**
 * @author Edenia Isaac
 */

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.agileproject.Model.Answerable;
import android.widget.TextView;
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


/**
 * A simple {@link Fragment} subclass.
 @author Edenia Isaac, Pegah Amanzadeh
 */
public class Questions2 extends Fragment {

    NavController navController;

    ChipGroup SleepHoursNight;
    ChipGroup SleepHoursDay;
    ChipGroup SleptWell;
    RadioGroup Reasons;
    TextAnswer question71;
    NumberAnswer question5;
    NumberAnswer question6;
    BooleanAnswer question7;
    Chip selectedChip;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questions2, container, false);
        if (QuizActivity.AnswerHolder.isComplementaryQuestionForPageAnswered(2)){
            view.findViewById(R.id.textView6).setVisibility(View.GONE);
            view.findViewById(R.id.chipGroup3).setVisibility(View.GONE);
            view.findViewById(R.id.textView10).setVisibility(View.VISIBLE);
            view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);
            view.findViewById(R.id.imageView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.chipNo).setSelected(true);

        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         navController = Navigation.findNavController(view);
         SleepHoursNight = view.findViewById(R.id.sleeptHours);
         SleepHoursDay = view.findViewById(R.id.sleepHourDay);
         SleptWell = view.findViewById(R.id.chipGroup3);
         Reasons = view.findViewById(R.id.sideeffectsRadiobutton);



         view.findViewById(R.id.chipYes).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.chipNo).setSelected(false);
             }
         });

         view.findViewById(R.id.chipNo).setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView6).setVisibility(View.GONE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.GONE);
                 view.findViewById(R.id.textView10).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.sideeffectsRadiobutton).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.imageView).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipNo).setSelected(true);

             }
         });

         view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView10).setVisibility(View.GONE);
                 view.findViewById(R.id.sideeffectsRadiobutton).setVisibility(View.GONE);
                 view.findViewById(R.id.imageView).setVisibility(View.GONE);
                 view.findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);
             }
         });




        TextView ErrorText = (TextView) view.findViewById(R.id.textView19);
        view.findViewById(R.id.next_button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedChip == view.findViewById(R.id.chipNo)) {
                    if( !(question5==null || question6==null || question7 == null  || question71==null)){
                        navController.navigate(R.id.action_questions2_to_questions3);
                    }else{

                        ErrorText.setVisibility(View.VISIBLE);
                    }

                }

                else if (selectedChip==view.findViewById(R.id.chipYes)) {
                    if (!(question5 == null || question6 == null || question7 == null)) {
                        navController.navigate(R.id.action_questions2_to_questions3);
                    }else{

                        ErrorText.setVisibility(View.VISIBLE);
                    }
                }
                else {

                    ErrorText.setVisibility(View.VISIBLE);
                }
            }
        });






         SleepHoursNight.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip  selectedChip = view.findViewById(group.getCheckedChipId());
                 if(selectedChip != null){
                      question5 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),5, LocalDate.now().toString());
                    QuizActivity.AnswerHolder.AddingToList(question5);
                 }else{
                     question5 = null;
                 }

             }
         });

         SleepHoursDay.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
                 if(selectedChip != null) {
                      question6 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 6, LocalDate.now().toString());
                    QuizActivity.AnswerHolder.AddingToList(question6);
                 }else{
                     question6 = null;
                 }
             }
         });

         SleptWell.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onCheckedChanged(ChipGroup group, int checkedId) {
                  selectedChip = view.findViewById(group.getCheckedChipId());
                 if (selectedChip != null) {
                 String answer = selectedChip.getText().toString();
                 Boolean YesOrNo = QuizActivity.AnswerHolder.getBooleanValue(answer);
                 question7 = new BooleanAnswer(YesOrNo, 7, LocalDate.now().toString());
                QuizActivity.AnswerHolder.AddingToList(question7);
                 if (YesOrNo == true) {
                     RemoveComplementaryQuestion();
                 }
               }
             }
         });
          Reasons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @RequiresApi(api = Build.VERSION_CODES.O)
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  RadioButton selectedButton = view.findViewById(group.getCheckedRadioButtonId());
                  question71 = new TextAnswer(selectedButton.getText().toString(),71,LocalDate.now().toString());
                QuizActivity.AnswerHolder.AddingToList(question71);
              }
          });

    }

    private void RemoveComplementaryQuestion(){
        if(QuizActivity.AnswerHolder.QuizAnswers.contains(question71)){
            QuizActivity.AnswerHolder.QuizAnswers.remove(question71);
        }
    }




}

