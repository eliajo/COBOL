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
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 */
public class Questions2 extends Fragment {

    NavController navController;
    ChipGroup SleepHoursNight;
    ChipGroup SleepHoursDay;
    ChipGroup SleptWell;
    RadioGroup Reasons;
   private  TextAnswer question71;



    public Questions2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         navController = Navigation.findNavController(view);
         SleepHoursNight = view.findViewById(R.id.sleeptHours);
         SleepHoursDay = view.findViewById(R.id.sleeptHoursDayTime);
         SleptWell = view.findViewById(R.id.chipGroup3);
         Reasons = view.findViewById(R.id.chipGroup);
         view.findViewById(R.id.next_button_questions2).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 navController.navigate(R.id.action_questions2_to_questions3);
             }
         });
         view.findViewById(R.id.chipNo).setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView6).setVisibility(View.GONE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.GONE);
                 view.findViewById(R.id.textView10).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.imageView).setVisibility(View.VISIBLE);

             }
         });
         view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView10).setVisibility(View.GONE);
                 view.findViewById(R.id.chipGroup).setVisibility(View.GONE);
                 view.findViewById(R.id.imageView).setVisibility(View.GONE);
                 view.findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);
             }
         });
         view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView10).setVisibility(View.GONE);
                 view.findViewById(R.id.chipGroup).setVisibility(View.GONE);
                 view.findViewById(R.id.imageView).setVisibility(View.GONE);
                 view.findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);

             }
         });

         SleepHoursNight.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onCheckedChanged(ChipGroup group, int checkedId) {
                 Chip selectedChip = view.findViewById(group.getCheckedChipId());
                 NumberAnswer question5 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),5, LocalDate.now().toString());
                AddingToList(question5);
             }
         });

         SleepHoursDay.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onCheckedChanged(ChipGroup group, int checkedId) {
                 Chip selectedChip = view.findViewById(group.getCheckedChipId());
                 NumberAnswer question6 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),6,LocalDate.now().toString());
                 AddingToList(question6);
             }
         });

         SleptWell.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onCheckedChanged(ChipGroup group, int checkedId) {
                 Chip selectedChip = view.findViewById(group.getCheckedChipId());
                 String answer = selectedChip.getText().toString();
                 Boolean YesOrNo = getBooleanValue(answer);
                 BooleanAnswer question7 = new BooleanAnswer( YesOrNo,7,LocalDate.now().toString());
                 AddingToList(question7);
                 if(YesOrNo == true){
                     RemoveComplementaryQuestion();
                 }

             }
         });
          Reasons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @RequiresApi(api = Build.VERSION_CODES.O)
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  RadioButton selectedButton = view.findViewById(group.getCheckedRadioButtonId());
                  question71 = new TextAnswer(selectedButton.getText().toString(),71,LocalDate.now().toString());
                 AddingToList(question71);
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


    private void RemoveComplementaryQuestion(){
        if(QuizActivity.AnswerHolder.QuizAnswers.contains(question71)){
            QuizActivity.AnswerHolder.QuizAnswers.remove(question71);
        }
    }

    private boolean getBooleanValue(String answer){
        Boolean YesOrNo = false;
        String Yes = "Ja ";
        if(answer.equals(Yes)){
            YesOrNo = true;
        }
      return YesOrNo;
    }


}