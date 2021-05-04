package com.example.agileproject.ControlView;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Model.AnswerEntry;
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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GraphDrawer {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawLineChart(List<List<AnswerEntry>> entries, GraphAdapter.GraphHolder holder, int position){
        LineChart chart = (LineChart) holder.getGraph();
        Integer id = entries.get(position).get(0).getQuestionId();
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
                holder.getMainLabel().setText("Hur mycket du sovit på nattid");
                break;
            case 6:
                holder.getMainLabel().setText("Hur mycket du sovit på dagtid");
                break;
            case 8:
                holder.getMainLabel().setText("Hur mycket ilska du haft");
                break;
            case 9:
                holder.getMainLabel().setText("Hur ditt mående har varit");
                break;
            default: throw new IllegalArgumentException("No valid questionID");
        }

        //Not nice, if someone knows a better way feel free to fix it.
        //Think it should work though. This is because lineDataSet needs an Entry but
        //we still need questionId from AnswerEntry
        //List<? extends Entry> converterList = entries.get(position);

        //Safer solution but but takes O(n) time. Might have to discuss this.
        List<Entry> converterList = new ArrayList<>(entries.get(position));
        LineDataSet lineDataSet = new LineDataSet(converterList, "Dagar");

        //Temporary code to show graph

        LineData lineData = new LineData(lineDataSet);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setAxisMaximum(entries.get(position).size());
        chart.getXAxis().setAxisMinimum(0f);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //chart.setVisibleXRange(entries.size()-1,entries.size()-1);
        chart.getAxisLeft().setAxisMaximum(10);
        chart.getAxisLeft().setAxisMinimum(0);

        chart.getAxisLeft().setGranularity(1f);

        chart.getAxisLeft().setDrawGridLines(false);

        chart.getXAxis().setGranularity(1f);
        chart.getXAxis().setDrawGridLines(false);

        //chart.setVisibleXRangeMinimum(0);
        //chart.setVisibleXRangeMaximum(10);
        chart.setData(lineData);
        chart.fitScreen();
        chart.invalidate();
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

    }

    public void drawPieChart(List<List<AnswerEntry>> entries, GraphAdapter.GraphHolder holder, int position) {
        PieChart pieChart = (PieChart) holder.getGraph();
        PieData pieData ;
        Integer id = entries.get(position).get(0).getQuestionId();
        switch(id) {
            case 7:
                holder.getMainLabel().setText("Hur du har sovit");
                break;
            case 10:
                holder.getMainLabel().setText("Hur ofta du tagit din medicin");
                break;
            case 11:
                holder.getMainLabel().setText("Hur ofta du haft biverkningar");
                break;
            case 13:
                holder.getMainLabel().setText("Hur ofta du druckit alkohol");
                break;
            case 14:
                holder.getMainLabel().setText("Hur ofta du har haft tvångstankar");
                break;
            case 17:
                holder.getMainLabel().setText("Hur ofta du har gjort någon fysisk aktivitet");
                break;
            default: throw new IllegalArgumentException("No valid questionID");
        }
        List<PieEntry> pieEntryList = new ArrayList<>(entries.get(position));

            pieChart = pieChart.findViewById(R.id.piechart);
            pieChart.setUsePercentValues(true);
            PieDataSet pieDataSet = new PieDataSet(pieEntryList,"Ja");
            pieDataSet.setLabel("");
            pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
            pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.invalidate();
            pieEntryList.add(new PieEntry(70,"Nej"));
            pieEntryList.add(new PieEntry(30,"Ja"));
            pieChart.setDrawHoleEnabled(false);
            pieData.setDrawValues(false);

    }




}
