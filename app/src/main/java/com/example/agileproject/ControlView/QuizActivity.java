package com.example.agileproject.ControlView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.Storable;
import com.example.agileproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edenia Isaac
 */
public class QuizActivity extends AppCompatActivity {
    Fragment1_in_QuizActivity fragment1;

    @Override
    public void onBackPressed() {

        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        if(fragment.getId() == 0x7f0901aa){
            findViewById(R.id.textView10).setVisibility(View.GONE);
            findViewById(R.id.chipGroup).setVisibility(View.GONE);
            findViewById(R.id.imageView).setVisibility(View.GONE);
            findViewById(R.id.textView6).setVisibility(View.VISIBLE);
            findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);
        }else{
            super.onBackPressed();
        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


    }



    public static class AnswerHolder{

       static List<Answerable> QuizAnswers = new ArrayList<>();

        public static void AddingToList(Answerable answerable) {
            List<Answerable> answerables = new ArrayList<>(QuizActivity.AnswerHolder.QuizAnswers);

            for (Answerable a : answerables) {
                if (a.getQuestionId() == answerable.getQuestionId()) {
                    QuizActivity.AnswerHolder.QuizAnswers.remove(a);
                }
            }
            QuizActivity.AnswerHolder.QuizAnswers.add(answerable);
        }


    public static boolean getBooleanValue(String answer){
            Boolean YesOrNo = false;
            String Yes = "Ja ";
            if(answer.equals(Yes)){
                YesOrNo = true;
            }
            return YesOrNo;
        }


    }


}