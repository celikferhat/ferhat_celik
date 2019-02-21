package com.secpisir.secpisir;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Favoriler extends AppCompatActivity implements FavorilerFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriler);
        //TODO: kullanıcı sınıfından Karaliste datafieldì ile işlemi yap
        final Favoriler cntx = this;
        final LinearLayout linearly = findViewById(R.id.linearLayout_favoriler);
        //frame layout oluşturuldu
        YönetimSistemi yönetimSistemi=new YönetimSistemi();
        BinarySearchTree<String> favorilerTree= YönetimSistemi.getCurrentKullanici().getFavoriListe();
        String[] favoriler = favorilerTree.toString().split("-");
        for (int i=0;i<favoriler.length&& favoriler[0]!="";++i)
        {
            FrameLayout flTest = new FrameLayout(cntx);
            int id = View.generateViewId();
            flTest.setId(id);
            linearly.addView(flTest);
            addFragment(id,favoriler[i]);
        }
    }

    @Override
    public void onFragmentClose(FavorilerFragment fr) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fr);
        fragmentTransaction.commit();
    }

    protected void addFragment(int id, String text) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FavorilerFragment fav = FavorilerFragment.newInstance(text);
        fragmentTransaction.add(id, fav);
        fragmentTransaction.commit();
    }
}
