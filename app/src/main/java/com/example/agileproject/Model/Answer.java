package com.example.agileproject.Model;

public class Answer {
    private int num;
    private String text;
    private boolean bool;
    private int questionId;
    private final int type;

    public Answer(String text, int questionId) {
        this.text = text;
        this.questionId = questionId;
        this.type = 0;
    }

    public Answer(int num, int questionId) {
        this.num = num;
        this.questionId = questionId;
        this.type = 1;
    }

    public Answer(boolean bool, int questionId) {
        this.bool = bool;
        this.questionId = questionId;
        this.type = 2;
    }

    public String getData() {
        if (type == 0) {
            return questionId + "$$$$$$$" + text;
        }
        if (type == 1) {
            return questionId + "$$$$$$$" + num;
        }
        if (type == 2) {
            return questionId + "$$$$$$$" + bool;
        }
        throw new IllegalArgumentException("Objected failed to be created properly");
    }
}