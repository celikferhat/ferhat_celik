package com.secpisir.secpisir;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    Button giris;
    EditText e_mail,sifre;
    public static final String kAdi = "nameKey";
    private YönetimSistemi yönetimSistemi;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        giris = (Button)findViewById(R.id.button_giris_yap);
        e_mail = (EditText)findViewById(R.id.edit_kullaniciadi);
        sifre = (EditText)findViewById(R.id.edit_sifre);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        sp.edit().putBoolean("logged",true).apply();
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yönetimSistemi = new YönetimSistemi();
                yönetimSistemi.setKullaniciInputStream(getResources().openRawResource(R.raw.kullanici));
                yönetimSistemi.setYemekInputStream(getResources().openRawResource(R.raw.yemek));

               new UpdateTask().execute();
            }
        });
    }




    public void onLoginButtonClick(View view){
        Intent intent = new Intent(this, MainScreen.class);

        startActivity(intent);
    }

    public void onRegisterButtonClick(View view){
        //setContentView(R.layout.activity_kayit);
        Intent intent = new Intent(this, KayitActivity.class);
        startActivity(intent);
    }

    public void onSideBarClick(View view){ view.requestFocus();}

    /* ------Menu Onclicks------- */
    public void menudenKaralisteye(MenuItem item) {
        Intent intent = new Intent(this, Karaliste.class);
        startActivity(intent);
    }
    public void menudenFavorilere(MenuItem item) {
        Intent intent = new Intent(this, Favoriler.class);
        startActivity(intent);
    }
    public void menudenTarifEklemeye(MenuItem item){
        Intent intent = new Intent(this, TarifEkle.class);
        startActivity(intent);
    }
    public void menudenGecmise (MenuItem item) {
        Intent intent = new Intent(this, Gecmis.class);
        startActivity(intent);
    }
    /* ------------- */

    private class UpdateTask extends AsyncTask<String, String,String> {


        private boolean login = false;
        protected String doInBackground(String... urls) {

            try
            {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_ferhat?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat1", "FerhaT2018");

                Statement st = conn.createStatement();
                // note that i'm leaving "date_created" out of this insert statement


                String query = "SELECT * FROM kullanici_bilgileri";

                ResultSet rs = st.executeQuery(query);

                while (rs.next()){
                    if(rs.getString("email").equals(e_mail.getText().toString()) )
                        if(rs.getString("password").equals(sifre.getText().toString()))
                        {
                            login=true;

                            System.out.println("Giriş başarılı");

                            String _favoriler = "",_karaliste = "",_gecmis="";

                            if(rs.getString("favoriler") != null)
                                _favoriler = rs.getString("favoriler");

                            if(rs.getString("kara_liste") != null)
                                _karaliste = rs.getString("kara_liste");


                            if(rs.getString("gecmis") != null)
                                _gecmis = rs.getString("gecmis");





                            yönetimSistemi.setCurrentKullanici(rs.getString("email"),_favoriler,_karaliste,_gecmis);

                            Intent intent = new Intent(LoginActivity.this,MainScreen.class);





                            SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = sharedpref.edit();
                            editor.putInt("user_id", Integer.valueOf( rs.getString("user_id") ));

                            editor.commit();


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


                        Toast.makeText(LoginActivity.this, value, Toast.LENGTH_SHORT).show();



                }
            }
        }



    }






}
