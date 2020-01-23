package com.example.u1leydihuallpaejercicio2tema4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.text.Html;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class Antivirus extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "1000";
    public static final String NOTIFICATION_CHANNEL_NAME = "Aantivirus";

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {

        NotificationCompat.Builder notific = new NotificationCompat.Builder(this)
                        .setContentText(Html.fromHtml("<b><font color=\"#F62248\">Buscando virus</font></b>"))
                        .setSmallIcon(R.drawable.antivirus)
                .setContentText(Html.fromHtml("<b><font color=\"#CAC4C5\">Analizando</font></b> <u>búsqueda <i>exahustiva</i></u>"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.antivirus));

        PendingIntent intencionPendiente = PendingIntent.getActivity(this, 0, new Intent(this, Servicio.class), 0);
        notific.setContentIntent(intencionPendiente);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            NOTIFICATION_CHANNEL_ID,
                            NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_LOW);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(R.color.colorAccent);
            notificationManager.createNotificationChannel(notificationChannel);
            notific.setChannelId(NOTIFICATION_CHANNEL_ID);

        }
        startForeground(101,notific.build());
        return START_STICKY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
