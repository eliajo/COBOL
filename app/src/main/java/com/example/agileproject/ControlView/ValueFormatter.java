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

/**
 * Class that formats the x-axis to show telling values.
 * @author Elias Johansson, Alva Leufstedt
 */

public class ValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {

    private int size;
    private LocalDate localDate;
    private GraphHelper.TimePeriod timePeriod;
    private List<AnswerEntry> answerEntries;
    private int axisIndex;
    private String lastDate;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ValueFormatter(List<AnswerEntry> answerEntries, String lastDate, GraphHelper.TimePeriod timePeriod) {
        if (lastDate!=null){
        this.localDate = LocalDate.parse(lastDate);}
        else{
            this.localDate=LocalDate.now();
        }
        this.lastDate=lastDate;
        this.timePeriod = timePeriod;
        this.answerEntries = answerEntries;
        this.axisIndex=0;
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


    /**
     * Inherited method to format x-values. From Api MpAndroidChart
     *
     * @param value
     * @param axis
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        int index = (int) value;
        LocalDate returnDate;

        if (index >= answerEntries.size()||lastDate==null) {
            returnDate = localDate.plusDays(index);
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
            return returnDate.getDayOfMonth() + "/" + returnDate.getMonthValue() ;
        } else if (timePeriod == GraphHelper.TimePeriod.YEAR) {

            switch (localDate.getMonth().plus(axisIndex)) {
                case JANUARY:
                    axisIndex++;
                    return "Jan";
                case FEBRUARY:
                    axisIndex++;
                    return "Feb";
                case MARCH:
                    axisIndex++;
                    return "Mars";
                case APRIL:
                    axisIndex++;
                    return "April";
                case MAY:
                    axisIndex++;
                    return "Maj";
                case JUNE:
                    axisIndex++;
                    return "Juni";
                case JULY:
                    axisIndex++;
                    return "Juli";
                case AUGUST:
                    axisIndex++;
                    return "Aug";
                case SEPTEMBER:
                    axisIndex++;
                    return "Sep";
                case OCTOBER:
                    axisIndex++;
                    return "Okt";
                case NOVEMBER:
                    axisIndex++;
                    return "Nov";
                case DECEMBER:
                    axisIndex++;
                    return "Dec";

            }
        }

        return returnDate.toString();
    }
}