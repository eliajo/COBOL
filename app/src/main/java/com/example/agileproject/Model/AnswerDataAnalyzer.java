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
    public void analyzeBalance(int id, int time, int upper, int lower) {
        List<Answerable> toAnalyze = timeFrameAdjust(id, time);
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
