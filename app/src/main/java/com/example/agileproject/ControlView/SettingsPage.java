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
import com.example.agileproject.Utils.AnalyzerConverter;
import com.example.agileproject.Utils.AnswerConverter;
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
    private Boolean hBool = false;
    private Switch hSwitch;
    private EditText hUpper;
    private EditText hLower;
    private EditText hTime;
    //Delusion
    private Boolean dBool = false;
    private Switch dSwitch;
    private EditText dUpper;
    private EditText dLower;
    private EditText dTime;
    //Anxiety
    Boolean aBool = false;
    private Switch aSwitch;
    private EditText aUpper;
    private EditText aLower;
    private EditText aTime;
    //Irritation
    Boolean iBool = false;
    private Switch iSwitch;
    private EditText iUpper;
    private EditText iLower;
    private EditText iTime;
    //Energy
    Boolean eBool = false;
    private Switch eSwitch;
    private EditText eUpper;
    private EditText eLower;
    private EditText eTime;

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
        hSwitch = view.findViewById(R.id.hallucinations_switch);
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
        dSwitch = view.findViewById(R.id.delusions_switch);
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
        aSwitch = view.findViewById(R.id.anxiety_switch);
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
        iSwitch = view.findViewById(R.id.irritation_switch);
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
        eSwitch = view.findViewById(R.id.energy_switch);
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

        adjustToCorrectSettings();

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

    private void adjustToCorrectSettings() {
        String s = fileHandler.read(getContext(),"Settings.txt");
        AnalyzerConverter.getInstance().convert(s);
        AnalyzerSettingNumber hSettings = (AnalyzerSettingNumber) AnalyzerConverter.getInstance().getAnalyzerSettings(2);
        if(hSettings != null) {
            hSwitch.setChecked(true);
            hTime.setText(String.valueOf(hSettings.getTimeFrame()));
            hLower.setText(String.valueOf(hSettings.getLowerLimit()));
            hUpper.setText(String.valueOf(hSettings.getUpperLimit()));
        }

        AnalyzerSettingNumber dSettings = (AnalyzerSettingNumber) AnalyzerConverter.getInstance().getAnalyzerSettings(3);
        if(dSettings != null) {
            dSwitch.setChecked(true);
            dTime.setText(String.valueOf(dSettings.getTimeFrame()));
            dLower.setText(String.valueOf(dSettings.getLowerLimit()));
            dUpper.setText(String.valueOf(dSettings.getUpperLimit()));
        }

        AnalyzerSettingNumber aSettings = (AnalyzerSettingNumber) AnalyzerConverter.getInstance().getAnalyzerSettings(4);
        if(aSettings != null) {
            aSwitch.setChecked(true);
            aTime.setText(String.valueOf(aSettings.getTimeFrame()));
            aLower.setText(String.valueOf(aSettings.getLowerLimit()));
            aUpper.setText(String.valueOf(aSettings.getUpperLimit()));
        }

        AnalyzerSettingNumber iSettings = (AnalyzerSettingNumber) AnalyzerConverter.getInstance().getAnalyzerSettings(8);
        if(iSettings != null) {
            iSwitch.setChecked(true);
            iTime.setText(String.valueOf(iSettings.getTimeFrame()));
            iLower.setText(String.valueOf(iSettings.getLowerLimit()));
            iUpper.setText(String.valueOf(iSettings.getUpperLimit()));
        }

        AnalyzerSettingNumber eSettings = (AnalyzerSettingNumber) AnalyzerConverter.getInstance().getAnalyzerSettings(1);
        if(eSettings != null) {
            eSwitch.setChecked(true);
            eTime.setText(String.valueOf(eSettings.getTimeFrame()));
            eLower.setText(String.valueOf(eSettings.getLowerLimit()));
            eUpper.setText(String.valueOf(eSettings.getUpperLimit()));
        }

    }
}