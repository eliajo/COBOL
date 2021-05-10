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
import android.widget.CompoundButton;
import android.widget.TextView;


import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDateTime;

/**
 * @author Pegah Amanzadeh
 */

// Fragment 1 in QuizActivity


public class Fragment1_in_QuizActivity extends Fragment {


    NavController navController;
    Chip selectedChip;


    NumberAnswer question1;
    NumberAnswer question2;
    NumberAnswer question3;
    NumberAnswer question4;


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

        // switching to fragment 2 if the user have answered to all mandatory questions
        TextView ErrorText = (TextView) view.findViewById(R.id.errorText);

        view.findViewById(R.id.next_button_q2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(question1 == null || question2 == null || question3 == null || question4 == null)) {
                    navController.navigate(R.id.action_question1_to_questions2);
                } else {
                    //Toast.makeText(Fragment1_in_QuizActivity.this, R.string.error_label,Toast.LENGTH_LONG).show();
                    ErrorText.setVisibility(View.VISIBLE);

                }

            }
        });

        ChipGroup EnergyLevel = (ChipGroup) view.findViewById(R.id.EnergyLevel);
        EnergyLevel.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if (selectedChip == null) {

                } else
                    question1 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 1, LocalDateTime.now().toString());


            }
        });
        ChipGroup Hallucination = (ChipGroup) view.findViewById(R.id.chipGroup10);
        Hallucination.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if (selectedChip == null) {

                } else
                    question2 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 2, LocalDateTime.now().toString());

            }
        });
        ChipGroup Delusions = (ChipGroup) view.findViewById(R.id.chipGroup15);
        Delusions.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if (selectedChip == null) {

                } else
                    question3 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 3, LocalDateTime.now().toString());

            }
        });
        ChipGroup Anxiety = (ChipGroup) view.findViewById(R.id.chipGroup16);
        Anxiety.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if (selectedChip == null) {

                } else
                    question4 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 4, LocalDateTime.now().toString());


            }
        });
        Chip questionmark1 = (Chip) view.findViewById(R.id.chip1questionmark);
        questionmark1.setOnClickListener(new Chip.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();

            }
        });
        Chip questionmark2=(Chip) view.findViewById(R.id.chip2questionmark);
        questionmark2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2();
            }
        });
        Chip questionmark3=(Chip) view.findViewById(R.id.chip3questionmark);
        questionmark3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog3();
            }
        });
        Chip questionmark4=(Chip) view.findViewById(R.id.chip4questionmark);
        questionmark4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog4();
            }
        });

       


    }

    public void openDialog() {
        PopUpDialog d = new PopUpDialog();
        d.show(getFragmentManager(), "kdkdkjfj");

    }
    public void openDialog2(){
        PopUpDialog2 d2=new PopUpDialog2();
        d2.show(getFragmentManager(),"Diagol2");

    }
    public void openDialog3(){
        PopUpDialog3 d3=new PopUpDialog3();
        d3.show(getFragmentManager(),"Diagol3");

    }
    public void openDialog4(){
        PopUpDialog4 d4=new PopUpDialog4();
        d4.show(getFragmentManager(),"Diagol4");

    }


}






