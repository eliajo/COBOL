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



public class Question1 extends Fragment {

    NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);



        // when user click on button chip9Ja it will show the complementary question which belong to this question

        view.findViewById(R.id.chip9Ja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_complementaryQuestion1);

            }
        });

        // when user click on button chip11a it will show comlementary question to this question

        view.findViewById(R.id.chip11Ja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_anxietyQuestionInQ1);

            }
        });

        // when user click on button chip13Ja it will show comlementary question to this question

        view.findViewById(R.id.chip13Ja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_delusionsQuestionInQ1);

            }
        });



            // switching fragment to fragment 2

        view.findViewById(R.id.next_button_q2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_questions2);
            }
        });


    }
}