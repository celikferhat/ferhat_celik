package com.example.asus.msql_park;

import android.app.NotificationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class sonuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mNotificationManager.cancel(002);
    }

    public void hayir(View view) {

        new UpdateTask().execute();
        finish();


    }

    public void evet(View view) {

        new UpdateTask2().execute();
        finish();




    }


    private class UpdateTask extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {




            try {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_deneme?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat", "FerhaT123");

                Statement st = conn.createStatement();
                String query = "SELECT * FROM sikayetler";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){

                    if (rs.getString("plaka").equals(  getIntent().getExtras().get("plaka").toString()   ) ) {

                        if( rs.getString("sikayet_id").equals(getIntent().getExtras().get("sikayet_id").toString()) ){

                            st.executeUpdate("UPDATE sikayetler SET durum=1 WHERE plaka='"+rs.getString("plaka")+"'"+ " AND sikayet_id='"+getIntent().getExtras().get("sikayet_id").toString()+"'" );


                        }

                    }
                }





                conn.close();


                finish();


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

        }



    }

    private class UpdateTask2 extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {




            try {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_deneme?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat", "FerhaT123");

                Statement st = conn.createStatement();
                String query = "SELECT * FROM sikayetler";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){

                    if (rs.getString("plaka").equals(  getIntent().getExtras().get("plaka").toString()   )){

                        if( rs.getString("sikayet_id").equals(getIntent().getExtras().get("sikayet_id").toString()) ){

                            st.executeUpdate("UPDATE sikayetler SET durum=2 WHERE plaka='"+rs.getString("plaka")+"'" + " AND sikayet_id='"+getIntent().getExtras().get("sikayet_id").toString()+"'");


                        }
                    }
                }





                conn.close();


                finish();


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

        }



    }



}
