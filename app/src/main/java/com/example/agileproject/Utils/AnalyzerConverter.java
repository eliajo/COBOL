package com.example.agileproject.Utils;

import com.example.agileproject.Model.AnalyzerSettable;
import com.example.agileproject.Model.AnalyzerSettingBoolean;
import com.example.agileproject.Model.AnalyzerSettingNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts string from file into AnalyzerSetting objects in a List.
 * Settings will be gotten by id of the wanted answer to analyze.
 * @author William Hugo
 */
public class AnalyzerConverter {

    private List<AnalyzerSettable> analyzerList;

    private static AnalyzerConverter answerAnalyzerConverter;

    private AnalyzerConverter() {
        analyzerList = new ArrayList<>();
    }

    public static AnalyzerConverter getInstance() {
        if (answerAnalyzerConverter == null) {
            answerAnalyzerConverter = new AnalyzerConverter();
        }
        return answerAnalyzerConverter;
    }

    public void convert(String data) {
        analyzerList.clear();
        if(data.length() == 0) {
            return;
        }

        String[] analyzers = data.split("@@@---@@@---@@@"); //Splitting up different analyzers for different questions

        for(String s : analyzers) {
            String[] settings = s.split("###---###---###"); //Splitting up the different settings of one analyzer
            switch (Integer.parseInt(settings[1])) { //This finds the type
                case 1: //Numbers
                    AnalyzerSettingNumber intSetting = new AnalyzerSettingNumber(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
                    analyzerList.add(intSetting);
                    break;
                case 2: //Booleans
                    AnalyzerSettingBoolean booleanSetting = new AnalyzerSettingBoolean(Integer.parseInt(settings[0]), Boolean.parseBoolean(settings[1]));
                    analyzerList.add(booleanSetting);
                    break;
            }
        }
    }
    
}
