package com.example.agileproject.Model;

import java.time.LocalDate;

public class Answer {
    private int num;
    private String text;
    private boolean bool;
    private int questionId;
    private String date;
    private final int type;

    public Answer(String text, int questionId, String date) {
        this.text = text;
        this.questionId = questionId;
        this.date = date;
        this.type = 0;
    }

    public Answer(int num, int questionId, String date) {
        this.num = num;
        this.questionId = questionId;
        this.date = date;
        this.type = 1;
    }

    public Answer(boolean bool, int questionId, String date) {
        this.bool = bool;
        this.questionId = questionId;
        this.date = date;
        this.type = 2;
    }

    public String getData() {
        if (type == 0) {
            return text + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
        }
        if (type == 1) {
            return num + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
        }
        if (type == 2) {
            return bool + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
        }
        throw new IllegalArgumentException("Objected failed to be created properly");
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getDate() {
        return date;
    }
}