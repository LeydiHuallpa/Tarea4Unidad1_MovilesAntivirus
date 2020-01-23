package com.example.u1leydihuallpaejercicio2tema4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;

public class MainActivity extends AppCompatActivity {


    private static final int ID_NOTIFICACION_CREAR = 1;
    public static final String NOTIFICATION_CHANNEL_ID = "1000";
    public static final String NOTIFICATION_CHANNEL_NAME = "Antivirus";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent resultIntent = new Intent(this, ActividadTiempo.class);
        // creAR Intencion
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // obtener pending para la lista completo
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notific = new NotificationCompat.Builder(this)
                .setContentTitle(Html.fromHtml("<b>Antivirus</b>"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Presione INICIAR para realizar búsqueda de virus.")

                .addAction(android.R.drawable.ic_dialog_alert, "INICIAR", resultPendingIntent);

        // Para lanzar una actividad
        PendingIntent intencionPendiente = PendingIntent.getActivity(
                this, 0, new Intent(this, Servicio.class), 0);
        notific.setContentIntent(intencionPendiente);


        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //si es mayor a android oreo=8
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
