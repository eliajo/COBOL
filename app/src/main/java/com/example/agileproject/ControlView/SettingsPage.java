package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.agileproject.Model.AnalyzerSettable;
import com.example.agileproject.Model.AnalyzerSettingNumber;
import com.example.agileproject.R;
import com.example.agileproject.Utils.FileFormatter;
import com.example.agileproject.Utils.FileHandler;

import java.util.ArrayList;
import java.util.List;

/** A class for the page where you can add your medicines and follow when you started taking them in the graphs.
 * Also, your doctor can log for which personal levels of different answers you should be notified to contact your doctor.
 * @author Klara Jakobsson, William Hugo
 */
public class SettingsPage extends Fragment implements View.OnClickListener {

    FileHandler fileHandler = new FileHandler();
    FileFormatter fileFormatter = new FileFormatter();

    NavController navController;
    //Hallucination
    Boolean hBool = false;
    EditText hUpper;
    EditText hLower;
    EditText hTime;
    //Delusion
    Boolean dBool = false;
    EditText dUpper;
    EditText dLower;
    EditText dTime;
    //Anxiety
    Boolean aBool = false;
    EditText aUpper;
    EditText aLower;
    EditText aTime;
    //Irritation
    Boolean iBool = false;
    EditText iUpper;
    EditText iLower;
    EditText iTime;
    //Energy
    Boolean eBool = false;
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

        hLower = view.findViewById(R.id.hallucination_lower);
        hUpper = view.findViewById(R.id.hallucination_upper);
        hTime = view.findViewById(R.id.hallucination_time);
        Switch hSwitch = view.findViewById(R.id.hallucinations_switch);
        hSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    hBool = true;
                } else {
                    hBool = false;
                }
            }
        });

        dUpper = view.findViewById(R.id.delusions_upper);
        dLower = view.findViewById(R.id.delusions_lower);
        dTime = view.findViewById(R.id.delusions_time);
        Switch dSwitch = view.findViewById(R.id.delusions_switch);
        dSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBool = true;
                } else {
                    dBool = false;
                }
            }
        });

        aUpper = view.findViewById(R.id.anxiety_upper);
        aLower = view.findViewById(R.id.anxiety_lower);
        aTime = view.findViewById(R.id.anxiety_time);
        Switch aSwitch = view.findViewById(R.id.anxiety_switch);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    aBool = true;
                } else {
                    aBool = false;
                }
            }
        });

        iUpper = view.findViewById(R.id.irritation_upper);
        iLower = view.findViewById(R.id.irritation_lower);
        iTime = view.findViewById(R.id.irritation_time);
        Switch iSwitch = view.findViewById(R.id.irritation_switch);
        iSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    iBool = true;
                } else {
                    iBool = false;
                }
            }
        });

        eUpper = view.findViewById(R.id.energy_upper);
        eLower = view.findViewById(R.id.energy_lower);
        eTime = view.findViewById(R.id.energy_time);
        Switch eSwitch = view.findViewById(R.id.energy_switch);
        eSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    eBool = true;
                } else {
                    eBool = false;
                }
            }
        });

        Button saveSettings = view.findViewById(R.id.save_settings);
        saveSettings.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        List<AnalyzerSettable> settings = new ArrayList<>();

        if(hBool) {
            int id = 2, //Hallucination
                    time = Integer.parseInt(hTime.getText().toString()),
                    lower = Integer.parseInt(hLower.getText().toString()),
                    upper = Integer.parseInt(hUpper.getText().toString());
            settings.add(new AnalyzerSettingNumber(id, upper, lower, time));
        }
        if(dBool) {
            int id = 3, //Delusions
                    time = Integer.parseInt(dTime.getText().toString()),
                    lower = Integer.parseInt(dLower.getText().toString()),
                    upper = Integer.parseInt(dUpper.getText().toString());
            settings.add(new AnalyzerSettingNumber(id, upper, lower, time));
        }
        if(aBool) {
            int id = 4, //Anxiety
                    time = Integer.parseInt(aTime.getText().toString()),
                    lower = Integer.parseInt(aLower.getText().toString()),
                    upper = Integer.parseInt(aUpper.getText().toString());
            settings.add(new AnalyzerSettingNumber(id, upper, lower, time));
        }
        if(iBool) {
            int id = 8, //Irritation
                    time = Integer.parseInt(iTime.getText().toString()),
                    lower = Integer.parseInt(iLower.getText().toString()),
                    upper = Integer.parseInt(iUpper.getText().toString());
            settings.add(new AnalyzerSettingNumber(id, upper, lower, time));
        }
        if(eBool) {
            int id = 1, //Energy
                    time = Integer.parseInt(eTime.getText().toString()),
                    lower = Integer.parseInt(eLower.getText().toString()),
                    upper = Integer.parseInt(eUpper.getText().toString());
            settings.add(new AnalyzerSettingNumber(id, upper, lower, time));
        }

        if(!settings.isEmpty()) {
            fileHandler.empty(getContext(), "Settings.txt");
            String s = fileFormatter.formatSetting(settings);
            fileHandler.write(s, getContext(), "Settings.txt");
        } else {
            fileHandler.empty(getContext(), "Settings.txt");
        }
    }
}