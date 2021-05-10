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
import com.example.agileproject.R;

import com.example.agileproject.Utils.FileFormatter;

import com.example.agileproject.Utils.FileHandler;




import com.google.android.material.chip.ChipGroup;


/**
 * @author Pegah Amanzadeh
 */

// Fragment 4 in QuizActivity


public class Fragment4_in_QuizActivity extends Fragment {
    NavController navController;
    FileFormatter fileFormatter = new FileFormatter();
    FileHandler fileHandler = new FileHandler();

    ChipGroup ChipGroupAlcohol;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment4, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        EditText editText=(EditText) view.findViewById(R.id.textInputExercise);
        ChipGroupAlcohol = view.findViewById(R.id.chipGroup8);


                // Switching to fragment  doneQuestion

        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
            String allQuizAnswers = fileFormatter.format(QuizActivity.AnswerHolder.QuizAnswers);
            fileHandler.write(allQuizAnswers,getContext(),"Answers.txt");



            }
        });

        view.findViewById(R.id.chipYesExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
            }
        });








    }

}

