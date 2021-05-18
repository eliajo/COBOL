package com.example.agileproject.ControlView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.R;
import com.example.agileproject.Utils.AlarmHandler;
import com.example.agileproject.Utils.AnswerConverter;
import com.example.agileproject.Utils.FileHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author William Hugo
 * @author Edenia Isaac
 */
public class MainActivity extends AppCompatActivity {
    NavController navController;

    private Button startQuiz;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileHandler fileHandler=new FileHandler();
       String s =fileHandler.read(getApplicationContext(),"Answers.txt");
       AnswerConverter.getInstance().convert(s);





        //This code connects the navigation bar to the fragment for our four main pages
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        NavController navController = Navigation.findNavController(this, R.id.main_pages_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        AlarmHandler alarmHandler = new AlarmHandler();

        alarmHandler.setAlarm(getApplicationContext(),22,AlarmHandler.REMINDER_TYPE);
        createNotificationChannel();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Name";
            String description = "Desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Test", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }





}