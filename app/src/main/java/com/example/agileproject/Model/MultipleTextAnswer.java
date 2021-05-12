package com.example.agileproject.Model;

import java.util.ArrayList;
import java.util.List;

public class MultipleTextAnswer implements Answerable {
    private List<String> text;
    private int questionId;
    private String date;
    private final int type;

    public MultipleTextAnswer(List<String> text, int questionId, String date) {
        this.text = text;
        this.questionId = questionId;
        this.date = date;
        this.type = 3;

    }

    @Override
    public String getInfoToWrite() {
        StringBuilder sb = new StringBuilder();
        for (String s:text) {
            sb.append(s);
            sb.append("§§§---§§§---§§§");
        }

        return sb.toString() + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
    }

    @Override
    public List<String> getData() {
        List<String> safeCopy = new ArrayList<>();
        safeCopy.addAll(text);
        return safeCopy;
    }

    @Override
    public int getQuestionId() {
        return questionId;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public int getType() {
        return type;
    }
}
