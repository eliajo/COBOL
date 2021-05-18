package com.example.agileproject.ControlView;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agileproject.Model.AnswerEntry;
import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.GraphHelper;
import com.example.agileproject.Model.MultipleTextAnswer;
import com.example.agileproject.R;
import com.example.agileproject.Utils.AnswerConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that shows more information about a graph in a list. Purely Android based.
 * @author Elias Johansson
 */
public class GraphInfoAdapter extends RecyclerView.Adapter<GraphInfoAdapter.GraphInfoHolder> {

    private int questionId;
    private List<MultipleTextAnswer> answerEntries;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public GraphInfoAdapter(Context context,int questionId) {
        this.questionId=questionId;
        this.context=context;
        GraphHelper graphHelper = new GraphHelper();
        LocalDate endDate =LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);
        if (questionId==101) {
            answerEntries = graphHelper.getMultipleTextAnswerFromDateToDate(startDate.toString(), endDate.toString(), questionId);
        }
    }


    @Override
    public GraphInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.graph_info_card,parent,false);
        return new GraphInfoHolder(v);
    }

    @Override
    public void onBindViewHolder(GraphInfoAdapter.GraphInfoHolder holder, int position) {
        holder.getDateText().append(answerEntries.get(position).getDate());
        //Not good but cant come up with other way now
        if (questionId==101){
        List<String> stringList = (List<String>) answerEntries.get(position).getData();
        StringBuilder sb = new StringBuilder();
        for (String s:stringList) {
            sb.append(s+"\n");

        }
        holder.getInfoText().append((sb.toString()) );
    }}

    @Override
    public int getItemCount() {
        if (questionId==101){
        return answerEntries.size();}
        //TODO add support for other questions of type answerable
         return 1;
    }

    protected class GraphInfoHolder extends RecyclerView.ViewHolder {
        private TextView infoText;
        private TextView dateText;
        public GraphInfoHolder(View itemView) {
            super(itemView);
            infoText=itemView.findViewById(R.id.infoText);
            dateText=itemView.findViewById(R.id.TitleTextView);
        }

        public TextView getInfoText() {
            return infoText;
        }

        public TextView getDateText() {
            return dateText;
        }
    }
}
