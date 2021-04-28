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

// Create Complementary question to question 2 in  fragment 1(Question1)

public class Complementaryquestion2_in_fragment1 extends Fragment {
    NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complementaryquestion1_fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);



        // user can se Complementary question for the q3 in Question1 when clicking on this button

        view.findViewById(R.id.chip13Ja).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_complementaryQuestion1_to_delusionsQuestionInQ1);
            }
        });

        // switching fragment from complementaryQuestion1 to Questions2

        view.findViewById(R.id.next_button_q2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_complementaryQuestion1_to_questions2);
            }
        });

    }

}