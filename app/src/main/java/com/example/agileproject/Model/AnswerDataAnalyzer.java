package com.example.agileproject.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Utils.AnalyzerConverter;
import com.example.agileproject.Utils.AnswerConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that analyzes data by getting the settings of what factors to analyze by
 * from the AnalyzerConverter class. Calls for notifications to be sent if needed.
 * @Author William Hugo
 */
public class AnswerDataAnalyzer {

    boolean[] warning = {false, false};

    public AnswerDataAnalyzer() {}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean[] analyzeBalance(int id) {
        List<Answerable> toAnalyze = timeFrameAdjust(id);
        AnalyzerSettable settings = AnalyzerConverter.getInstance().getAnalyzerSettings(id);

        if(settings != null) {
            switch (settings.getType()) {
                case 0: //The answer type is text
                    System.out.println("Can't analyze text");
                    break;
                case 1: //The answer type is int
                    AnalyzerSettingNumber numberSettings = (AnalyzerSettingNumber) AnalyzerConverter.getInstance().getAnalyzerSettings(id);
                    numberAnalyze(toAnalyze, numberSettings);
                    break;
                case 2: //The answer type is boolean
                    AnalyzerSettingBoolean booleanSettings = (AnalyzerSettingBoolean) AnalyzerConverter.getInstance().getAnalyzerSettings(id);
                    booleanAnalyze(toAnalyze, booleanSettings);
                    break;
                default:
                    System.out.println("No type represented by this id");
                    break;
            }
        }
        return warning;
    }

    private void booleanAnalyze(List<Answerable> toAnalyze, AnalyzerSettingBoolean settings) {
        List<BooleanAnswer> booleanAnswers = new ArrayList<>();
        for (Answerable a : toAnalyze) {
            booleanAnswers.add((BooleanAnswer) a);
        }

        if(booleanWarning(booleanAnswers, settings)) {
            warning[0] = true;
            warning[1] = true;
        }
    }

    private boolean booleanWarning(List<BooleanAnswer> toAnalyze, AnalyzerSettingBoolean settings) {
        boolean sendWarning = true;
        for(BooleanAnswer a : toAnalyze) {
            if(settings.getWarningSign() != a.getData()) {
                sendWarning = false;
            }
        }
        return sendWarning;
    }

    private void numberAnalyze(List<Answerable> toAnalyze, AnalyzerSettingNumber settings) {
        List<NumberAnswer> numberAnswers = new ArrayList<>();
        for (Answerable a : toAnalyze) {
            numberAnswers.add((NumberAnswer) a);
        }

        if(aboveUpperLimit(numberAnswers, settings)) {
            warning[1] = true;
        }else if(belowLowerLimit(numberAnswers, settings)) {
            warning[0] = true;
        }
    }

    private boolean aboveUpperLimit(List<NumberAnswer> toAnalyze, AnalyzerSettingNumber settings) {
        boolean sendWarning = true;
        for(NumberAnswer a : toAnalyze) { //check upper bound
            if(settings.getUpperLimit() >= a.getData()) {
                sendWarning = false;
            }
        }
        return sendWarning;
    }

    private boolean belowLowerLimit(List<NumberAnswer> toAnalyze, AnalyzerSettingNumber settings) {
        boolean sendWarning = true;
        for(NumberAnswer a : toAnalyze) { //check lower bound
            if(settings.getLowerLimit() <= a.getData()) {
                sendWarning = false;
            }
        }
        return sendWarning;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Answerable> timeFrameAdjust(int id) {

        List<Answerable> allOfId = AnswerConverter.getInstance().getAnswersByQuestionID(id);
        List<Answerable> adjusted = new ArrayList<>();

        AnalyzerSettable settings = AnalyzerConverter.getInstance().getAnalyzerSettings(id);
        int time = settings.getTimeFrame();

        for(int i = 0; i > time; i++) {
            for (Answerable a : allOfId) {
                if(a.getDate().equals(LocalDate.now().minusDays(time).toString())) {
                    adjusted.add(a);
                }
            }
        }

        return adjusted;
    }

}
