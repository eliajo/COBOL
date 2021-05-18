package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agileproject.R;

/**
 * Class that initializes list that shows more info about a graph
 */
public class GraphInfoPage extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph_info);
        RecyclerView recyclerView = findViewById(R.id.infoRecycler);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("Id");

        TextView textView = findViewById(R.id.graphInfoLabel);
        if (id == 101) {
            textView.append("Vilka biverkningar du haft");
        }
        if(id == 71) {
            textView.append("Varför du inte sovit bra");
        }
        if(id == 131) {
            textView.append("Vad du gjort för fysisk aktivitet");
        }

        GraphInfoAdapter graphInfoAdapter = new GraphInfoAdapter(this,id);
        recyclerView.setAdapter(graphInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
