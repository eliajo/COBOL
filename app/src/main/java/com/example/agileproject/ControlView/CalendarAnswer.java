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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarAnswer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarAnswer.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarAnswer newInstance(String param1, String param2) {
        CalendarAnswer fragment = new CalendarAnswer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_answer, container, false);
    }

    // run when clicking on a date, returns the answer from a specific date on question 14
    private String fetchAnswers(String date){
        List<Answerable> answersDate = AnswerConverter.getInstance().getAnswersByDate(date);
        for (Answerable a : answersDate){
            if (a.getQuestionId() == 14){
                return a.getInfoToWrite();
            }
        }
        return "Inget svar att visa för denna dag.";
    }
}