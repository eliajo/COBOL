package com.example.agileproject.Model;

/**
 * Settings for analyzing answers with the data type int
 * @author William Hugo
 */
public class SettingNotificationReminder implements AnalyzerSettable, Storable {
    private int id;
    private final int type;

    private int hour;
    private int minute;

    public SettingNotificationReminder(int id, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;

        this.id = id;
        this.type = 3;
    }

    @Override
    public String getInfoToWrite() {
        return id + "###---###---###" + type + "###---###---###" + hour + "###---###---###" + minute;
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
        return 0;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
