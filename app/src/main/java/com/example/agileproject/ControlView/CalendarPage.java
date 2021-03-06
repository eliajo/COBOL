package com.example.agileproject.ControlView;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.R;
import com.example.agileproject.Utils.AnswerConverter;
import com.example.agileproject.Utils.FileFormatter;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
        CaldroidFragment caldroid = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar calendar = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, calendar.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, calendar.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        caldroid.setArguments(args);
        // Kod för att testa så att det fungerar i kalendern.
        /* TextAnswer test = new TextAnswer("Test", 14, "2021-05-16");
        List <Answerable> list = new ArrayList<>();
        list.add(test);
        FileFormatter file = new FileFormatter();
        String s = file.format(list);
        AnswerConverter.getInstance().convert(s);

         */




        addAllEvents(caldroid);

        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, caldroid);
        t.commit();

        List<Date> dates = fetchDatesAndFormat();

        //Toast toast = new Toast(getContext());
        //toast.setText("Inget quiz sparat för denna dag");
        //toast.setDuration(Toast.LENGTH_SHORT);

        final CaldroidListener listener = new CaldroidListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectDate(Date date, View view) {



                    LocalDate local = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    System.out.println("THIS DATE EXISTS!!!");
                    CalendarAnswer c = CalendarAnswer.getInstance();
                    String s = local.toString();
                    c.fetchAnswers(s);


            }
        };
        caldroid.setCaldroidListener(listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_page, container, false);
    }

    private boolean dateExists(Date date, List<Date> dateList) {
        for(Date d : dateList) {
            if((d.toString()).equals(date.toString())) {
                return true;
            }
        }
        return false;
    }

    private void addAllEvents(CaldroidFragment cal) {
        List<Date> dates = fetchDatesAndFormat();
        for(Date d : dates) {
            addEventColor(cal, d);
        }
    }

    /**
     * Fetches all the dates of answered quizzes from FileConverter and formats the dates into the right format for the calendar
     */
    private List<Date> fetchDatesAndFormat() {
        List<String> stringDates = AnswerConverter.getInstance().getAllDates();
        List<Date> dates = new ArrayList<>();
        for(String s : stringDates) {
            String[] array = s.split("-"); //splits the dateString into an array of three strings, [0] = year, [1] = month, [2] = day
            dates.add(new Date(Integer.parseInt(array[0])-1900,Integer.parseInt(array[1])-1,Integer.parseInt(array[2])));
        }
        return dates;
    }

    /**
     * Adds a color to events, used for marking which days quizzes were done
     */
    private void addEventColor(CaldroidFragment cal, Date date) {
        ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
        cal.setBackgroundDrawableForDate(green, date);
    }

}