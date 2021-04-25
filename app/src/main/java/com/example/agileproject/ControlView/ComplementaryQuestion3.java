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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.agileproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComplementaryQuestion3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComplementaryQuestion3 extends Fragment {

    NavController navController;
    EditText SideEffects;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComplementaryQuestion3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComplementaryQuestion3.
     */
    // TODO: Rename and change types and number of parameters
    public static ComplementaryQuestion3 newInstance(String param1, String param2) {
        ComplementaryQuestion3 fragment = new ComplementaryQuestion3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1  =getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complementary_question3, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
         SideEffects=(EditText) view.findViewById(R.id.textInputSideEffects);

        view.findViewById(R.id.next_button_ComplementaryQuestions3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_complementaryQuestion3_to_question4);
            }
        });
        view.findViewById(R.id.otherSideEffectsChip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SideEffects.setVisibility(View.VISIBLE);
            }
        });




    }
}