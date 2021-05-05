package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.AnswerEntry;
import com.example.agileproject.Model.GraphHelper;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.R;
import com.example.agileproject.Utils.FileConverter;
import com.example.agileproject.Utils.FileFormatter;
import com.github.mikephil.charting.data.Entry;

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
        List<Answerable> answerableList = new ArrayList<>();


        //All this is just demo functionality for now
        NumberAnswer na1 = new NumberAnswer(5,10,"2021-01-01");
        NumberAnswer na2 = new NumberAnswer(6,10,"2021-01-02");
        NumberAnswer na3 = new NumberAnswer(7,10,"2021-01-03");
        NumberAnswer na4 = new NumberAnswer(5,10,"2021-01-04");
        NumberAnswer na5 = new NumberAnswer(6,10,"2021-01-05");
        NumberAnswer na6 = new NumberAnswer(7,10,"2021-01-06");
        NumberAnswer s1 = new NumberAnswer(3,20,"2021-01-01");
        NumberAnswer s2 = new NumberAnswer(3,20,"2021-01-02");
        NumberAnswer s3 = new NumberAnswer(3,20,"2021-01-03");


        answerableList.add(na1);
        answerableList.add(na2);
        answerableList.add(na3);
        answerableList.add(na4);
        answerableList.add(na5);
        answerableList.add(na6);
        answerableList.add(s1);
        answerableList.add(s2);
        answerableList.add(s3);
        FileFormatter ff = new FileFormatter();
        String save =ff.format(answerableList);

        FileConverter fileConverter = FileConverter.getInstance();
        fileConverter.convert(save);

        GraphHelper graphHelper = new GraphHelper();
        List<Answerable> wellBeingList = new ArrayList<>();
        List<Answerable> sleepList = new ArrayList<>();
        wellBeingList = graphHelper.getDataFromDateToDate("2021-01-01","2021-01-06",10);
        sleepList = graphHelper.getDataFromDateToDate("2021-01-01","2021-01-03",20);
        int index = 0;

        List<List<AnswerEntry>> entries = new ArrayList<>();

        List<AnswerEntry> wellBeingEntry = new ArrayList<>();
        List<AnswerEntry> sleepEntry = new ArrayList<>();

        for (Answerable a:wellBeingList) {
            int tmp = (Integer) a.getData();
            wellBeingEntry.add(new AnswerEntry(index,1.0f*tmp,a.getQuestionId()));
            index++;
        }
        index=0;

        for (Answerable a:sleepList) {
            int tmp = (Integer) a.getData();
            sleepEntry.add(new AnswerEntry(index,1.0f*tmp,a.getQuestionId()));
            index++;
        }

        entries.add(wellBeingEntry);
        entries.add(sleepEntry);


        RecyclerView recyclerView = v.findViewById(R.id.graph_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        GraphAdapter graphAdapter = new GraphAdapter(getContext(),entries);

        recyclerView.setAdapter(graphAdapter);

        return v;
    }
}