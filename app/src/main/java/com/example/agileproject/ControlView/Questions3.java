package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
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
 * @author Edenia
 */
public class Questions3 extends Fragment {

    NavController navController;
    Chip selectedChip;

    NumberAnswer question8;
    BooleanAnswer question9;
    BooleanAnswer question10;
    


    public Questions3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question3, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        EditText editText=(EditText) view.findViewById(R.id.textInputSideEffects);
        TextView errorText=(TextView)  view.findViewById(R.id.textView20);

        view.findViewById(R.id.next_button_questions3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(selectedChip==view.findViewById(R.id.chipYesSideEffects)) {
                    //if (!(question8 == null || question9 == null || question10 == null || question10YesComplementary == null)) {

                        //navController.navigate(R.id.action_questions3_to_question4);
                   // }
                //}
                //if(selectedChip==view.findViewById(R.id.otherSideEffectsChip)){
                    //if(!(question8==null ||  question9==null ||  question10==null || question10YesComplementary==null || question10EditText==null)){

                        //navController.navigate(R.id.action_questions3_to_question4);
                    //}
               // }
                //if(selectedChip==view.findViewById(R.id.chip2)) {
                    if (!(question8 == null || question9 == null || question10 == null)) {

                        navController.navigate(R.id.action_questions3_to_question4);
                   //}
               }
                else{
                    errorText.setVisibility(View.VISIBLE);

                }
        }
        });

       view.findViewById(R.id.chipYesSideEffects).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView2).setVisibility(View.GONE);
               view.findViewById(R.id.sideeffectsRadiobutton).setVisibility(View.GONE);
               view.findViewById(R.id.textView11).setVisibility(View.VISIBLE);
               view.findViewById(R.id.side_effects).setVisibility(View.VISIBLE);
               view.findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
               //navController.navigate(R.id.action_questions3_to_complementaryQuestion3);
           }
       });

       view.findViewById(R.id.otherSideEffectsChip).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editText.setVisibility(View.VISIBLE);

           }
       });

       view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView11).setVisibility(View.GONE);
               view.findViewById(R.id.side_effects).setVisibility(View.GONE);
               view.findViewById(R.id.imageView3).setVisibility(View.GONE);
               editText.setVisibility(View.GONE);
               view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
               view.findViewById(R.id.sideeffectsRadiobutton).setVisibility(View.VISIBLE);
           }
       });

        ChipGroup anger = (ChipGroup) view.findViewById(R.id.sleepHourDay);
        anger.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int checkedId) {

                selectedChip = view.findViewById(chipGroup.getCheckedChipId());
                if(selectedChip==null){

                }else {
                    question8 = new NumberAnswer(Integer.valueOf(selectedChip.getText().toString()), 9, LocalDateTime.now().toString());
                }
            }
        });

        ChipGroup takingMedecin=(ChipGroup) view.findViewById(R.id.EnergyLevel);
        takingMedecin.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                selectedChip=view.findViewById(group.getCheckedChipId());
                if(selectedChip==null){

                }else {

                    question9 = new BooleanAnswer(Boolean.getBoolean(selectedChip.getText().toString()), 10, LocalDateTime.now().toString());
                }

            }
        });
        ChipGroup SideEffects=(ChipGroup) view.findViewById(R.id.sideeffectsRadiobutton);
        SideEffects.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                selectedChip=view.findViewById(group.getCheckedChipId());
                if(selectedChip==null){

                }else {
                    question10 = new BooleanAnswer(Boolean.getBoolean(selectedChip.getText().toString()), 11, LocalDateTime.now().toString());
                }

            }
        });

        //question10YesComplementary is null!!!!!!!!!!!!!!

        /*ChipGroup sideEffectsComplementary=(ChipGroup) view.findViewById(R.id.side_effects);
        sideEffectsComplementary.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                selectedChip = view.findViewById(group.getCheckedChipId());
                if(selectedChip==null){

                }else {
                    question10YesComplementary = new TextAnswer(String.valueOf(selectedChip.getText().toString()), 12, LocalDateTime.now().toString());
                }
            }
        });

         */



        /*editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                question10EditText=new TextAnswer(String.valueOf(s),13,LocalDateTime.now().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

         */

    }

}

