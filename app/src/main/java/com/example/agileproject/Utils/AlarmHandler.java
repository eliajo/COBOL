package com.example.agileproject.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class AlarmHandler {
    public static final int REMINDER_TYPE = 0;
    public static final int MEDICINE_TYPE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarm(Context context, int time, int type){
    Intent intent = new Intent(context,AlarmReceiver.class);
    //Might need to double check flags for different types of notifications. Right now only one Pending indent be created for one intend...
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,type,intent,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,time);


        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 10000,pendingIntent);
    }
}
