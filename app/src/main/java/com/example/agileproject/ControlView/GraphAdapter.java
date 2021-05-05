package com.example.agileproject.ControlView;

import android.content.Context;
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
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to display graphs in a list. Contains an inheritence structure for different types of graphs.
 *
 * @author Elias Johansson
 */
public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.GraphHolder> {

    private static final int linechartID = 1;
    private static final int piechartID = 2;

    private Context context;
    private List<List<AnswerEntry>> entries;
    private GraphDrawer graphDrawer;
    public GraphAdapter(Context context,List<List<AnswerEntry>> entries){
        this.context = context;
        this.entries = entries;
        this.graphDrawer = new GraphDrawer();
    }

    @NonNull
    @Override
    public GraphAdapter.GraphHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view ;
        if (viewType == linechartID) {
            view = LayoutInflater.from(context).inflate(R.layout.linegraph, parent, false);
            return new LineGraphHolder(view);
        } else if (viewType == piechartID) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.piegraph, parent, false);
            return new PieGraphHolder(view);
        }
        else throw new IllegalArgumentException("No valid viewtype");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull GraphHolder holder, int position) {
      //if(viewtype == linechartid (For later))
        if(holder.getItemViewType()==linechartID) {
            graphDrawer.drawLineChart(entries, holder, position);
        }
        else graphDrawer.drawPieChart(entries,holder, position);

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
            case 9:
                return linechartID;
            case 7:
            case 10:
            case 11:
            case 13:
            case 14:
            case 17:
                return piechartID;
            default: throw new IllegalArgumentException("No valid questionID");
        }
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
    /**
     * Creates a holder for LineChart
     */
    public class LineGraphHolder extends GraphHolder{
          private LineChart chart;
          private TextView mainLabel;

        public LineGraphHolder(@NonNull View itemView) {
            super(itemView);
            this.chart = itemView.findViewById(R.id.linechart);
            this.mainLabel = itemView.findViewById(R.id.LineChartTextView);
            Button oneWeek = (Button) itemView.findViewById(R.id.lineOneWeek);
            oneWeek.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Add one week worth of data to the linegraph
                }
            });
            Button oneMonth = (Button) itemView.findViewById(R.id.lineOneMonth);
            oneMonth.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Add one month worth of data to the linegraph
                }
            });
            Button sinceBeginning = (Button) itemView.findViewById(R.id.lineSinceBeginning);
            sinceBeginning.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Since beginning linegraph
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

    }
    /**
     * Creates a holder for PieChart
     *
     * @author Alva och Elin
     */
    public class PieGraphHolder extends GraphHolder{
        private PieChart chart;
        private TextView mainLabel;

        public PieGraphHolder(@NonNull View itemView) {
            super(itemView);
            this.chart = itemView.findViewById(R.id.piechart);
            this.mainLabel = itemView.findViewById(R.id.PieChartTextView);

            Button oneWeek = (Button) itemView.findViewById(R.id.pieOneWeek);
            oneWeek.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Add one week worth of data to the piegraph
                }
            });

            Button oneMonth = (Button) itemView.findViewById(R.id.pieOneMonth);
            oneMonth.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Add one month worth of data to the list
                }
            });

            Button sinceBeginning = (Button) itemView.findViewById(R.id.pieSinceBeginning);
            sinceBeginning.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Since beginning graph
                }
            });
        }

        @Override
        Chart<PieData> getGraph() { return chart;
        }

        @Override
        TextView getMainLabel() {
            return mainLabel;
        }

    }
}
