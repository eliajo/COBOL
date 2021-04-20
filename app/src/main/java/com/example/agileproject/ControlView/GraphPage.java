package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.R;
import com.github.mikephil.charting.charts.LineChart;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_graph_page, container, false);
        LineChart chart = v.findViewById(R.id.chart);
        return v;
    }
}