package com.example.agileproject.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.agileproject.Model.AnalyzerSettable;
import com.example.agileproject.Model.SettingNotificationReminder;
import com.example.agileproject.R;

public class MedicineReminderReceiver extends BroadcastReceiver {
    private static final int notificationID =6000;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.println(Log.DEBUG, "Rev", "Recieved");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Medicine")
                .setSmallIcon(R.drawable.ic_baseline_person_24)
                .setContentText("Glöm inte att ta din medicin idag!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationID, builder.build());
        FileHandler fileHandler = new FileHandler();
        String readData = fileHandler.read(context,"Settings.txt");
        AnalyzerConverter.getInstance().convert(readData);
        SettingNotificationReminder medicineSettings= (SettingNotificationReminder) AnalyzerConverter.getInstance().getAnalyzerSettings(101);
        AlarmHandler alarmHandler = new AlarmHandler();
        alarmHandler.setAlarm(context,medicineSettings.getHour(),medicineSettings.getMinute(),AlarmHandler.MEDICINE_TYPE,true);
    }
}
