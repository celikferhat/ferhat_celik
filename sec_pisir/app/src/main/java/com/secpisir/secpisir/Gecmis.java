package com.secpisir.secpisir;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Stack;

public class Gecmis extends AppCompatActivity implements KaralisteFragment.OnFragmentInteractionListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gecmis);

        //TODO: kullanıcı sınıfından Karaliste datafieldì ile işlemi yap
        YönetimSistemi yönetimSistemi=new YönetimSistemi();
        Stack<String> gecmis=yönetimSistemi.getCurrentKullanici().getGecmis();

        final Gecmis cntx = this;
        final LinearLayout linearly = findViewById(R.id.linearLayout_gecmis);
        //frame layout oluşturuldu
        for (int i=gecmis.size()-1;i>=0;--i)
        {
            FrameLayout flTest = new FrameLayout(cntx);
            int id = View.generateViewId();
            flTest.setId(id);
            linearly.addView(flTest);
            addFragment(id,gecmis.get(i));
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
        KaralisteFragment kara =  KaralisteFragment.newInstance(text);
        kara.liste = "gecmis";
        fragmentTransaction.add(id,kara);
        fragmentTransaction.commit();
    }
}
