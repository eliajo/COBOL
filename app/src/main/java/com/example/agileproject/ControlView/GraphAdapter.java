package com.example.agileproject.ControlView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agileproject.Model.AnswerEntry;
import com.example.agileproject.Model.GraphHelper;
import com.example.agileproject.Model.Question;
import com.example.agileproject.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to display graphs in a list. Contains an inheritence structure for different types of graphs.
 *
 * @author Elias Johansson
 */
public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.GraphHolder> implements OnChartValueSelectedListener {

    private static final int linechartID = 1;
    private static final int piechartID = 2;



    private Context context;
    private List<List<AnswerEntry>> entries;
    private GraphDrawer graphDrawer;

    public GraphAdapter(Context context, List<List<AnswerEntry>> entries) {
        this.context = context;
        this.entries = entries;
        this.graphDrawer = new GraphDrawer();
    }

    @NonNull
    @Override
    public GraphAdapter.GraphHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == linechartID) {
            view = LayoutInflater.from(context).inflate(R.layout.linegraph, parent, false);
            return new LineGraphHolder(view);
        } else if (viewType == piechartID) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.piegraph, parent, false);
            return new PieGraphHolder(view);
        } else throw new IllegalArgumentException("No valid viewtype");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull GraphHolder holder, int position) {
        //if(viewtype == linechartid (For later))
        if (!holder.isInitialized()){
            holder.setQuestionId(entries.get(position).get(0).getQuestionId());
        }
        if (entries.get(position).get(0).getQuestionId()==101){
            return;
        }
        if (holder.getItemViewType() == linechartID) {
            //Only called once.
            graphDrawer.drawLineChart(entries, holder, position, GraphHelper.TimePeriod.WEEK);
            holder.setPosition(position);
        } else {
            graphDrawer.drawPieChart(entries, holder, position);
            if (holder.getQuestionId()==10){
                PieChart chart = (PieChart) holder.getGraph();
                chart.setOnChartValueSelectedListener(this);
            }
            holder.setPosition(position);
        }

    }

    // getting the questiontype from the position
    @Override
    public int getItemViewType(int position) {

        int id = entries.get(position).get(0).getQuestionId();
        switch (id) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                //Needed if no data exists to avoid crash and show empty graph
            case 1000:
                return linechartID;
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                //Needed if no data exists to avoid crash and show empty graph
            case 2000:
                return piechartID;
            default:
                throw new IllegalArgumentException("No valid questionID");
        }
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Intent intent = new Intent(context,GraphInfoPage.class);
        AnswerEntry answerEntry = (AnswerEntry) e;
       int id= answerEntry.getQuestionId();

       if (id==10){
           id=101;
       }
       intent.putExtra("Id",id);
        context.startActivity(intent);

    }

    @Override
    public void onNothingSelected() {

    }

    /**
     * Inheritence structure to make sure that the adapter can show different types of graphs.
     */
    public abstract class GraphHolder extends RecyclerView.ViewHolder {

        public GraphHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract ViewGroup getGraph();

        abstract void setPosition(int position);

        abstract TextView getMainLabel();

        abstract int getQuestionId();

        abstract void setQuestionId(int questionId);

        abstract boolean isInitialized();



        @RequiresApi(api = Build.VERSION_CODES.O)
        public void getNewDataWeek(int position, int questionId, int mockQuestionId){
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusWeeks(1);
            GraphHelper graphHelper = new GraphHelper();
            List<AnswerEntry> subEntries = graphHelper.getDataFromDateToDate(startDate.toString(), endDate.toString(), questionId);
            if (subEntries.size()==0){
                subEntries.add(new AnswerEntry(0,0,mockQuestionId,null));
            }
            entries.set(position,subEntries);

        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void getNewDataMonth(int position, int questionId, int mockQuestionId){
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusMonths(1);
            GraphHelper graphHelper = new GraphHelper();
            List<AnswerEntry> subEntries = graphHelper.getDataFromDateToDate(startDate.toString(), endDate.toString(), questionId);
            if (subEntries.size()==0){
                subEntries.add(new AnswerEntry(0,0,mockQuestionId,null));
            }
            entries.set(position,subEntries);

        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void getNewDataYear(int position, int questionId, int mockQuestionId){
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusYears(1);
            GraphHelper graphHelper = new GraphHelper();
            List<AnswerEntry> subEntries = graphHelper.getDataFromDateToDate(startDate.toString(), endDate.toString(), questionId);
            if (subEntries.size()==0){
                subEntries.add(new AnswerEntry(0,0,mockQuestionId,null));
            }
            entries.set(position,subEntries);

        }
    }

    /**
     * Creates a holder for LineChart
     */
    public class LineGraphHolder extends GraphHolder {
        private LineChart chart;
        private TextView mainLabel;
        private int position;
        private int questionId;
        private boolean initialized;
        private static final int lineGraphMockId  = 1000;
        private LineGraphHolder instance;

        public void setPosition(int position) {
            this.position = position;
        }

        public LineGraphHolder(@NonNull View itemView) {
            super(itemView);
            this.chart = itemView.findViewById(R.id.linechart);
            this.mainLabel = itemView.findViewById(R.id.LineChartTextView);
            Button oneWeek = (Button) itemView.findViewById(R.id.lineOneWeek);
            this.initialized = false;
            this.instance=this;
            oneWeek.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v) {
                    // Add one week worth of data to the linegraph

                    if (!initialized) {
                        questionId = entries.get(position).get(0).getQuestionId();
                        initialized = true;
                    }
                    getNewDataWeek(position,questionId,lineGraphMockId);
                    GraphDrawer g = new GraphDrawer();
                    g.drawLineChart(entries,instance,position,GraphHelper.TimePeriod.WEEK);

                }
            });
            Button oneMonth = (Button) itemView.findViewById(R.id.lineOneMonth);
            oneMonth.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v) {
                    // Add one month worth of data to the linegraph
                    if (!initialized) {
                        questionId = entries.get(position).get(0).getQuestionId();
                        initialized = true;
                    }
                    getNewDataMonth(position,questionId,lineGraphMockId);
                    GraphDrawer g = new GraphDrawer();
                    g.drawLineChart(entries,instance,position,GraphHelper.TimePeriod.MONTH);

                }
            });
            Button sinceBeginning = (Button) itemView.findViewById(R.id.lineSinceBeginning);
            sinceBeginning.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v) {
                    // Since beginning graph
                    if (!initialized) {
                        questionId = entries.get(position).get(0).getQuestionId();
                        initialized = true;
                    }
                    getNewDataYear(position,questionId,lineGraphMockId);
                    GraphDrawer g = new GraphDrawer();
                    g.drawLineChart(entries,instance,position,GraphHelper.TimePeriod.YEAR);
                }
            });
        }

        @Override
        Chart<LineData> getGraph() {
            return chart;
        }

        @Override
        TextView getMainLabel() {
            return mainLabel;
        }

        @Override
        int getQuestionId() {
            return questionId;
        }

        @Override
        void setQuestionId(int questionId) {
            this.questionId=questionId;
            initialized=true;
        }

        @Override
        boolean isInitialized() {
            return initialized;
        }

    }

    /**
     * Creates a holder for PieChart
     *
     * @author Alva och Elin
     */
    public class PieGraphHolder extends GraphHolder {
        private PieChart chart;
        private TextView mainLabel;
        private int position;
        private int questionId;
        private boolean initialized;
        private static final int pieGraphMockId  = 2000;
        private PieGraphHolder instance;

        public PieGraphHolder(@NonNull View itemView) {
            super(itemView);
            this.chart = itemView.findViewById(R.id.piechart);
            this.mainLabel = itemView.findViewById(R.id.PieChartTextView);
            initialized = false;
            this.instance=this;
            Button oneWeek = (Button) itemView.findViewById(R.id.pieOneWeek);
            oneWeek.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v) {
                    if (!initialized) {
                        questionId = entries.get(position).get(0).getQuestionId();
                        initialized = true;
                    }
                    getNewDataWeek(position,questionId,pieGraphMockId);
                    GraphDrawer g = new GraphDrawer();
                    g.drawPieChart(entries,instance,position);
                }
            });

            Button oneMonth = (Button) itemView.findViewById(R.id.pieOneMonth);
            oneMonth.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v) {
                    if (!initialized) {
                        questionId = entries.get(position).get(0).getQuestionId();
                        initialized = true;
                    }
                    getNewDataMonth(position,questionId,pieGraphMockId);
                    GraphDrawer g = new GraphDrawer();
                    g.drawPieChart(entries,instance,position);
                }
            });

            Button sinceBeginning = (Button) itemView.findViewById(R.id.pieSinceBeginning);
            sinceBeginning.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v) {
                    if (!initialized) {
                        questionId = entries.get(position).get(0).getQuestionId();
                        initialized = true;
                    }
                    getNewDataYear(position,questionId,pieGraphMockId);
                    GraphDrawer g = new GraphDrawer();
                    g.drawPieChart(entries,instance,position);
                }
            });
        }

        @Override
        Chart<PieData> getGraph() {
            return chart;
        }

        @Override
        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        TextView getMainLabel() {
            return mainLabel;
        }

        @Override
        int getQuestionId() {
            return questionId;
        }

        @Override
        void setQuestionId(int questionId) {
            this.questionId=questionId;
        }

        @Override
        boolean isInitialized() {
            return initialized;
        }

    }
}
