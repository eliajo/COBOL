package com.example.agileproject.Model;

/**
 * Class for answers of type boolean
 * @author Elias Johansson William Hugo
 */

public class BooleanAnswer implements Answerable {

    private boolean bool;
    private int questionId;
    private String date;
    private final int type;


    public BooleanAnswer(boolean bool, int questionId, String date) {
        this.bool = bool;
        this.questionId = questionId;
        this.date = date;
        this.type = 2;
    }

    @Override
    public String getInfoToWrite() {
        return bool + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
    }

    @Override
    public Boolean getData() {
        return bool;
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
