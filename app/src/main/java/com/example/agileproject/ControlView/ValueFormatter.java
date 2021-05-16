package com.example.agileproject.ControlView;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Model.AnswerEntry;
import com.example.agileproject.Model.GraphHelper;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;


public class ValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {

    private int size;
    private LocalDate localDate;
    private GraphHelper.TimePeriod timePeriod;
    private List<AnswerEntry> answerEntries;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ValueFormatter(List<AnswerEntry> answerEntries, String lastDate, GraphHelper.TimePeriod timePeriod) {
        this.localDate = LocalDate.parse(lastDate);
        this.timePeriod = timePeriod;
        this.answerEntries = answerEntries;
    }

    //override?
    public String getXValue(String dateInMillisecons, int index, ViewPortHandler viewPortHandler) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
            return sdf.format(new Date(Long.parseLong(dateInMillisecons)));

        } catch (Exception e) {

            return dateInMillisecons;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        int index = (int) value;
        LocalDate returnDate;
        if (index >= answerEntries.size()) {
            returnDate = localDate.plusDays(index-answerEntries.size()+1);
        } else {
            LocalDate valueDate = LocalDate.parse(answerEntries.get(index).getDateAdded());
            long daysBetween = DAYS.between(valueDate, localDate);
            returnDate = localDate.minusDays(daysBetween);
        }
        if (timePeriod == GraphHelper.TimePeriod.WEEK) {
            switch (returnDate.getDayOfWeek()) {
                case MONDAY:
                    return "Mån";
                case TUESDAY:
                    return "Tis";
                case WEDNESDAY:
                    return "Ons";
                case THURSDAY:
                    return "Tors";
                case FRIDAY:
                    return "Fre";
                case SATURDAY:
                    return "Lör";
                case SUNDAY:
                    return "Sön";
            }
        } else if (timePeriod == GraphHelper.TimePeriod.MONTH) {
            return returnDate.toString();
        } else if (timePeriod == GraphHelper.TimePeriod.YEAR) {

            switch (returnDate.getMonth()) {
                case JANUARY:
                    return "Jan";
                case FEBRUARY:
                    return "Feb";
                case MARCH:
                    return "Mars";
                case APRIL:
                    return "April";
                case MAY:
                    return "Maj";
                case JUNE:
                    return "Juni";
                case JULY:
                    return "Juli";
                case AUGUST:
                    return "Aug";
                case SEPTEMBER:
                    return "Sep";
                case OCTOBER:
                    return "OKT";
                case NOVEMBER:
                    return "Nov";
                case DECEMBER:
                    return "Dec";

            }
        }

        return returnDate.toString();
    }
}