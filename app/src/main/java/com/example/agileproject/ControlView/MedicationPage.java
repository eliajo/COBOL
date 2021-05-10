package com.example.agileproject.ControlView;
/** A class for the page where you can add your medicines and follow when you started taking them in the graphs.
 * Also, your doctor can log for which personal levels of different answers you should be notified to contact your doctor.
 * @author Klara Jakobsson
 */
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.agileproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MedicationPage extends Fragment {

    NavController navController;

    public MedicationPage() {
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
        View v = inflater.inflate(R.layout.fragment_medication_page, container, false);

        RecyclerView medicationRecyclerView = v.findViewById(R.id.MedicationRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        medicationRecyclerView.setLayoutManager(layoutManager);

        // Set the buttons for this fragment
        Button addMedication = v.findViewById(R.id.addMedicationButton);
        Button addSign = v.findViewById(R.id.addSignButton);

        // main_pages_fragment defines the navigation between pages.
        navController = Navigation.findNavController(this.getActivity(), R.id.main_pages_fragment);

        // when clicking on add medication this will happen....
        addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to other page TODO:navigation to new page (that is not yet created) for this operation
               // navController.navigate(R.id.switch_contact_page22);
            }
        });

        // when clicking on add sign this will happen....
        addSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to other page TODO:navigation to new page (that is not yet created) for this operation
               // navController.navigate(R.id.switch_contact_page22);
            }
        });

        return v;
    }
}