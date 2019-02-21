package com.secpisir.secpisir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class KategorilerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriler);
    }

    public void kategorilerdenSlidera(View view){
        ArrayList<Integer> kategoridenYemekler = new ArrayList<>();
        switch(view.getId()){
            case(R.id.butoncorba):
                kategoridenYemekler = YönetimSistemi.kategoridenYemekIDleri("Çorba");
                break;
            case(R.id.butonanayemek):
                kategoridenYemekler = YönetimSistemi.kategoridenYemekIDleri("Ana Yemek");
                break;
            case(R.id.butonarayemek):
                kategoridenYemekler = YönetimSistemi.kategoridenYemekIDleri("Ara Yemek");
                break;
            case(R.id.butonicecekler):
                kategoridenYemekler = YönetimSistemi.kategoridenYemekIDleri("İçecekler");
                break;
            case(R.id.butonsalata):
                kategoridenYemekler = YönetimSistemi.kategoridenYemekIDleri("Salata");
                break;
            case(R.id.butontatlilar):
                kategoridenYemekler = YönetimSistemi.kategoridenYemekIDleri("Tatlı");
                break;
        }
        Yemek[] siraliYemekler = new Yemek[kategoridenYemekler.size()];
        int i = 0;
        /* --Merge sorting -- */
        for (Integer integer : kategoridenYemekler) {
            siraliYemekler[i] = YönetimSistemi.getYemek(integer);
            ++i;
        }
        MergeSort.sort(siraliYemekler);
        for (int j = 0; j < kategoridenYemekler.size(); j++) {
            kategoridenYemekler.set(j, siraliYemekler[j].getCode());
        }
        /* --- */
        Intent intent;
        if(kategoridenYemekler.size() != 0) {
            intent = new Intent(this, FerhatMain.class);
            intent.putExtra("aramaSonucu",kategoridenYemekler);
        }
        else {
            System.out.println("arama sonucu is zeor");
            intent = new Intent(this, TarifBulunamadi.class);
        }
        startActivity(intent);
    }
}