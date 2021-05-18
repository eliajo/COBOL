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
import com.example.agileproject.Model.MultipleTextAnswer;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.Model.Storable;
import com.example.agileproject.R;
import com.example.agileproject.Utils.AnswerConverter;
import com.example.agileproject.Utils.FileFormatter;

import java.time.LocalDate;
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
        List<List<AnswerEntry>> entries = new ArrayList<>();





        //All this is just demo functionality for now
      /*  NumberAnswer na1 = new NumberAnswer(5,5,"2021-05-03");
        NumberAnswer na2 = new NumberAnswer(6,5,"2021-05-04");
        NumberAnswer na3 = new NumberAnswer(7,5,"2021-05-05");
        NumberAnswer na4 = new NumberAnswer(5,5,"2021-05-06");
        NumberAnswer na5 = new NumberAnswer(6,5,"2021-05-07");
        NumberAnswer na6 = new NumberAnswer(7,5,"2021-05-08");
        NumberAnswer na7 = new NumberAnswer(5,5,"2021-03-03");
        NumberAnswer nb1 = new NumberAnswer(2,6,"2021-05-03");
        NumberAnswer nb2 = new NumberAnswer(2,6,"2021-05-04");
        NumberAnswer nb3 = new NumberAnswer(2,6,"2021-05-05");
        NumberAnswer nb4 = new NumberAnswer(2,6,"2021-05-06");
        NumberAnswer nb5 = new NumberAnswer(2,6,"2021-05-07");
        NumberAnswer nb6 = new NumberAnswer(2,6,"2021-05-08");
        NumberAnswer s1 = new NumberAnswer(0,10,"2021-01-01");
        NumberAnswer s2 = new NumberAnswer(1,10,"2021-01-02");
        NumberAnswer s3 = new NumberAnswer(1,10,"2021-01-03");

        List <String> stringList = new ArrayList<>();
        stringList.add("Test");
        stringList.add("Functionality");
        stringList.add("Functionality");
        stringList.add("Test1");
        stringList.add("Test2");
        stringList.add("Functionality");
        stringList.add("Functionality");
        stringList.add("Functionality");
        stringList.add("Functionality");
        stringList.add("Functionality");

        MultipleTextAnswer m1 = new MultipleTextAnswer(stringList,101,"2021-01-01",true);
        MultipleTextAnswer m2 = new MultipleTextAnswer(stringList,101,"2021-01-02",false);
        MultipleTextAnswer m3 = new MultipleTextAnswer(stringList,101,"2021-01-03",false);



        answerableList.add(na1);
        answerableList.add(na2);
        answerableList.add(na3);
        answerableList.add(na4);
        answerableList.add(na5);
        answerableList.add(na6);
        answerableList.add(na7);
        answerableList.add(nb1);
        answerableList.add(nb2);
        answerableList.add(nb3);
        answerableList.add(nb4);
        answerableList.add(nb5);
        answerableList.add(nb6);
        answerableList.add(s1);
        answerableList.add(s2);
        answerableList.add(s3);
        answerableList.add(m1);
        answerableList.add(m2);
        answerableList.add(m3);
        FileFormatter ff = new FileFormatter();
        String save =ff.format(answerableList);

        AnswerConverter fileConverter = AnswerConverter.getInstance();
        fileConverter.convert(save);

        GraphHelper graphHelper = new GraphHelper();
        List<AnswerEntry> wellBeingList = new ArrayList<>();
        List<AnswerEntry> sleepList = new ArrayList<>();
        List<AnswerEntry> secondSleepList = new ArrayList<>();
        wellBeingList = graphHelper.getDataFromDateToDate("2021-05-03","2021-05-09",5);
        sleepList = graphHelper.getDataFromDateToDate("2021-01-01","2021-01-03",10);
        secondSleepList= graphHelper.getDataFromDateToDate("2021-05-03","2021-05-09",6);


        //Order is very important here...
        entries.add(wellBeingList);
        entries.add(sleepList);
        entries.add(secondSleepList);
*/

        GraphHelper graphHelper = new GraphHelper();

        String startDate = LocalDate.now().minusDays(7).toString();
        String endDate = LocalDate.now().toString();

        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,1));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,2));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,3));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,4));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,5));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,6));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,7));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,8));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,9));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,10));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,11));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,12));
        entries.add(graphHelper.getDataFromDateToDate(startDate,endDate,13));

        for(int i=0; i<entries.size();i++){
            if (entries.get(i).isEmpty()){
                entries.get(i).add(new AnswerEntry(0,0,1000,""));
            }
        }


        RecyclerView recyclerView = v.findViewById(R.id.graph_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        GraphAdapter graphAdapter = new GraphAdapter(getContext(),entries);

        recyclerView.setAdapter(graphAdapter);

        return v;
    }
}
