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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.pcdashboard.R;

import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

public class NotificationsUtils {
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

    public static void createNotification(Context context, String title, String name, String content, Bitmap avatar) {
        NotificationCompat.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(title)
                    .setContentText(name != null ? name + " : " + content : content)
                    .setLargeIcon(avatar)
                    .setPriority(PRIORITY_HIGH)
                    .setVibrate(new long[]{500, 500})
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true);
        } else {
            builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(title)
                    .setContentText(name != null ? name + " : " + content : content)
                    .setLargeIcon(avatar)
                    .setPriority(PRIORITY_HIGH)
                    .setVibrate(new long[]{500, 500})
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true);
        }
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(00010001, builder.build());
    }

    public static void getNotificationAvatar(final Context context, final String title, final String name, final String content, String avatar) {
        if (avatar != null) {
            Glide.with(context)
                    .asBitmap().load(Uri.parse(avatar))
                    .listener(new RequestListener<Bitmap>() {
                                  @Override
                                  public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                      createNotification(context, title, name, content, BitmapFactory.decodeResource(context.getResources(), R.drawable.logo));
                                      return false;
                                  }

                                  @Override
                                  public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                      createNotification(context, title, name, content, resource);
                                      return false;
                                  }
                              }
                    ).submit();
        } else
            createNotification(context, title, name, content, BitmapFactory.decodeResource(context.getResources(), R.drawable.logo));
    }
}
