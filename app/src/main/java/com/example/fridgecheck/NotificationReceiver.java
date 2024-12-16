package com.example.fridgecheck;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String ingredientName = intent.getStringExtra("ingredient_name");

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder builder = notificationHelper.getChannelNotification(
                "Expiration Reminder",
                "The ingredient \"" + ingredientName + "\" is expiring soon!"
        );

        // Use a unique ID based on the ingredient's name
        int notificationId = ingredientName != null ? ingredientName.hashCode() : 0;

        notificationHelper.notify(notificationId, builder);
    }
}






