package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private TextView text;
    private static CalendarAnswer calendarAnswer;

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

    public static CalendarAnswer getInstance() {

            return calendarAnswer;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
         **final TextView helloTextView = (TextView) findViewById(R.id.text_view_id);
         **helloTextView.setText(R.string.user_greeting);
         */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_calendar_answer, container, false);
        text = view.findViewById(R.id.questionView);
        calendarAnswer = this;
        return view;
    }

    // run when clicking on a date, returns the answer from a specific date on question 14
    public void fetchAnswers(String date){
        List<Answerable> answersDate = AnswerConverter.getInstance().getAnswersByDate(date);
        if(answersDate.isEmpty()) {
            text.setText("Inga svar finns denna dag" + " " + date);
            return;
        }
        for (Answerable a : answersDate){
            if (a.getQuestionId() == 14){
                text.setText( (String) a.getData() + " " + date);
                // return (String) a.getData();
            }
            else {
                text.setText("Svar på fråga 14 saknas för" + " " + date);
            }
        }

    }
}