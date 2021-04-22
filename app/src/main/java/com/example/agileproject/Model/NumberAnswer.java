package com.example.agileproject.Model;

/**
 * Class for answers of type numbers
 * @author Elias Johansson William Hugo
 */
public class NumberAnswer implements Answerable {
    private int num;
    private int questionId;
    private String date;
    private final int type;


    public NumberAnswer(int num, int questionId, String date) {
        this.num = num;
        this.questionId = questionId;
        this.date = date;
        this.type = 1;
    }
    @Override
    public String getInfoToWrite() {
        return num + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
    }

    @Override
    public Integer getData() {
        return num;
    }
    @Override
    public int getQuestionId() {
        return questionId;
    }
    @Override
    public String getDate() {
        return date;
    }
}
