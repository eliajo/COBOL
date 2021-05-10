package com.example.agileproject.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.agileproject.R;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int notificationID =5000;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.println(Log.DEBUG, "Rev", "Recieved");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Test")
                .setSmallIcon(R.drawable.ic_baseline_person_24)
                .setContentText("Du har inte svarat på frågor idag!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationID, builder.build());

    }
}
