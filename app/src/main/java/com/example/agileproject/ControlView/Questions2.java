package com.example.agileproject.ControlView;

/**
 * @author Edenia Isaac
 */

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.R;

/**
 * A simple {@link Fragment} subclass.

 */
public class Questions2 extends Fragment {

    NavController navController;



    public Questions2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         navController = Navigation.findNavController(view);
         view.findViewById(R.id.next_button_questions2).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 navController.navigate(R.id.action_questions2_to_questions3);
             }
         });
         view.findViewById(R.id.chipNo).setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView6).setVisibility(View.GONE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.GONE);
                 view.findViewById(R.id.textView10).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.imageView).setVisibility(View.VISIBLE);

             }
         });
         view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView10).setVisibility(View.GONE);
                 view.findViewById(R.id.chipGroup).setVisibility(View.GONE);
                 view.findViewById(R.id.imageView).setVisibility(View.GONE);
                 view.findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);

             }
         });
         view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 view.findViewById(R.id.textView10).setVisibility(View.GONE);
                 view.findViewById(R.id.chipGroup).setVisibility(View.GONE);
                 view.findViewById(R.id.imageView).setVisibility(View.GONE);
                 view.findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                 view.findViewById(R.id.chipGroup3).setVisibility(View.VISIBLE);
             }
         });


    }
}