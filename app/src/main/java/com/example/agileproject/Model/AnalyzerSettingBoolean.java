package com.example.agileproject.Model;

/**
 * Settings for analyzing answers with the data type boolean
 * @author William Hugo
 */
public class AnalyzerSettingBoolean implements AnalyzerSettable, Storable {
    private int id;
    private final int type;

    private boolean warningSign;
    private int timeFrame;

    public AnalyzerSettingBoolean(int id, boolean warningSign, int timeFrame) {
        this.warningSign = warningSign;
        this.timeFrame = timeFrame;

        this.id = id;
        this.type = 2;
    }

    @Override
    public String getInfoToWrite() {
        return id + "###---###---###" + type + "###---###---###" + timeFrame + "###---###---###" + warningSign;
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
