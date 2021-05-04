package com.example.agileproject.Model;
/**
 * Class for answers of type text
 * @author Elias Johansson William Hugo
 */

public class TextAnswer implements Answerable, Storable {
    private String text;
    private int questionId;
    private String date;
    private final int type;


    public TextAnswer(String text, int questionId, String date) {
        this.text = text;
        this.questionId = questionId;
        this.date = date;
        this.type = 0;
    }
    @Override
    public String getInfoToWrite() {
        return text + "###---###---###" + questionId + "###---###---###" + date + "###---###---###" + type;
    }

    @Override
    public String getData() {
        return text;
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
