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
                System.out.println("Text");
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

        //TODO a function which fetches upper and lower limits from settings
        int upper = 10; //Change to fetched value
        int lower = 0;  //Change to fetched value

        boolean sendWarning = true;
        for(NumberAnswer a : numberAnswers) {
            if(upper >= a.getData() && lower <= a.getData()) {
                sendWarning = false;
            }
        }

        if(sendWarning) {
            //TODO send warning notification
            System.out.println("Warning");
        }
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
