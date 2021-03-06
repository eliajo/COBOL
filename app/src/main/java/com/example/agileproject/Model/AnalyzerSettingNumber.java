package com.example.agileproject.Model;

/**
 * Settings for analyzing answers with the data type int
 * @author William Hugo
 */
public class AnalyzerSettingNumber implements AnalyzerSettable, Storable {
    private int id;
    private final int type;

    private int upperLimit;
    private int lowerLimit;
    private int timeFrame;

    public AnalyzerSettingNumber(int id, int upperLimit, int lowerLimit, int timeFrame) {
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.timeFrame = timeFrame;

        this.id = id;
        this.type = 1;
    }

    @Override
    public String getInfoToWrite() {
        return id + "###---###---###" + type + "###---###---###" + timeFrame + "###---###---###" + upperLimit + "###---###---###" + lowerLimit;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getTimeFrame() {
        return timeFrame;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }
}
