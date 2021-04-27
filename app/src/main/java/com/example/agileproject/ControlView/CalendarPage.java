package com.example.agileproject.ControlView;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.agileproject.R;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


/**
 * Page for viewing calendar which shows dates the quiz was done on
 * @author William Hugo, Klara Jakobsson
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
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        caldroidFragment.setArguments(args);

        //ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
        //Date date = new Date(2021-1900,3,25);
        //caldroidFragment.setBackgroundDrawableForDate(green, date);
        //caldroidFragment.refreshView();

        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, caldroidFragment);
        t.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_page, container, false);
    }
}