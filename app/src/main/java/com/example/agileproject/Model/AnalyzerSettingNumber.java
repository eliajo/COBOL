package com.example.agileproject.Model;

/**
 * @author William Hugo
 */
public class AnalyzerSettingNumber implements AnalyzerSettable, Storable {
    private int id;
    private final int type;

    private int upperValue;
    private int lowerValue;

    public AnalyzerSettingNumber(int id) {
        this.id = id;
        this.type = 1;
    }

    @Override
    public String getInfoToWrite() {
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return type;
    }
}
