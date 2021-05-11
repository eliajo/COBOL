package com.example.agileproject.Model;

/**
 * @author William Hugo
 */
public class AnalyzerSettingBoolean implements AnalyzerSettable, Storable {
    private int id;
    private final int type;

    private boolean warningSign;

    public AnalyzerSettingBoolean(int id) {
        this.id = id;
        this.type = 2;
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
