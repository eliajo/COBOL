package com.example.agileproject.Model;

/**
 * Class that acts as a data storage for a question. Might be useful for View related checks.
 * @author elias
 */
public class Question {

    private int id;

    private String question;

    private boolean obligatory;

    public enum  QuestionType {MULTIPLECHOICE,TEXT,SINGLECHOICE}

    private QuestionType questionType;

    public Question(int id, String question, boolean obligatory, QuestionType questionType){
        this.id = id;
        this.question=question;
        this.obligatory=obligatory;
        this.questionType=questionType;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isObligatory() {
        return obligatory;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
