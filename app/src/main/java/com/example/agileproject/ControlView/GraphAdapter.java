package com.example.agileproject.ControlView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agileproject.Model.AnswerEntry;
import com.example.agileproject.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.List;

/**
 * Class to display graphs in a list. Contains an inheritence structure for different types of graphs.
 *
 * @author Elias Johansson
 */
public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.GraphHolder> {


    private Context context;
    private List<List<AnswerEntry>> entries;
    public GraphAdapter(Context context,List<List<AnswerEntry>> entries){
        this.context = context;
        this.entries = entries;
    }

    @NonNull
    @Override
    public GraphAdapter.GraphHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.linegraph, parent, false);
        return new GraphAdapter.LineGraphHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GraphHolder holder, int position) {
      //if(viewtype == linechartid (For later))

        LineChart chart = (LineChart) holder.getGraph();


        if ((entries.get(position).get(0).getQuestionId()==10)){

            holder.getMainLabel().setText("Hur du har rankat ditt välmående");
        }

        //Not nice, if someone knows a better way feel free to fix it.
        //Think it should work though. This is because lineDataSet needs an Entry but
        //we still need questionId from AnswerEntry
        List<? extends Entry> converterList = entries.get(position);
        LineDataSet lineDataSet = new LineDataSet((List<Entry>) converterList, "Dagar");

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
    }



    @Override
    public int getItemCount() {
        return entries.size();
    }

    /**
     * Inheritence structure to make sure that the adapter can show different types of graphs.
     */
    public abstract class GraphHolder extends RecyclerView.ViewHolder{

        public GraphHolder(@NonNull View itemView) {
            super(itemView);
        }
        abstract ViewGroup getGraph();

        abstract TextView getMainLabel();
    }

    public class LineGraphHolder extends GraphHolder{
          private LineChart chart;
          private TextView mainLabel;

        public LineGraphHolder(@NonNull View itemView) {
            super(itemView);
            this.chart = itemView.findViewById(R.id.linechart);
            this.mainLabel = itemView.findViewById(R.id.LineChartTextView);
        }

        @Override
        Chart<LineData> getGraph() {
            return chart;
        }

        @Override
        TextView getMainLabel() {
            return mainLabel;
        }

    }
}
