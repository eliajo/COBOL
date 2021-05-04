package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.agileproject.R;

/**
 * A simple {@link Fragment} subclass.
 * @author Edenia
 */
public class Questions3 extends Fragment {
    NavController navController;



    public Questions3() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question3, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        EditText editText=(EditText) view.findViewById(R.id.textInputSideEffects);
        view.findViewById(R.id.next_button_questions3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_questions3_to_question4);
            }
        });

       view.findViewById(R.id.chipYesSideEffects).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView2).setVisibility(View.GONE);
               view.findViewById(R.id.chipGroup).setVisibility(View.GONE);
               view.findViewById(R.id.textView11).setVisibility(View.VISIBLE);
               view.findViewById(R.id.side_effects).setVisibility(View.VISIBLE);
               view.findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
               //navController.navigate(R.id.action_questions3_to_complementaryQuestion3);
           }
       });

       view.findViewById(R.id.otherSideEffectsChip).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editText.setVisibility(View.VISIBLE);

           }
       });

       view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               view.findViewById(R.id.textView11).setVisibility(View.GONE);
               view.findViewById(R.id.side_effects).setVisibility(View.GONE);
               view.findViewById(R.id.imageView3).setVisibility(View.GONE);
               editText.setVisibility(View.GONE);
               view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
               view.findViewById(R.id.chipGroup).setVisibility(View.VISIBLE);
           }
       });
    }

}