package com.example.agileproject.ControlView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBackPressed() {

        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        if(fragment.getClass().equals(Questions3.class) && findViewById(R.id.chipYesSideEffects).isSelected()){
            findViewById(R.id.textView11).setVisibility(View.GONE);
            findViewById(R.id.Side_Effects).setVisibility(View.GONE);
            findViewById(R.id.imageView3).setVisibility(View.GONE);
            findViewById(R.id.textInputSideEffects).setVisibility(View.GONE);
            findViewById(R.id.textView2).setVisibility(View.VISIBLE);
            findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);
        } else if(fragment.getClass().equals(Questions2.class) && findViewById(R.id.chipNo).isSelected()){
            findViewById(R.id.textView10).setVisibility(View.GONE);
            findViewById(R.id.chipGroup).setVisibility(View.GONE);
            findViewById(R.id.imageView).setVisibility(View.GONE);
            findViewById(R.id.textView6).setVisibility(View.VISIBLE);
            findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);

        }
        else{
            super.onBackPressed();
        }

        //findViewById(R.id.chipNo).isSelected(
       // findViewById(R.id.chipYesSideEffects).isSelected()


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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isComplementaryQuestionForPageAnswered(int pageNumber){
            //Checks if there is an object in list with question id equal to 7 or 10
            if (pageNumber==2){
                return QuizAnswers.stream().anyMatch(o -> o.getQuestionId()==7);
            }
            else if (pageNumber==3){
                return QuizAnswers.stream().anyMatch(o -> o.getQuestionId()==10);
            }
            return false;
    }



    }


}