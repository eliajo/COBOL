package com.example.agileproject.ControlView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.internal.ThemeEnforcement;

import org.w3c.dom.Text;

import java.time.LocalDateTime;

/**
 * @author Pegah Amanzadeh
 */

// Fragment 4 in QuizActivity


public class Fragment4_in_QuizActivity extends Fragment {
    NavController navController;
    BooleanAnswer question11;
    BooleanAnswer question12;
    BooleanAnswer question13;
    TextAnswer question14;
    Chip selectedChip;
    EditText text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment4, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        EditText editText = (EditText) view.findViewById(R.id.textInputExercise);
        TextView error = (TextView) view.findViewById(R.id.textView21);


        // Switching to fragment  doneQuestion

        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((question11 == null || question12 == null || question13 == null || question14 == null)) {

                    error.setVisibility(View.VISIBLE);
                } else {
                    navController.navigate(R.id.action_question4_to_doneQuestions);
                }
                view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navController.navigate(R.id.action_question4_to_doneQuestions);

                    }
                });


            }
        });

        view.findViewById(R.id.chipYesExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
            }
        });

        ChipGroup alcohol = (ChipGroup) view.findViewById(R.id.chipGroup8);
        alcohol.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                selectedChip = view.findViewById(group.getCheckedChipId());
                question11 = new BooleanAnswer(Boolean.getBoolean(selectedChip.getText().toString()), 14, LocalDateTime.now().toString());


            }
        });
        ChipGroup obsessions = (ChipGroup) view.findViewById(R.id.chipGroup7);
        obsessions.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                selectedChip = view.findViewById(group.getCheckedChipId());
                question12 = new BooleanAnswer(Boolean.getBoolean(selectedChip.getText().toString()), 15, LocalDateTime.now().toString());

            }
        });
        ChipGroup excersice = (ChipGroup) view.findViewById(R.id.chipGroup11);
        excersice.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                selectedChip = view.findViewById(group.getCheckedChipId());
                question13 = new BooleanAnswer(Boolean.getBoolean(selectedChip.getText().toString()), 16, LocalDateTime.now().toString());

            }
        });
        // not sure about textEdit

        EditText seriousIssue = (EditText) view.findViewById(R.id.textInputEditText);
        seriousIssue.addTextChangedListener(new TextWatcher() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                question14 = new TextAnswer(String.valueOf(s), 18, LocalDateTime.now().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seriousIssue.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                }

            }
        });








        }
}

