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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.MultipleTextAnswer;

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
 * @author Edenia
 */
public class Questions3 extends Fragment {

    NavController navController;

    ChipGroup Anger;
    ChipGroup Medicine;
    ChipGroup SideEffectsYesOrNo;
    ChipGroup SideEffects;
    EditText OtherSideEffects;

    MultipleTextAnswer question101;
    Chip other;
    Chip selectedChip;


    NumberAnswer question8;
    BooleanAnswer question9;
    BooleanAnswer question10;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question3, container, false);
        if (QuizActivity.AnswerHolder.isComplementaryQuestionForPageAnswered(3)){
            view.findViewById(R.id.textView2).setVisibility(View.GONE);
            view.findViewById(R.id.chipGroup).setVisibility(View.GONE);
            view.findViewById(R.id.textView11).setVisibility(View.VISIBLE);
            view.findViewById(R.id.Side_Effects).setVisibility(View.VISIBLE);
            view.findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
            view.findViewById(R.id.chipYesSideEffects).setSelected(true);
        }

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Anger = view.findViewById(R.id.Anger);
        Medicine = view.findViewById(R.id.chipGroup2);
        SideEffectsYesOrNo = view.findViewById(R.id.chipGroup);
        SideEffects = view.findViewById(R.id.Side_Effects);
        OtherSideEffects = view.findViewById(R.id.textInputSideEffects);
        other =  view.findViewById(R.id.otherSideEffectsChip);
        TextView errorText=(TextView)  view.findViewById(R.id.textView20);


        view.findViewById(R.id.next_button_questions3).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (selectedChip == view.findViewById(R.id.chipYesSideEffects)) {

                    List<Integer> ids = SideEffects.getCheckedChipIds();
                    List<String> answers = new ArrayList<>();
                    boolean otherAnswer = false;
                    for (Integer id : ids) {
                        Chip chip = view.findViewById(id);
                        String text = chip.getText().toString();
                        if (text.equals("Andra")) {
                            text = OtherSideEffects.getText().toString();
                            otherAnswer=true;
                        }
                        answers.add(text);
                    }

                    if(!answers.isEmpty()){

                        question101 = new MultipleTextAnswer(answers, 101, LocalDate.now().toString(),otherAnswer);
                        QuizActivity.AnswerHolder.AddingToList(question101);
                    }
                    else{
                        question101 = null;

                    }
                }

                if (selectedChip == view.findViewById(R.id.chipNoSideEffects)) {
                    if (!(question8 == null || question9 == null || question10 == null)) {


                        navController.navigate(R.id.action_questions3_to_question4);

                    } else {
                        errorText.setVisibility(View.VISIBLE);

                    }
                } else if(selectedChip == view.findViewById(R.id.chipYesSideEffects)){
                    if (!(question8 == null || question9 == null || question10 == null || question101 == null )){
                        navController.navigate(R.id.action_questions3_to_question4);
                    }else{
                        errorText.setVisibility(View.VISIBLE);
                    }

                } else{
                    errorText.setVisibility(View.VISIBLE);
                }

            }

        });


       view.findViewById(R.id.chipYesSideEffects).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView2).setVisibility(View.GONE);
               view.findViewById(R.id.chipGroup).setVisibility(View.GONE);
               view.findViewById(R.id.textView11).setVisibility(View.VISIBLE);
               view.findViewById(R.id.Side_Effects).setVisibility(View.VISIBLE);
               view.findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
              view.findViewById(R.id.chipYesSideEffects).setSelected(true);
              if(other.isChecked()){
                  OtherSideEffects.setVisibility(View.VISIBLE);
              }

           }
       });



       //check on master
       other.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(other.isChecked()){
                   OtherSideEffects.setVisibility(View.VISIBLE);
               }else if (!other.isChecked()){
                   OtherSideEffects.setVisibility(View.GONE);
               }

           }
       });



       view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView11).setVisibility(View.GONE);
               view.findViewById(R.id.Side_Effects).setVisibility(View.GONE);
               view.findViewById(R.id.imageView3).setVisibility(View.GONE);
               view.findViewById(R.id.textInputSideEffects).setVisibility(View.GONE);
               view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
               view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);

           }
       });



       Anger.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               if( selectedChip != null){
                   question8 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),8, LocalDate.now().toString());
                 QuizActivity.AnswerHolder.AddingToList(question8);
               }else{
                   question8 = null;
               }

           }
       });

       Medicine.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               if(selectedChip != null) {
                   Boolean YesOrNo = QuizActivity.AnswerHolder.getBooleanValue(selectedChip.getText().toString());
                    question9 = new BooleanAnswer(YesOrNo, 9, LocalDate.now().toString());
                  QuizActivity.AnswerHolder.AddingToList(question9);
               }else{
                   question9 = null;
               }
           }
       });

       SideEffectsYesOrNo.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               selectedChip = view.findViewById(group.getCheckedChipId());
               if( selectedChip != null) {
                   Boolean YesOrNo = QuizActivity.AnswerHolder.getBooleanValue(selectedChip.getText().toString());
                   question10 = new BooleanAnswer(YesOrNo, 10, LocalDate.now().toString());
                  QuizActivity.AnswerHolder.AddingToList(question10);
                   if (YesOrNo == false) {
                       RemoveComplementaryQuestion();
                       view.findViewById(R.id.chipYesSideEffects).setSelected(false);
                   }
               }
           }
       });




    }


    private void RemoveComplementaryQuestion() {
        if (QuizActivity.AnswerHolder.QuizAnswers.contains(question101)) {
            QuizActivity.AnswerHolder.QuizAnswers.remove(question101);
        }
    }



}


