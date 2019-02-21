package com.secpisir.secpisir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class KayitActivity extends Activity {

    Button kayit;
    EditText isim_edit,soyad_edit,mail_edit,sifre_edit,sifre_tekrar_edit;
    //PostClass post = new PostClass();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        kayit = findViewById(R.id.button_kayit_ol);
        isim_edit = findViewById(R.id.edit_ad);
        soyad_edit = findViewById(R.id.edit_soyad);
        mail_edit = findViewById(R.id.edit_email);
        sifre_edit = findViewById(R.id.edit_sifre);
        sifre_tekrar_edit = findViewById(R.id.edit_sifre_tekrar);


        kayit.setOnClickListener(new View.OnClickListener() {//Kayıt ol butonuna tıklanınca
            @Override
            public void onClick(View v) {
                String isim,soyad,mail,sifre,sifre_tekrar,hata_mesaji=" ";
                boolean hata = false;
                // TODO Auto-generated method stub
                //Edittextlerden bilgileri aldık
                isim = isim_edit.getText().toString();
                soyad = soyad_edit.getText().toString();
                mail = mail_edit.getText().toString();
                sifre = sifre_edit.getText().toString();
                sifre_tekrar = sifre_tekrar_edit.getText().toString();



                YönetimSistemi yönetimSistemi=new YönetimSistemi();
                yönetimSistemi.setKullaniciInputStream(getResources().openRawResource(R.raw.kullanici));
                yönetimSistemi.setYemekInputStream(getResources().openRawResource(R.raw.yemek));





               if (isim.matches("") || mail.matches("") || sifre.matches("") || sifre_tekrar.matches("")) {// boş veri var mı kontrolü
                    hata = true;
                    hata_mesaji = "Gerekli alanları doldurunuz.";
                } else if (!sifre.matches(sifre_tekrar)) {//şifreler uyuşuyor mu kontrolü
                    hata = true;
                    hata_mesaji = "Şifreler uyuşmuyor.";
                } else if (sifre.length()< 8) {//şifre karakter sayısı kontrolü
                    hata_mesaji = "Şifre 8 karakterden az olamaz.";
                    hata = true;
                } else if (!isEmailValid(mail)) {//Mail format kontrol
                    hata_mesaji = "Yanlış e-mail formatı!!!";
                    hata = true;
                }
                if(hata)
                    Toast.makeText(getApplicationContext(),hata_mesaji ,Toast.LENGTH_SHORT).show();
                else
                {
                   Kullanici yeni=new Kullanici( isim,  soyad,  sifre,  mail,"",  "","");
                   YönetimSistemi.kullaniciSet.add(yeni);



                    Toast.makeText(KayitActivity.this, "Kayıt", Toast.LENGTH_SHORT).show();
                        //YönetimSistemi.listeyeKullanicilariYaz();
                    new UpdateTask().execute();

                    kayit(v);
                }
            }
        });
    }
    private boolean isEmailValid(String mail) {
        return mail.endsWith("@gmail.com") || mail.endsWith("@hotmail.com") ||
                mail.endsWith("@outlook.com") || mail.endsWith("@gtu.edu.tr");
    }
    public void kayit(View view){

       finish();
    }

    private class UpdateTask extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {

            String mail,sifre;
            mail = mail_edit.getText().toString();
            sifre = sifre_edit.getText().toString();

            try {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_ferhat?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat1", "FerhaT2018");

                Statement st = conn.createStatement();




                st.executeUpdate("INSERT INTO kullanici_bilgileri(email,password,kara_liste,favoriler,gecmis) "
                        + " VALUE ('" + mail + "','" + sifre+"','"+""+"','"+""+"','"+""  + "')");







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
            if (values != null) {
                for (String value : values) {
                    // shows a toast for every value we get
                    Toast.makeText(KayitActivity.this, value, Toast.LENGTH_SHORT).show();
                }
            }
        }



    }










}
