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

public class Karaliste extends AppCompatActivity implements KaralisteFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaliste);

        final Karaliste cntx = this;
        final LinearLayout linearly = findViewById(R.id.linearLayout_karaliste);
        YönetimSistemi yönetimSistemi=new YönetimSistemi();
        BinarySearchTree<String> karaliste=yönetimSistemi.getCurrentKullanici().getKaraListe();
        String[] karaliste1 = karaliste.toString().split("-");
        System.out.println("here"+karaliste1.length);
        for (int i=0;i<karaliste1.length && karaliste1[0]!="";++i)
        {
            FrameLayout flTest = new FrameLayout(cntx);
            int id = View.generateViewId();
            flTest.setId(id);
            linearly.addView(flTest);
            addFragment(id,karaliste1[i]);
        }
    }

    @Override
    public void onFragmentClose(KaralisteFragment fr) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fr);
        fragmentTransaction.commit();
    }

    protected void addFragment(int id, String text) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        KaralisteFragment kara = KaralisteFragment.newInstance(text);
        kara.liste="kara";
        fragmentTransaction.add(id, kara);
        fragmentTransaction.commit();
    }
}
