package com.example.agileproject.ControlView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.agileproject.R;
import com.example.agileproject.Utils.AnswerConverter;
import com.example.agileproject.Utils.ContactConverter;
import com.example.agileproject.Utils.FileHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

/**
 * @author William Hugo
 * @author Edenia Isaac
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileHandler fileHandler = new FileHandler();
        String answers = fileHandler.read(getApplicationContext(),"Answers.txt");
        String contacts = fileHandler.read(getApplicationContext(),"Contacts.txt");

        AnswerConverter.getInstance().convert(answers);
        ContactConverter.getInstance().convert(contacts);

        //This code connects the navigation bar to the fragment for our four main pages
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        NavController navController = Navigation.findNavController(this, R.id.main_pages_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

}