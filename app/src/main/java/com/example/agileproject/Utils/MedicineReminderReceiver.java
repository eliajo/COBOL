package com.example.agileproject.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.agileproject.R;

public class MedicineReminderReceiver extends BroadcastReceiver {
    private static final int notificationID =6000;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.println(Log.DEBUG, "Rev", "Recieved");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Medicine")
                .setSmallIcon(R.drawable.ic_baseline_person_24)
                .setContentText("Glöm inte att ta din medicin idag!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationID, builder.build());
    }
}
