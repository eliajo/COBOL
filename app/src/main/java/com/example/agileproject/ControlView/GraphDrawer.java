package com.example.agileproject.ControlView;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.agileproject.Model.AnswerEntry;
import com.example.agileproject.Model.GraphHelper;
import com.example.agileproject.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GraphDrawer {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawLineChart(List<List<AnswerEntry>> entries, GraphAdapter.GraphHolder holder, int position, GraphHelper.TimePeriod timePeriod) {
        LineChart chart = (LineChart) holder.getGraph();
        chart.clear();
        int id = holder.getQuestionId();
        switch (id) {
            case 1:
                holder.getMainLabel().setText("Hur din energinivå har varit");
                break;
            case 2:
                holder.getMainLabel().setText("Hur mycket hallucinationer du haft");
                break;
            case 3:
                holder.getMainLabel().setText("Hur mycket vanföreställningar du haft");
                break;
            case 4:
                holder.getMainLabel().setText("Hur mycket ångest du haft");
                break;
            case 5:
                holder.getMainLabel().setText("Hur mycket du sovit på nattid jämfört med dagtid");
                break;
            case 6:
                holder.getMainLabel().setText("Hur mycket du sovit på nattid jämfört med dagtid");
                break;
            case 8:
                holder.getMainLabel().setText("Hur mycket ilska du haft");
                break;
            default:
                throw new IllegalArgumentException("No valid questionID");
        }


        //Not nice, if someone knows a better way feel free to fix it.
        //Think it should work though. This is because lineDataSet needs an Entry but
        //we still need questionId from AnswerEntry
        //List<? extends Entry> converterList = entries.get(position);

        //Safer solution but but takes O(n) time. Might have to discuss this.
        LineDataSet lineDataSet;
        List<Entry> converterList;
        if (entries.get(position).get(0).getQuestionId() == 1000 || entries.get(position).get(0).getQuestionId() == 2000) {
            converterList = new ArrayList<>();
        } else {
            converterList = new ArrayList<>(entries.get(position));
        }
        lineDataSet = new LineDataSet(converterList, "Dagar");

        LineData lineData;
        if (id == 5) {
            int relatedPosition = 0;
            int index = 0;
            boolean found = false;
            for (List<AnswerEntry> a : entries) {
                if (a.get(0).getQuestionId() == 6) {
                    relatedPosition = index;
                    found = true;
                }
                index++;
            }
            if (found) {
                List<Entry> secondList = new ArrayList<>(entries.get(relatedPosition));
                LineDataSet secondLineDataSet = new LineDataSet(secondList, "Dagar");
                lineData = new LineData(lineDataSet, secondLineDataSet);
            } else {
                lineData = new LineData(lineDataSet);

            }
        }
        //Temporary code to show graph
        else {
            lineData = new LineData(lineDataSet);
        }
        chart.getAxisRight().setEnabled(false);

        switch (timePeriod) {
            case WEEK:
                chart.getXAxis().setAxisMaximum(7);
                break;
            //Not really 30 days a month so might need to fix this
            case MONTH:
                chart.getXAxis().setAxisMaximum(30);
                break;
            case YEAR:
                chart.getXAxis().setAxisMaximum(365);
        }


        chart.getXAxis().setAxisMinimum(0f);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //chart.setVisibleXRange(entries.size()-1,entries.size()-1);

        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setAxisMaximum(10);
        chart.getAxisLeft().setGranularity(1f);

        chart.getAxisLeft().setDrawGridLines(false);

        chart.getXAxis().setGranularity(1f);
        chart.getXAxis().setDrawGridLines(false);
        chart.setClickable(false);
        chart.setTouchEnabled(false);
        chart.setPinchZoom(false);

        //chart.setVisibleXRangeMinimum(0);
        //chart.setVisibleXRangeMaximum(10);
        chart.setData(lineData);

        lineData.setDrawValues(false);
        chart.setDrawMarkers(false);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.parseColor("#87cefa"));
        lineDataSet.setCircleColor(Color.parseColor("#4682b4"));
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.DKGRAY);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.parseColor("#add8e6"));
        lineDataSet.setGradientColor(Color.parseColor("#ffffff"), Color.parseColor("#add8e6"));

        holder.getMainLabel().setTextColor(Color.parseColor("#4682b4"));
        holder.getMainLabel().setTextSize(22f);
        chart.getDescription().setText("");
        chart.getLegend().setEnabled(false);
        ValueFormatter valueFormatter = new com.example.agileproject.ControlView.ValueFormatter(entries.get(position).size(),entries.get(position).get(entries.get(position).size()-1).getDateAdded());
        chart.getXAxis().setValueFormatter(valueFormatter);

        if (timePeriod== GraphHelper.TimePeriod.WEEK){
            chart.setVisibleXRange(0, 7);
        }
        else if (timePeriod== GraphHelper.TimePeriod.MONTH){
            chart.setVisibleXRange(0, 4);
        }
        else if (timePeriod== GraphHelper.TimePeriod.YEAR){
            chart.setVisibleXRange(0, 12);
        }
        chart.fitScreen();
        chart.invalidate();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void drawPieChart(List<List<AnswerEntry>> entries, GraphAdapter.GraphHolder holder, int position) {
        PieChart pieChart = (PieChart) holder.getGraph();
        pieChart.clear();
        PieData pieData;

        pieChart.setClickable(false);
        pieChart.setTouchEnabled(false);
        int id = holder.getQuestionId();
        switch (id) {
            case 7:
                holder.getMainLabel().setText("Har du sovit bra inatt?");
                break;
            case 9:
                holder.getMainLabel().setText("Har du tagit din medicin idag?");
                break;
            case 10:
                holder.getMainLabel().setText("Hur du haft några biverkningar idag?");
                pieChart.setClickable(true);
                pieChart.setTouchEnabled(true);
                break;
            case 11:
                holder.getMainLabel().setText("Har du druckit alkohol idag?");
                break;
            case 12:
                holder.getMainLabel().setText("Har du haft tvångstankar?");
                break;
            case 13:
                holder.getMainLabel().setText("Har du gjort någon fysisk aktivitet?");
                break;
            default:
                throw new IllegalArgumentException("No valid questionID");
        }
        //   List<PieEntry> pieEntryList = new ArrayList<>(entries.get(position));


        //     (entries.get(position));
        List<PieEntry> pieEntryList;
        int yes = 0;
        int no = 0;
        for (AnswerEntry entry:entries.get(position)) {
            if (entry.getY()==1){
                yes++;
            }
            else {
                no++;
            }

        }

        pieChart = pieChart.findViewById(R.id.piechart);
        AnswerEntry yesEntry = new AnswerEntry("Yes",yes,id,"");
        AnswerEntry noEntry = new AnswerEntry("No",no,id,"");
        // pieEntryList.add(new PieEntry(30,"Ja"));
        List <AnswerEntry> answerEntryList = new ArrayList<>();
        if(yes!=0){
        answerEntryList.add(yesEntry);}
        if(no!=0){
        answerEntryList.add(noEntry);}
        pieEntryList = new ArrayList<>(answerEntryList);
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Procent");
        pieDataSet.setLabel("");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieData = new PieData(pieDataSet);
        //pieData.setValueFormatter(new PercentFormatter());
        pieChart.setData(pieData);
        //pieChart.setUsePercentValues(true);
        pieChart.invalidate();

        pieChart.setDrawHoleEnabled(false);
        pieData.setDrawValues(false);


        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.parseColor("#4682b4"));
        pieChart.setEntryLabelTextSize(25f);
        holder.getMainLabel().setTextColor(Color.parseColor("#4682b4"));
        holder.getMainLabel().setTextSize(22f);
        pieChart.getDescription().setText("");
        pieChart.getLegend().setTextSize(16f);
        pieChart.getLegend().setTextColor(Color.parseColor("#4682b4"));


    }


}
