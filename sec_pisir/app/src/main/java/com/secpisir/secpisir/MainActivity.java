package com.secpisir.secpisir;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                try {
                    YönetimSistemi yönetimSistemi = new YönetimSistemi();
                    yönetimSistemi.setKullaniciInputStream(getResources().openRawResource(R.raw.kullanici));
                    yönetimSistemi.setYemekInputStream(getResources().openRawResource(R.raw.yemek));
                    YönetimSistemi.yemekTarifleriniDosyadanOku();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);





    }

    /*@Override
    protected void onStart() {
        super.onStart();
        NavigationView navigationView = (NavigationView) findViewById(R.id.menuNavigationView);
        if (navigationView != null) {
            navigationView.setItemIconTintList(null);
        }

    }*/

    /*public void girisButonu(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }*/


    /*@Override
    protected void onStart() {
        super.onStart();
        View view = findViewById(R.id.view);
        view.setBackgroundColor(Color.RED);
    }*/

    public void onRegisterButtonClick(View view){
        //setContentView(R.layout.activity_kayit);
        //Intent intent = new Intent(this, KayitActivity.class);
        //startActivity(intent);
    }
}
