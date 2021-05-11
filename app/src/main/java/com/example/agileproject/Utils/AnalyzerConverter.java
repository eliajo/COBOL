package com.example.agileproject.Utils;

import com.example.agileproject.Model.AnalyzerSettable;

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
}
