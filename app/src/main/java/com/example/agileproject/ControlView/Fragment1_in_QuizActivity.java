package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pegah Amanzadeh
 */

// Fragment 1 in QuizActivity


public class Fragment1_in_QuizActivity extends Fragment {

    NavController navController;
    Chip selectedChip;
    List<Answerable> QuizAnswers = new ArrayList<>();

    NumberAnswer question1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        ChipGroup chipGroup2 = (ChipGroup) view.findViewById(R.id.chipGroup2);


        // switching to fragment 2

        view.findViewById(R.id.next_button_q2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_question1_to_questions2);
            }
        });
        chipGroup2.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
             selectedChip = view.findViewById(chipGroup.getCheckedChipId());
               question1=new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()),1, LocalDateTime.now().toString());

               if(QuizAnswers.contains(question1)){ // vi kollar om svaret redan finns i listan,då tar bort
                                                    // den gamla och lägg till nya svaret
                  QuizAnswers.remove(question1);
                  QuizAnswers.add(question1);


               }

            }
        });

    }
}
