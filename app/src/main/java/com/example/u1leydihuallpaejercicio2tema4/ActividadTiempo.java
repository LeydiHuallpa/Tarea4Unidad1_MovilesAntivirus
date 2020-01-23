package com.example.u1leydihuallpaejercicio2tema4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class ActividadTiempo extends Activity {

    private static final int ID_NOTIFICACION_CREAR = 1;
    public static final String NOTIFICATION_CHANNEL_ID = "1000";
    public static final String NOTIFICATION_CHANNEL_NAME = "ANTIVIRUS";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_actividad_tiempo);
        finish();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(ActividadTiempo.this, Antivirus.class));
        } else {
            startService(new Intent(ActividadTiempo.this,
                    Antivirus.class));
        }


        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    public void run() {
                        startActivity(new Intent(ActividadTiempo.this, ServicioMusica.class));

                    }
                }, 5000L);


    }
    @Override public void onDestroy() {
        super.onDestroy();
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_NOTIFICACION_CREAR);
    }
}
