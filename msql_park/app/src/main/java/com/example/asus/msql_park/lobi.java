package com.example.asus.msql_park;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class lobi extends AppCompatActivity {
private String userid;
private String plaka;
private String sikayet__id=".";
private String last_sikayet_id="";
private static boolean alert=false;

    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobi);
        Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_SHORT).show();

        userid =  getIntent().getExtras().get("userid").toString();
        plaka  =  getIntent().getExtras().get("plaka").toString();

        new UpdateTask().execute();
        mHandler = new Handler();
        startRepeatingTask();
/***************************************************************/



/****************************************************************/


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();

    }




    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                new UpdateTask().execute();
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

    public void dots(View view) {
        dot_fragment dot_fragment = new dot_fragment();

        dot_fragment.show(getSupportFragmentManager(), "a");
    }

    public void anaekranayolla(View view) {
        Intent intent = new Intent(lobi.this,sikayet.class);
        intent.putExtra("userid",userid);
        startActivity(intent);


    }


    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putString("userid",userid);
        editor.putString("plaka",plaka);

        editor.commit();

        final Intent intent2 = new Intent(getApplicationContext(),notiservice.class);
        intent2.putExtra("userid",userid);
        intent2.putExtra("plaka",plaka);
        startService(intent2);




    }

    public void bildirimeyolla(View view) {
        Intent intent = new Intent(lobi.this,bildirim.class);
        intent.putExtra("userid",userid);
        intent.putExtra("plaka",plaka);
        startActivity(intent);


    }

    public void kullanicibilgilerineyolla(View view) {
    }

    public void destegeyolla(View view) {

        Intent intent = new Intent(lobi.this,remain_time.class);
        startActivity(intent);




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

                    if (rs.getString("plaka").equals(  getIntent().getExtras().get("plaka").toString()   )){

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
                    if (a == 1)
                        notification(st);
                    if (a == 2)
                        sikayet__id = st;

                    a++;
                }
            }


        }


    }


    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }
}
