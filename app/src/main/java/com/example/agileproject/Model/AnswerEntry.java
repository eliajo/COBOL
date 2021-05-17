package com.example.agileproject.Model;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;

/**
 * Subclass of Entry that is used for graphing but also has questionId which is important for checking
 * what question the answers belong to.
 * @author Elias Johansson
 */
public class AnswerEntry extends PieEntry {
    private float x,y;
    private int questionId;
    private String dateAdded;
    private String label;

    public AnswerEntry(float x, float y, int questionId,String dateAdded){
        super(y,x);
        this.x=x;
        this.y=y;
        this.questionId=questionId;
        this.dateAdded = dateAdded;
    }
    public AnswerEntry(String label, float y, int questionId,String dateAdded){
        super(y,label);

        this.label = label;
        this.y=y;
        this.questionId=questionId;
        this.dateAdded = dateAdded;
    }


    public int getQuestionId() {
        return questionId;
    }

    public String getDateAdded(){
        return dateAdded;
    }
    @Override
    public float getX() {
        return x;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public float getY() {
        return y;
    }
}
