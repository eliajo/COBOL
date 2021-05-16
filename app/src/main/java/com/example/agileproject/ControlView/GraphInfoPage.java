package com.example.agileproject.ControlView;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agileproject.R;

public class GraphInfoPage extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_info);

        RecyclerView recyclerView = findViewById(R.id.infoRecycler);
        Bundle bundle = getIntent().getExtras();
        int id =bundle.getInt("Id");
        GraphInfoAdapter graphInfoAdapter = new GraphInfoAdapter(this,id);
        recyclerView.setAdapter(graphInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
