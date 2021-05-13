package com.example.agileproject.ControlView;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


public class ValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {

    private int size;
    private LocalDate localDate;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ValueFormatter(int size, String lastDate){
        this.size = size;
        this.localDate=LocalDate.parse(lastDate);
    }

    //override?
    public String getXValue(String dateInMillisecons, int index, ViewPortHandler viewPortHandler) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
            return sdf.format(new Date(Long.parseLong(dateInMillisecons)));

        } catch (Exception e) {

            return dateInMillisecons;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String getAxisLabel(float value, AxisBase axis) {

        return localDate.minusDays((long) (size-value)).toString();
    }
}