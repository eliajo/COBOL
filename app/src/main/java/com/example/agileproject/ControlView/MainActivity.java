package com.example.agileproject.ControlView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.agileproject.R;
import com.example.agileproject.Utils.AlarmHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @author William Hugo
 * @author Edenia Isaac
 */
public class MainActivity extends AppCompatActivity {

    private Button startQuiz;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This code connects the navigation bar to the fragment for our four main pages
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        NavController navController = Navigation.findNavController(this, R.id.main_pages_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        AlarmHandler alarmHandler = new AlarmHandler();

        alarmHandler.setAlarm(getApplicationContext(),21,AlarmHandler.REMINDER_TYPE);

    }



}