package com.example.agileproject.Model;

import com.github.mikephil.charting.data.Entry;

/**
 * Subclass of Entry that is used for graphing but also has questionId which is important for checking
 * what question the answers belong to.
 * @author Elias Johansson
 */
public class AnswerEntry extends Entry {
    private float x,y;
    private int questionId;

    public AnswerEntry(float x, float y, int questionId){
        this.x=x;
        this.y=y;
        this.questionId=questionId;
    }


    public int getQuestionId() {
        return questionId;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
