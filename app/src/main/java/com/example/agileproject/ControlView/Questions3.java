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
import android.widget.EditText;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.MultipleTextAnswer;
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question3, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        EditText editText=(EditText) view.findViewById(R.id.textInputSideEffects);
        Anger = view.findViewById(R.id.Anger);
        Medicine = view.findViewById(R.id.chipGroup2);
        SideEffectsYesOrNo = view.findViewById(R.id.chipGroup);
        SideEffects = view.findViewById(R.id.side_effects);
        OtherSideEffects = view.findViewById(R.id.textInputSideEffects);


        view.findViewById(R.id.next_button_questions3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_questions3_to_question4);
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
               //navController.navigate(R.id.action_questions3_to_complementaryQuestion3);

           }
       });

       view.findViewById(R.id.otherSideEffectsChip).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editText.setVisibility(View.VISIBLE);

           }
       });

       view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView11).setVisibility(View.GONE);
               view.findViewById(R.id.side_effects).setVisibility(View.GONE);
               view.findViewById(R.id.imageView3).setVisibility(View.GONE);
               view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
               view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);

           }
       });

       view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView11).setVisibility(View.GONE);
               view.findViewById(R.id.side_effects).setVisibility(View.GONE);
               view.findViewById(R.id.imageView3).setVisibility(View.GONE);
               view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
               view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);

           }
       });

       view.findViewById(R.id.otherSideEffectsChip).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editText.setVisibility(View.VISIBLE);
           }
       });

       view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView11).setVisibility(View.GONE);
               view.findViewById(R.id.side_effects).setVisibility(View.GONE);
               view.findViewById(R.id.imageView3).setVisibility(View.GONE);
               view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
               view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);
           }
       });


       Anger.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               NumberAnswer question8 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),8, LocalDate.now().toString());
               AddingToList(question8);
           }
       });

       Medicine.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               Boolean YesOrNo = getBooleanValue(selectedChip.getText().toString());
               BooleanAnswer question9 = new BooleanAnswer(YesOrNo,9,LocalDate.now().toString());
               AddingToList(question9);
           }
       });

       SideEffectsYesOrNo.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               Chip selectedChip = view.findViewById(group.getCheckedChipId());
               Boolean YesOrNo = getBooleanValue(selectedChip.getText().toString());
               BooleanAnswer question10 = new BooleanAnswer(YesOrNo,10,LocalDate.now().toString());
               AddingToList(question10);
               if(YesOrNo == false ){
                   RemoveComplementaryQuestion();
               }
           }
       });


       SideEffects.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onCheckedChanged(ChipGroup group, int checkedId) {
               List<Integer> ids = group.getCheckedChipIds();
               List<String> answers = new ArrayList<>() ;
                for(Integer id:ids){
                    Chip chip = group.findViewById(id);
                   String text = chip.getText().toString();
                   if(text.equals("Andra")){
                       text = OtherSideEffects.getText().toString();
                   }
                    answers.add(text);
                }
                question101 =  new MultipleTextAnswer(answers,10.1,LocalDate.now().toString());

                AddingToList(question101);
           }
       });


    }



    private void RemoveComplementaryQuestion(){
        if(QuizActivity.AnswerHolder.QuizAnswers.contains(  question101)){
            QuizActivity.AnswerHolder.QuizAnswers.remove(question101);
        }
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





}