package com.example.agileproject.Utils;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.agileproject.ControlView.MainActivity;
import com.example.agileproject.R;

public class QuizReminderReceiver extends BroadcastReceiver {
    private static final int notificationID =5000;


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent clickIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,clickIntent,0);
        Log.println(Log.DEBUG, "Rev", "Recieved");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Quiz")
                .setSmallIcon(R.drawable.ic_baseline_person_24)
                .setContentText("Du har inte svarat på frågor idag!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationID, builder.build());
        AlarmHandler alarmHandler = new AlarmHandler();
        //alarmHandler.setAlarm();

    }
}
