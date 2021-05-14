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

    public AnswerDataAnalyzer() {}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void analyzeBalance(int id) {
        List<Answerable> toAnalyze = timeFrameAdjust(id);
        AnalyzerSettable settings = AnalyzerConverter.getInstance().getAnalyzerSettings(id);

        if(settings != null) {
            switch (settings.getType()) {
                case 0: //The answer type is text
                    System.out.println("Can't analyze text");
                    break;
                case 1: //The answer type is int
                    numberAnalyze(toAnalyze);
                    break;
                case 2: //The answer type is boolean
                    System.out.println("Boolean"); //TODO add functions for boolean to call here
                    break;
                default:
                    System.out.println("No type represented by this id");
                    break;
            }
        }
    }

    private void numberAnalyze(List<Answerable> toAnalyze) {
        List<NumberAnswer> numberAnswers = new ArrayList<>();
        for (Answerable a : toAnalyze) {
            numberAnswers.add((NumberAnswer) a);
        }

        if(aboveUpperLimit(numberAnswers)) {
            //TODO send warning notification for high value
            System.out.println("Warning, value too high");//Test line, remove later
        }else if(belowLowerLimit(numberAnswers)) {
            //TODO send warning notification for low value
            System.out.println("Warning, value too low");//Test line, remove later
        }
    }

    private boolean aboveUpperLimit(List<NumberAnswer> toAnalyze) {
        boolean sendWarning = true;
        for(NumberAnswer a : toAnalyze) { //check upper bound
            if(/**upper >= a.getData()*/sendWarning) { //TODO method to get value from other place
                sendWarning = false;
            }
        }
        return sendWarning;
    }

    private boolean belowLowerLimit(List<NumberAnswer> toAnalyze) {
        boolean sendWarning = true;
        for(NumberAnswer a : toAnalyze) { //check lower bound
            if(/**lower <= a.getData()*/ sendWarning) { //TODO method to get value from other place
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
