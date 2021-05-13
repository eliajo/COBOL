package com.example.agileproject.Utils;

import com.example.agileproject.Model.AnalyzerSettable;
import com.example.agileproject.Model.AnalyzerSettingBoolean;
import com.example.agileproject.Model.AnalyzerSettingNumber;

import java.util.HashMap;
import java.util.Map;

/**
 * Converts string from file into AnalyzerSetting objects in a List.
 * Settings will be gotten by id of the wanted answer to analyze.
 * @author William Hugo
 */
public class AnalyzerConverter {

    private Map<Integer, AnalyzerSettable> analyzerMap = new HashMap();

    private static AnalyzerConverter answerAnalyzerConverter;

    private AnalyzerConverter() {
        analyzerMap = new HashMap<>();
    }

    public static AnalyzerConverter getInstance() {
        if (answerAnalyzerConverter == null) {
            answerAnalyzerConverter = new AnalyzerConverter();
        }
        return answerAnalyzerConverter;
    }

    public void convert(String data) {
        analyzerMap.clear();
        if(data.length() == 0) {
            return;
        }

        String[] analyzers = data.split("@@@---@@@---@@@"); //Splitting up different analyzers for different questions

        for(String s : analyzers) {
            String[] settings = s.split("###---###---###"); //Splitting up the different settings of one analyzer
            switch (Integer.parseInt(settings[1])) { //This finds the type
                case 1: //Numbers
                    AnalyzerSettingNumber numberSetting = new AnalyzerSettingNumber(Integer.parseInt(settings[0]), Integer.parseInt(settings[1]), Integer.parseInt(settings[2]), Integer.parseInt(settings[3]));
                    analyzerMap.put(numberSetting.getId(), numberSetting);
                    break;
                case 2: //Booleans
                    AnalyzerSettingBoolean booleanSetting = new AnalyzerSettingBoolean(Integer.parseInt(settings[0]), Boolean.parseBoolean(settings[1]), Integer.parseInt(settings[2]));
                    analyzerMap.put(booleanSetting.getId(), booleanSetting);
                    break;
            }
        }
    }

    public AnalyzerSettable getAnalyzerSettings(int id) {
        if(analyzerMap.containsKey(id)) {
            if(analyzerMap.get(id).getType() == 1) {
                AnalyzerSettingNumber numberSetting = (AnalyzerSettingNumber) analyzerMap.get(id);
                return numberSetting;
            } else if(analyzerMap.get(id).getType() == 2 ) {
                AnalyzerSettingBoolean booleanSetting = (AnalyzerSettingBoolean) analyzerMap.get(id);
                return booleanSetting;
            }
        }
        return null; //TODO check if exception is needed
    }

}
