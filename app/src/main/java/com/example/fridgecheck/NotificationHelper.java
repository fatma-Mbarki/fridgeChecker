package com.example.fridgecheck;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    private static final String CHANNEL_ID = "ingredient_tracker";
            private final Context context;

            public NotificationHelper(Context context) {
                this.context = context;
                createNotificationChannel();
            }

            private void createNotificationChannel() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            CHANNEL_ID, "Ingredient Notifications", NotificationManager.IMPORTANCE_HIGH);
                    channel.setDescription("Notifications for ingredient expiration");

                    NotificationManager manager = context.getSystemService(NotificationManager.class);
                    if (manager != null) {
                        manager.createNotificationChannel(channel);
                    }
                }
            }
        public NotificationCompat.Builder getChannelNotification(String title, String message) {
            return new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_notification) // Ensure this drawable exists
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true); // Dismiss notification when tapped
        }

        public void notify(int id, NotificationCompat.Builder builder) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.notify(id, builder.build());
            }
        }
    }