package com.example.asus.msql_park;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class BroadcastService extends Service {

    private final static String TAG = "BroadcastService";
    private String _plaka;
    private String sikayet_id;
    public static final String COUNTDOWN_BR = "com.example.asus.msql_park.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);

    CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");

        cdt = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);

                bi.putExtra("countdown", millisUntilFinished);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {

                Log.i(TAG, "Timer finished");
                cdt.cancel();
                notification(_plaka);
                BroadcastService.this.stopSelf();

            }
        };

        cdt.start();
    }

    @Override
    public void onDestroy() {

        cdt.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null)
        {

        _plaka=intent.getExtras().get("plaka").toString();
        sikayet_id=intent.getExtras().get("sikayet_id").toString();
        }else{
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            _plaka = sharedPref.getString("splaka","Kayıt Yok");
            sikayet_id = sharedPref.getString("sikayet_id","Kayıt Yok");

        }


        return super.onStartCommand(intent, flags, startId);



    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }




    private void notification(String plaka){



        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"personal notification");
        builder.setSmallIcon(R.drawable.ic_announcement);
        builder.setContentTitle("Araç aynı konumda mı ?");
        builder.setContentText(plaka);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Uri alarmSound = Uri.parse("android.resource://com.example.asus.msql_park/raw/siren");
        builder.setSound(alarmSound);
        Intent intent = new Intent(this,sonuc.class);

        intent.putExtra("plaka",plaka);
        intent.putExtra("sikayet_id",sikayet_id);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentIntent(contentIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);




                mNotifyMgr.notify(002, builder.build());





    }





}
