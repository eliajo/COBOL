package com.example.agileproject.ControlView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.agileproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @author William Hugo
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView ContactRecycleView;


    String s1[], s2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactRecycleView = findViewById(R.id.ContactRecycleView);

        s1 = getResources().getStringArray(R.array.Contacts);
        s2 = getResources().getStringArray(R.array.Telefonenumber);

        ContactAdapter contactAdapter = new ContactAdapter(this, s1,s2);

        //This code connects the navigation bar to the fragment for our four main pages
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        NavController navController = Navigation.findNavController(this, R.id.main_pages_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}