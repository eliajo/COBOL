package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.R;
import com.example.agileproject.Utils.AnswerConverter;

import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarAnswer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarAnswer extends Fragment {

    private String date;

    public CalendarAnswer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CalendarAnswer.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarAnswer newInstance() {
        CalendarAnswer fragment = new CalendarAnswer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_answer, container, false);
    }

    // run when clicking on a date, returns the answer from a specific date on question 14
    public String fetchAnswers(String date){
        List<Answerable> answersDate = AnswerConverter.getInstance().getAnswersByDate(date);
        for (Answerable a : answersDate){
            if (a.getQuestionId() == 14){
                return a.getInfoToWrite();
            }
        }
        return "Inget svar att visa för denna dag.";
    }
}