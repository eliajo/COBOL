package com.example.agileproject.Model;

import java.util.List;

public class MultipleTextAnswer implements Answerable {
    private List<String> text;
    private double questionId;
    private String date;
    private final int type;

    public MultipleTextAnswer(List<String> text, double questionId, String date) {
        this.text = text;
        this.questionId = questionId;
        this.date = date;
        this.type = 3;

    }

    @Override
    public String getInfoToWrite() {

        return text + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public int getQuestionId() {
        return 0;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }
}
