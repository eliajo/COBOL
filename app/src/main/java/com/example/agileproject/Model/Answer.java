package com.example.agileproject.Model;

/**
 * A class that stores and keep tracks of answers,
 * the question it was an answer for by an id, and the date the answer was made.
 * @author William Hugo, Elias Johansson
 */
public class Answer {
    private int num;
    private String text;
    private boolean bool;
    private int questionId;
    private String date;
    private final int type;

    public Answer(String text, int questionId, String date) { // Used if the question is a String
        this.text = text;
        this.questionId = questionId;
        this.date = date;
        this.type = 0;
    }

    public Answer(int num, int questionId, String date) { // Used if the question is an int
        this.num = num;
        this.questionId = questionId;
        this.date = date;
        this.type = 1;
    }

    public Answer(boolean bool, int questionId, String date) { // Used if the question is a boolean
        this.bool = bool;
        this.questionId = questionId;
        this.date = date;
        this.type = 2;
    }

    /**
     * Turns answer's different parameters into a String.
     * Used to format the String so that the answer can be saved in a text file.
     * @return String containing all parameters of an answer
     */
    public String getData() { // Turns the question into
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