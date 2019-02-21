package com.example.asus.msql_park;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.LogRecord;


public class notiservice extends Service {

    private final static String TAG = "notiservice";
    private  String _plaka;

    private Handler mHandler;
    private int mInterval = 5000;
    private String sikayet__id=".";
    private String last_sikayet_id="";
    private static boolean alert=false;
    private String userid;






    @Override
    public void onCreate() {
        super.onCreate();




        new UpdateTask().execute();
        mHandler = new Handler();
        startRepeatingTask();
        Log.i(TAG, "Starting timer...");



    }

    @Override
    public void onDestroy() {

        stopRepeatingTask();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {


                new notiservice.UpdateTask().execute();

            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent != null)
        {
            userid=intent.getExtras().get("userid").toString();
            _plaka=intent.getExtras().get("plaka").toString();



        }
        else {

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String savedString = sharedPref.getString("userid","Kayıt Yok");
            _plaka = sharedPref.getString("plaka","Kayıt Yok");
            userid = savedString;

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
        builder.setContentTitle("Hatalı Park Yaptınız!");
        builder.setContentText(plaka);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Uri alarmSound = Uri.parse("android.resource://com.example.asus.msql_park/raw/siren");
        builder.setSound(alarmSound);
        Intent intent = new Intent(this,bildirim.class);
        intent.putExtra("userid",userid);
        intent.putExtra("plaka",plaka);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentIntent(contentIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        boolean var=false;




        for(StatusBarNotification ntf : mNotifyMgr.getActiveNotifications()  )
        {
            if(ntf.getId()==  001 )
                var=true;


        }
        if(!var){



            if(last_sikayet_id.equals(sikayet__id)){
                if(!alert){
                    mNotifyMgr.notify(001, builder.build());
                    alert=true;

                }



            }

            if(!last_sikayet_id.equals(sikayet__id)){

                last_sikayet_id=sikayet__id;
                alert=false;

            }








        }




    }




    private class UpdateTask extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {

            try {

                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_deneme?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat", "FerhaT123");

                Statement st = conn.createStatement();
                String query = "SELECT * FROM sikayetler";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){

                    if (rs.getString("plaka").equals(  _plaka   )){

                        if(rs.getInt("durum") == 0){
                            publishProgress(rs.getString("plaka"),rs.getString("sikayet_id"));



                        }

                    }
                }


            }

            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }




            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            int a=1;

            if(values != null) {
                for (String st : values) {
                    if (a == 1){
                        notification(st);


                    }
                    if (a == 2)
                        sikayet__id = st;

                    a++;
                }
            }


        }


    }





}
