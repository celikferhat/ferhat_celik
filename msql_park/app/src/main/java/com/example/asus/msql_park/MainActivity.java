package com.example.asus.msql_park;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    protected static boolean login=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    public void logintoregisterbutton(View view) {

        Intent intent = new Intent(MainActivity.this,register.class);
        startActivity(intent);

    }

    public void logintolobi(View view) {


        new UpdateTask().execute();



    }



    private class UpdateTask extends AsyncTask<String, String,String> {
        EditText eposta = findViewById(R.id.loginemail);
        EditText parola = findViewById(R.id.loginparola);
        protected String doInBackground(String... urls) {

            try
            {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_deneme?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat", "FerhaT123");

                Statement st = conn.createStatement();
                // note that i'm leaving "date_created" out of this insert statement


                String query = "SELECT * FROM kullanici";

                ResultSet rs = st.executeQuery(query);

                while (rs.next()){
                    if(rs.getString("user_eposta").equals(eposta.getText().toString()) )
                        if(rs.getString("user_pass").equals(parola.getText().toString()))
                        {
                           login=true;

                           System.out.println("Giriş başarılı");
                            Intent intent = new Intent(MainActivity.this,lobi.class);
                            intent.putExtra("userid",rs.getString("user_id"));
                            intent.putExtra("plaka",rs.getString("user_plaka"));
                            startActivity(intent);
                            finish();
                        }else {

                           System.out.println("yanlış");
                        }
                }

                if(!login)
                    publishProgress("E-posta ve ya parola hatalı");


                conn.close();


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
            if (values != null) {
                for (String value : values) {
                    // shows a toast for every value we get
                    Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                }
            }
        }



    }






}
