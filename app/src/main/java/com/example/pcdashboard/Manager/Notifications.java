package com.example.pcdashboard.Manager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.pcdashboard.R;

import javax.sql.DataSource;

public class Notifications {
    static final String CHANNEL_ID = "pc_dashboard_id";
    static final String CHANNEL_NAME = "pc_dasboard_name";

    public static void createChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription("Messaging");
            channel.setShowBadge(true);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
    }

//    public static void createNotification(Context context) {
//        NotificationCompat.Builder builder;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.logo)
//                    .setColor(context.getResources().getColor(R.color.colorOrange))
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setLargeIcon(avatar)
//                    .setPriority(PRIORITY_HIGH)
//                    .setVibrate(new long[]{500, 500})
//                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent);
//        } else {
//            builder = new NotificationCompat.Builder(context)
//                    .setSmallIcon(R.drawable.ic_belicoffee_small)
//                    .setColor(context.getResources().getColor(R.color.colorDarkCyan))
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setLargeIcon(avatar)
//                    .setPriority(PRIORITY_HIGH)
//                    .setVibrate(new long[]{500, 500})
//                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent);
//        }
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(notificationId, builder.build());
//    }
}
