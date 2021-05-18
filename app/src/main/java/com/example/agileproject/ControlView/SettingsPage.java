package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.agileproject.R;

/** A class for the page where you can add your medicines and follow when you started taking them in the graphs.
 * Also, your doctor can log for which personal levels of different answers you should be notified to contact your doctor.
 * @author Klara Jakobsson, William Hugo
 */
public class SettingsPage extends Fragment implements View.OnClickListener {

    NavController navController;
    //Hallucination
    EditText hUpper;
    EditText hLower;
    EditText hTime;
    //Delusion
    EditText dUpper;
    EditText dLower;
    EditText dTime;
    //Anxiety
    EditText aUpper;
    EditText aLower;
    EditText aTime;
    //Irritation
    EditText iUpper;
    EditText iLower;
    EditText iTime;
    //Energy
    EditText eUpper;
    EditText eLower;
    EditText eTime;

    public SettingsPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and set it to the View called v
        View view = inflater.inflate(R.layout.fragment_settings_page, container, false);

        // main_pages_fragment defines the navigation between pages.
        navController = Navigation.findNavController(this.getActivity(), R.id.main_pages_fragment);

        hTime = view.findViewById(R.id.hallucination_time);
        hLower = view.findViewById(R.id.hallucination_lower);
        hUpper = view.findViewById(R.id.hallucination_upper);

        dUpper = view.findViewById(R.id.delusions_upper);
        dLower = view.findViewById(R.id.delusions_lower);
        dTime = view.findViewById(R.id.delusions_time);

        aUpper = view.findViewById(R.id.anxiety_upper);
        aLower = view.findViewById(R.id.anxiety_lower);
        aTime = view.findViewById(R.id.anxiety_time);

        iUpper = view.findViewById(R.id.irritation_upper);
        iLower = view.findViewById(R.id.irritation_lower);
        iTime = view.findViewById(R.id.irritation_time);

        eUpper = view.findViewById(R.id.energy_upper);
        eLower = view.findViewById(R.id.energy_lower);
        eTime = view.findViewById(R.id.energy_time);

        Button saveSettings = view.findViewById(R.id.save_settings);
        saveSettings.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        System.out.println("Click!  " + hUpper.getText() + " " + hLower.getText());
    }
}