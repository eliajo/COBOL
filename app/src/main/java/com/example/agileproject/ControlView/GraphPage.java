package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.GraphHelper;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.R;
import com.example.agileproject.Utils.FileConverter;
import com.example.agileproject.Utils.FileFormatter;
import com.example.agileproject.Utils.FileHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraphPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphPage extends Fragment {

    public GraphPage() {
        // Required empty public constructor
    }

    public static GraphPage newInstance() {
        GraphPage fragment = new GraphPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_graph_page, container, false);
        LineChart chart = v.findViewById(R.id.chart);
        List<Answerable> answerableList = new ArrayList<>();
        NumberAnswer na1 = new NumberAnswer(5,10,"2021-01-01");
        NumberAnswer na2 = new NumberAnswer(6,10,"2021-01-02");
        NumberAnswer na3 = new NumberAnswer(7,10,"2021-01-03");


        answerableList.add(na1);
        answerableList.add(na2);
        answerableList.add(na3);
        FileFormatter ff = new FileFormatter();
        String save =ff.format(answerableList);

        FileConverter fileConverter = FileConverter.getInstance();
        fileConverter.convert(save);

        GraphHelper graphHelper = new GraphHelper();
        answerableList = graphHelper.getDataFromDateToDate("2021-01-01","2021-01-03",10);
        int index = 0;
        List<Entry> entries = new ArrayList<>();

        for (Answerable a:answerableList) {
            int tmp = (Integer) a.getData();
            entries.add(new Entry(index,1.0f*tmp));
            index++;
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Test");

        LineData lineData = new LineData(lineDataSet);
        chart.getAxisRight().setEnabled(false);
       chart.getXAxis().setAxisMaximum(entries.size());
        chart.getXAxis().setAxisMinimum(0f);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //chart.setVisibleXRange(entries.size()-1,entries.size()-1);
        chart.getAxisLeft().setAxisMaximum(10);
        chart.getAxisLeft().setAxisMinimum(0);

        //chart.setVisibleXRangeMinimum(0);
        //chart.setVisibleXRangeMaximum(10);
        chart.setData(lineData);
        chart.fitScreen();
        chart.invalidate();


        return v;
    }
}