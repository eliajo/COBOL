package com.example.agileproject.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Class that handles the setting of alarms for the notifications
 * @author Elias Johansson
 */
public class AlarmHandler {
    public static final int REMINDER_TYPE = 0;
    public static final int MEDICINE_TYPE = 1;

    /**
     * Sets the alarm
     * @param context the context of the app
     * @param hour hour of the day
     * @param minute minute of the day
     * @param type the type of notification
     * @param alreadyTriggered Has the alarm already been triggered today?
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarm(Context context, int hour, int minute, int type,boolean alreadyTriggered) {
        Intent intent;
        if (type == REMINDER_TYPE) {
             intent = new Intent(context, QuizReminderReceiver.class);
        }
        else {
            intent = new Intent(context,MedicineReminderReceiver.class);
        }
        //Might need to double check flags for different types of notifications. Right now only one Pending indent be created for one intend...
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, type, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        if (alreadyTriggered){
            calendar.add(Calendar.DATE,1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.setTimeZone(TimeZone.getDefault());


        long time = calendar.getTimeInMillis();
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    /**
     * Clears a specific alarm
     * @param context the app context
     * @param type the type of alarm to be cleared
     */
    public void clearAlarm(Context context, int type){
        Intent intent;
        if (type == REMINDER_TYPE) {
            intent = new Intent(context, QuizReminderReceiver.class);
        }
        else {
            intent = new Intent(context,MedicineReminderReceiver.class);
        }
        //Might need to double check flags for different types of notifications. Right now only one Pending indent be created for one intend...
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, type, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }
}
