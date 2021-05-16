package com.example.agileproject.ControlView;

/**
 * @author Edenia Isaac
 */

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDateTime;

/**
 * A simple {@link Fragment} subclass.

 */
public class Questions2 extends Fragment {

    NavController navController;
    NumberAnswer question5;
    NumberAnswer question6;
    BooleanAnswer question7;
    TextAnswer question7Complementary0;
    //NumberAnswer question7Complementary1;
    //NumberAnswer question7Complementary2;


    Chip selectedChip;
    RadioButton selectedRadioButton;

    public Questions2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);


        TextView ErrorText = (TextView) view.findViewById(R.id.textView19);
        view.findViewById(R.id.next_button_questions2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedChip == view.findViewById(R.id.chipNo)) {
                    if( !(question5==null || question6==null || question7 == null  || question7Complementary0==null)){
                        navController.navigate(R.id.action_questions2_to_questions3);
                    }

                }

                if  (selectedChip==view.findViewById(R.id.chip19)) {
                    if (!(question5 == null || question6 == null || question7 == null)) {

                        navController.navigate(R.id.action_questions2_to_questions3);

                    }
                }
                else {

                    ErrorText.setVisibility(View.VISIBLE);
                }
            }
        });

        view.findViewById(R.id.chipNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.textView6).setVisibility(View.GONE);
                view.findViewById(R.id.chipGroup3).setVisibility(View.GONE);
                view.findViewById(R.id.textView10).setVisibility(View.VISIBLE);
                view.findViewById(R.id.sideeffectsRadiobutton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.imageView).setVisibility(View.VISIBLE);

            }
        });
        view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.textView10).setVisibility(View.GONE);
                view.findViewById(R.id.sideeffectsRadiobutton).setVisibility(View.GONE);
                view.findViewById(R.id.imageView).setVisibility(View.GONE);
                view.findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                view.findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);
            }
        });

        ChipGroup sleepTime = (ChipGroup) view.findViewById(R.id.sleeptHours);
        sleepTime.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if(selectedChip==null){

                }
                else{
                    question5 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 5, LocalDateTime.now().toString());
                }

            }
        });
        ChipGroup sleepHourDay = (ChipGroup) view.findViewById(R.id.sleepHourDay);
        sleepHourDay.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if(selectedChip==null){

                }else {

                    question6 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 6, LocalDateTime.now().toString());
                }
            }
        });
        ChipGroup sleep = (ChipGroup) view.findViewById(R.id.chipGroup3);
        sleep.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {
                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if(selectedChip==null){

                }else {
                    question7 = new BooleanAnswer(Boolean.getBoolean(selectedChip.getText().toString()), 7, LocalDateTime.now().toString());
                }
            }
        });


        RadioGroup complementaryq = (RadioGroup) view.findViewById(R.id.sideeffectsRadiobutton);
        complementaryq.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(RadioGroup radioButtonGroup, int checkedId) {
                selectedRadioButton = view.findViewById(radioButtonGroup.getCheckedRadioButtonId());
                if(selectedChip==null){

                }else {
                    question7Complementary0 = new TextAnswer(String.valueOf(selectedRadioButton.getText().toString()), 8, LocalDateTime.now().toString());
                }
                
            }

        });


    }
}



