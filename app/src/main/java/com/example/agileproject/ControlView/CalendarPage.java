package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.R;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarPage extends Fragment {

    public CalendarPage() {
        // Required empty public constructor
    }

    public static CalendarPage newInstance() {
        CalendarPage fragment = new CalendarPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_page, container, false);
    }
}