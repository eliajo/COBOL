package com.example.agileproject.ControlView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

    }

    public static class AnswerHolder{

       static List<Answerable> QuizAnswers = new ArrayList<>();



    }



}