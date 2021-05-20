package com.example.agileproject.ControlView;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.agileproject.Model.AnswerEntry;
import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.GraphHelper;
import com.example.agileproject.Model.MultipleTextAnswer;
import com.example.agileproject.R;
import com.example.agileproject.Utils.AnswerConverter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingDouble;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.summingInt;

/**
 * Class that holds logic to draw the different graphs.
 * @author Elias Johansson, Alva Leufstedt, Elin Berthag
 */

public class GraphDrawer {

    /**
     *
     * @param entries List that contain lists of data points divided by questionid
     * @param holder The class that holds the graphs and other related data
     * @param position The position in entries where the current data is located
     * @param timePeriod The period of time that is to be viewed.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawLineChart(List<List<AnswerEntry>> entries, GraphAdapter.GraphHolder holder, int position, GraphHelper.TimePeriod timePeriod) {
        LineChart chart = (LineChart) holder.getGraph();
        chart.clear();
        int id = entries.get(position).get(0).getQuestionId();
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
            case 1000:
                return;
            case 2000:
                return;
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
        List<AnswerEntry> tmpAnswerEntryList = new ArrayList<>();
        if (timePeriod.equals( GraphHelper.TimePeriod.YEAR)){
            convertToYear(entries, position, tmpAnswerEntryList);
        }
        if (entries.get(position).get(0).getQuestionId() == 1000 || entries.get(position).get(0).getQuestionId() == 2000) {
            converterList = new ArrayList<>();
        } else {
            if (timePeriod== GraphHelper.TimePeriod.YEAR){
                converterList = new ArrayList<>(tmpAnswerEntryList);
            }
            else {
            converterList = new ArrayList<>(entries.get(position));}
        }
        lineDataSet = new LineDataSet(converterList, "Dagar");
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.parseColor("#add8e6"));
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
                LineDataSet secondLineDataSet;
                List<Entry> secondList;
                if (timePeriod== GraphHelper.TimePeriod.YEAR){
                    List <AnswerEntry> tmpDoubleGraphList = new ArrayList();
                    convertToYear(entries,relatedPosition,tmpDoubleGraphList);
                    secondList= new ArrayList<>(tmpDoubleGraphList);

                }
                else{
                 secondList= new ArrayList<>(entries.get(relatedPosition));
                 }
                secondLineDataSet = new LineDataSet(secondList, "Dagar");
                secondLineDataSet.setColor(Color.parseColor("#97e1d0"));
                secondLineDataSet.setCircleColor(Color.parseColor("#30a68b"));
                secondLineDataSet.setCircleRadius(6);
                secondLineDataSet.setCircleHoleRadius(3);
                secondLineDataSet.setLineWidth(2);
                lineData = new LineData(lineDataSet, secondLineDataSet);
                lineDataSet.setDrawFilled(false);

            } else {
                lineData = new LineData(lineDataSet);

            }

        }
        //Temporary code to show graph
        else {
            lineData = new LineData(lineDataSet);
        }
        chart.getAxisRight().setEnabled(false);


        /*switch (timePeriod) {
            case WEEK:
                chart.getXAxis().setAxisMaximum(7);
                break;
            //Not really 30 days a month so might need to fix this
            case MONTH:
                chart.getXAxis().setAxisMaximum(30);
                break;
            case YEAR:
                chart.getXAxis().setAxisMaximum(365);
        }*/


        chart.getXAxis().setAxisMinimum(0f);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //chart.setVisibleXRange(entries.size()-1,entries.size()-1);

        if (id==1){
            chart.getAxisLeft().setAxisMinimum(-5);
            chart.getAxisLeft().setAxisMaximum(5);
        }
        else{
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setAxisMaximum(10);}
        chart.getAxisLeft().setGranularity(1f);
        chart.getAxisLeft().setTextColor(Color.parseColor("#4682b4"));
        chart.getAxisLeft().setTextSize(15f);
        chart.getXAxis().setTextColor(Color.parseColor("#4682b4"));
        chart.getXAxis().setTextSize(13f);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setZeroLineColor(Color.parseColor("#4682b4"));
        chart.getXAxis().setAxisLineColor(Color.parseColor("#4682b4"));
        //chart.getXAxis().setXOffset(10f);
        chart.getXAxis().setDrawGridLines(false);
        chart.setClickable(false);
        chart.setTouchEnabled(false);
        chart.setPinchZoom(false);

        //chart.setVisibleXRangeMinimum(0);
        //chart.setVisibleXRangeMaximum(10);


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
        lineDataSet.setGradientColor(Color.parseColor("#ffffff"), Color.parseColor("#add8e6"));
        holder.getMainLabel().setTextColor(Color.parseColor("#4682b4"));
        holder.getMainLabel().setTextSize(22f);
        chart.getDescription().setText("");
        chart.getLegend().setEnabled(false);
        com.example.agileproject.ControlView.ValueFormatter valueFormatter;
        if (timePeriod== GraphHelper.TimePeriod.YEAR){
            valueFormatter= new ValueFormatter(tmpAnswerEntryList,tmpAnswerEntryList.get(0).getDateAdded(),timePeriod);
        }
        else {
            valueFormatter = new com.example.agileproject.ControlView.ValueFormatter(entries.get(position), entries.get(position).get(0).getDateAdded(), timePeriod);
        }
        if (timePeriod== GraphHelper.TimePeriod.WEEK){
            chart.getXAxis().setGranularity(1f);
            chart.getXAxis().setAxisMaximum(6);
            chart.getXAxis().setLabelCount(7,false);}
        else if (timePeriod== GraphHelper.TimePeriod.MONTH){
            chart.getXAxis().setGranularity(7f);
            chart.getXAxis().setAxisMaximum(30);
            chart.getXAxis().setLabelCount(4,true);

        }
        else if (timePeriod== GraphHelper.TimePeriod.YEAR){
            chart.getXAxis().setAxisMaximum(12);
            chart.getXAxis().setLabelCount(12,true);
        }
        chart.getXAxis().setValueFormatter(valueFormatter);


        chart.setData(lineData);
        chart.fitScreen();
        chart.invalidate();

    }

    /**
     * Method that converts datapoints by merging them into one per month
     * @param entries List that contain lists of data points divided by questionid
     * @param position Position of the data in entries that are to be converted
     * @param tmpAnswerEntryList New list that is to be populated
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void convertToYear(List<List<AnswerEntry>> entries, int position, List<AnswerEntry> tmpAnswerEntryList) {
        LocalDate localDate = LocalDate.parse(entries.get(position).get(0).getDateAdded());
        for (int i =0;i<12;i++) {
            int a = i;
            List <AnswerEntry> returnList=entries.get(position).stream().filter(o -> LocalDate.parse(o.getDateAdded()).getMonth() == localDate.getMonth().plus(a)).collect(Collectors.toList());
           int sum = 0;
            for (AnswerEntry answerEntry:returnList) {
                sum +=answerEntry.getY();
            }
            if (returnList.size()>0){
            int mean = sum/returnList.size();
            tmpAnswerEntryList.add(new AnswerEntry(i,mean,entries.get(position).get(0).getQuestionId(),localDate.plusMonths(i).toString()));}
        }
    }

    /**
     * Method that handles logic to draw piecharts
     * @param entries List that contain lists of data points divided by questionid
     * @param holder The class that holds the graphs and other related data
     * @param position The position in entries where the current data is located
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawPieChart(List<List<AnswerEntry>> entries, GraphAdapter.GraphHolder holder, int position) {
        PieChart pieChart = (PieChart) holder.getGraph();
        pieChart.clear();
        PieData pieData;

        pieChart.setClickable(false);
        pieChart.setTouchEnabled(false);
        int id = entries.get(position).get(0).getQuestionId();
        switch (id) {
            case 7:
                holder.getMainLabel().setText("Har du sovit bra inatt?");
                break;
            case 9:
                holder.getMainLabel().setText("Har du tagit din medicin idag?");
                break;
            case 10:
                holder.getMainLabel().setText("Hur du haft några biverkningar idag? (Tryck för mer info)");
                pieChart.setClickable(true);
                pieChart.setTouchEnabled(true);
                pieChart.setRotationEnabled(false);
                break;
            case 11:
                holder.getMainLabel().setText("Har du druckit alkohol idag?");
                break;
            case 12:
                holder.getMainLabel().setText("Har du haft tvångstankar?");
                break;
            case 13:
                holder.getMainLabel().setText("Har du gjort någon fysisk aktivitet? (Tryck för mer info)");
                pieChart.setClickable(true);
                pieChart.setTouchEnabled(true);
                pieChart.setRotationEnabled(false);
                break;
            default:
                throw new IllegalArgumentException("No valid questionID");
        }
        //   List<PieEntry> pieEntryList = new ArrayList<>(entries.get(position));


        //     (entries.get(position));

        List<PieEntry> pieEntryList = new ArrayList<>();
        if (id==10||id==7) {
            if (id==10) {
                final List<PieEntry> tmpPieEntryList = new ArrayList<>();
                GraphHelper graphHelper = new GraphHelper();
                List<MultipleTextAnswer> otherEffectsList;
                otherEffectsList = graphHelper.getMultipleTextAnswerFromDateToDate(entries.get(position).get(0).getDateAdded(), entries.get(position).get(entries.get(position).size() - 1).getDateAdded(), 101);

                //This looks through all the different dates and adds them together by answer. For example if you choose Annat three times it will
                //have the weight of three in the final list that will be added to the graph.
                for (int i = 0; i < otherEffectsList.size(); i++) {

                    List<String> strings = (List<String>) otherEffectsList.get(i).getData();
                    for (int j = 0; j < strings.size(); j++) {
                        if (j == strings.size() - 1 && otherEffectsList.get(i).isOther()) {
                            pieEntryList.add(new AnswerEntry("Annat", 1, 101, ""));
                        }
                        else {
                            pieEntryList.add(new AnswerEntry(strings.get(j), 1, 101, ""));
                        }
                        }
                }
                Map<String, Double> map = pieEntryList.stream()
                        .collect(groupingBy(PieEntry::getLabel,
                                summingDouble((PieEntry::getValue))));
                pieEntryList.clear();
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEachOrdered((e) -> tmpPieEntryList.add(new AnswerEntry(e.getKey(), e.getValue().floatValue(), 101, "")));
                pieEntryList.addAll(tmpPieEntryList);
                //Sum all nos
                int yes = 0;
                int no = 0;
                for (AnswerEntry entry : entries.get(position)) {
                    if (entry.getY() == 1) {
                        yes++;
                    } else {
                        no++;
                    }

                }
                if (no>0) {
                    pieEntryList.add(new AnswerEntry("Nej", no, 101, ""));
                }
            }
            else if (id==7) {
                GraphHelper graphHelper = new GraphHelper();
                List<AnswerEntry> sleepList = graphHelper.getDataFromDateToDate(entries.get(position).get(0).getDateAdded(), entries.get(position).get(entries.get(position).size() - 1).getDateAdded(), 71);
                Map<String, Double> map = sleepList.stream()
                        .collect(groupingBy(AnswerEntry::getLabel,
                                summingDouble((AnswerEntry::getValue))));
                sleepList.clear();
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEachOrdered((e) -> sleepList.add(new AnswerEntry(e.getKey(), e.getValue().floatValue(), 71, "")));
                pieEntryList.addAll(sleepList);
                int yes = 0;
                int no = 0;
                for (AnswerEntry entry : entries.get(position)) {
                    if (entry.getY() == 1) {
                        yes++;
                    } else {
                        no++;
                    }
                }
                for (PieEntry ae :pieEntryList
                ) {
                    if(ae.getLabel().equals("Jag vaknade många gånger under natten")){
                        ae.setLabel("Jag vaknade under natten");
                    }
                    if(ae.getLabel().equals("Jag vaknade tidigt och kunde inte somna om")){
                        ae.setLabel("Jag vaknade tidigt");
                    }
                }
                if (yes > 0) {
                    pieEntryList.add(new AnswerEntry("Ja", yes, 71, ""));
                }
            }
        }



        else {
            int yes = 0;
            int no = 0;
            for (AnswerEntry entry : entries.get(position)) {
                if (entry.getY() == 1) {
                    yes++;
                } else {
                    no++;
                }

            }


        AnswerEntry yesEntry = new AnswerEntry("Ja",yes,id,"");
        AnswerEntry noEntry = new AnswerEntry("Nej",no,id,"");
        // pieEntryList.add(new PieEntry(30,"Ja"));
        List <AnswerEntry> answerEntryList = new ArrayList<>();
        if(yes!=0){
        answerEntryList.add(yesEntry);}
        if(no!=0){
        answerEntryList.add(noEntry);}
        pieEntryList = new ArrayList<>(answerEntryList);}
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Procent");
        pieDataSet.setLabel("");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        //pieDataSet.setSelectionShift(10f);
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
       // LegendEntry legendEntry = new LegendEntry();
       // legendEntry.label = "Jag vaknade flera gånger under natten";
       // pieChart.getLegend().setCustom(Arrays.asList(legendEntry));

        if(id==7 || id==10) {
            pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE); // labels outside of pie chart
            pieDataSet.setValueLinePart1OffsetPercentage(90f);
            pieDataSet.setValueLineColor(Color.parseColor("#4682b4"));
            pieDataSet.setValueLinePart1Length(0.6f);
            pieDataSet.setValueLinePart2Length(0.10f);
            pieChart.setExtraOffsets(30.f, 5.f, 30.f, 5.f);
            pieChart.getLegend().setTextSize(10f);
            pieChart.setEntryLabelTextSize(10f);

        }

    }


}
