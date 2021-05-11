package com.example.agileproject.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Utils.AnswerConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author William Hugo
 */
public class AnswerDataAnalyzer {

    public AnswerDataAnalyzer() {}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void analyzeBalance(int id, int time) {
        List<Answerable> toAnalyze = timeFrameAdjust(id, time);

        switch (toAnalyze.get(0).getType()) {
            case 0: //The answer type is text
                System.out.println("Can't analyze text");
                break;
            case 1: //The answer type is int
                numberAnalyze(toAnalyze);
                break;
            case 2: //The answer type is boolean
                System.out.println("Boolean");
                break;
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
        } else if(belowLowerLimit(numberAnswers)) {
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
    private List<Answerable> timeFrameAdjust(int id, int time) {

        List<Answerable> allOfId = AnswerConverter.getInstance().getAnswersByQuestionID(id);
        List<Answerable> adjusted = new ArrayList<>();

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
