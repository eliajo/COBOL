package com.example.agileproject.ControlView;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.agileproject.Model.AnswerDataAnalyzer;
import com.example.agileproject.R;
import com.example.agileproject.Utils.AnswerConverter;

import java.time.LocalDate;

/**
 * @author William Hugo, Pegah Amanzadeh, Edenia Isaac
 */

public class HomePage extends Fragment {

    NavController navController;

    private Button quizButton;
    private Button statButton;
    private Button calendarButton;
    private TextView warningText;

    public HomePage() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        navController = Navigation.findNavController(this.getActivity(), R.id.main_pages_fragment);
        statButton = view.findViewById(R.id.stat_button);

        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.home_to_stats2);
            }
        });
        calendarButton = view.findViewById(R.id.calendar_button);

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.home_to_calendar2);
            }
        });
        if(!(AnswerConverter.getInstance().getAnswersByDate(LocalDate.now().toString()).isEmpty())){
            view.findViewById(R.id.quiz_Button).setVisibility(View.GONE);
            view.findViewById(R.id.stat_button).setVisibility(View.VISIBLE);
            view.findViewById(R.id.calendar_button).setVisibility(View.VISIBLE);
            view.findViewById(R.id.textView17).setVisibility(View.GONE);
            view.findViewById(R.id.imageView4).setVisibility(View.GONE);
            view.findViewById(R.id.warning_text).setVisibility(View.VISIBLE);
            view.findViewById(R.id.textView15).setVisibility(View.VISIBLE);
            view.findViewById(R.id.imageView6).setVisibility(View.VISIBLE);

            warningText = view.findViewById(R.id.warning_text);
            setWarningText();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizButton = view.findViewById(R.id.quiz_Button);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });
    }

    private void changeActivity(){
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWarningText() {
        AnswerDataAnalyzer ada = new AnswerDataAnalyzer();
        StringBuilder sb = new StringBuilder();
        sb.append("Varning: ");

        boolean[] hallucinationWarning = ada.analyzeBalance(2);
        if(hallucinationWarning[0]) {
            sb.append("l??ga hallucinationsv??rden");
        } else if(hallucinationWarning[1]) {
            sb.append("h??ga hallucinationsv??rden");
        }

        if(sb.toString().endsWith("n")) {
            sb.append(", ");
        }

        boolean[] delusionWarning = ada.analyzeBalance(3);
        if(delusionWarning[0]) {
            sb.append("l??ga vanf??rest??llningsv??rden");
        } else if(delusionWarning[1]) {
            sb.append("h??ga vanf??rest??llningsv??rden");
        }

        if(sb.toString().endsWith("n")) {
            sb.append(", ");
        }

        boolean[] anxietyWarning = ada.analyzeBalance(4);
        if(anxietyWarning[0]) {
            sb.append("l??ga ??ngestv??rden");
        } else if(anxietyWarning[1]) {
            sb.append("h??ga ??ngestv??rden");
        }

        if(sb.toString().endsWith("n")) {
            sb.append(", ");
        }

        boolean[] irritationWarning = ada.analyzeBalance(8);
        if(irritationWarning[0]) {
            sb.append("l??ga irritationsv??rden");
        } else if(irritationWarning[1]) {
            sb.append("h??ga irritationsv??rden");
        }

        if(sb.toString().endsWith("n")) {
            sb.append(", ");
        }

        boolean[] energyWarning = ada.analyzeBalance(1);
        if(energyWarning[0]) {
            sb.append("l??ga energiv??rden");
        } else if(energyWarning[1]) {
            sb.append("h??ga energiv??rden");
        }

        if(sb.toString().endsWith("n")) {
            sb.append(", ");
        }

        if(!sb.toString().contentEquals("Varning: ")) {
            sb.append("kontakta l??kare eller kontaktperson");
            warningText.setText(sb.toString());
        } else {
            warningText.setText("");
        }
    }

}