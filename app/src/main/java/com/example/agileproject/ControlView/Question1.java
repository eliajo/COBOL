package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.R;
/**
 * @author Pegah Amanzadeh
 */

// Fragment 1 in QuizActivity


public class Question1 extends Fragment {

    NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question1, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);



        // when user click on button chip9Ja it will show the complementary question to question 2

        view.findViewById(R.id.chip9Ja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_complementaryQuestion1);

            }
        });

        // when user click on button chip11a it will show comlementary to question4

        view.findViewById(R.id.chip11Ja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_anxietyQuestionInQ1);

            }
        });

        // when user click on button chip13Ja it will show comlementary to question 3

        view.findViewById(R.id.chip13Ja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_delusionsQuestionInQ1);

            }
        });



            // switching to fragment 2

        view.findViewById(R.id.next_button_q2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_questions2);
            }
        });


    }
}