package com.example.u1leydihuallpaejercicio2tema4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;

public class Servicio extends Activity {

    private static final int ID_NOTIFICACION_CREAR = 1;
    public static final String NOTIFICATION_CHANNEL_ID = "1000";
    public static final String NOTIFICATION_CHANNEL_NAME = "ANTIVIRUS";
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_servicio);

        Intent resultIntent = new Intent(this, ActividadTiempo.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notific = new NotificationCompat.Builder(this)
                .setContentTitle(Html.fromHtml("<b>Antivirus</b>"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Presione iniciar para realizar búsqueda de virus")
                .addAction(android.R.drawable.ic_dialog_alert, "INICIAR", resultPendingIntent);

        PendingIntent intencionPendiente = PendingIntent.getActivity(
                this, 0, new Intent(this, Servicio.class), 0);
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
        notificationManager.notify(ID_NOTIFICACION_CREAR, notific.build());
        finish();


    }
}
