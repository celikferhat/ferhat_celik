package com.secpisir.secpisir;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.System.exit;

public class MainScreen extends AppCompatActivity {
    private Toolbar toolbar;
    public static Integer user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user_id = sharedPref.getInt("user_id",0);



        //MenuFragment menuFragment = new MenuFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);

        return true;
    }

    /* ------Menu Onclicks------- */
    public void menudenKaralisteye(MenuItem item) {
        if(YönetimSistemi.getCurrentKullanici()!=null)
        {
            Intent intent = new Intent(this, Karaliste.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
    }
    public void menudenFavorilere(MenuItem item) {
        if(YönetimSistemi.getCurrentKullanici()!=null) {
            Intent intent = new Intent(this, Favoriler.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
    }
    public void menudenTarifEklemeye(MenuItem item){
        if(YönetimSistemi.getCurrentKullanici()!=null){
            Intent intent = new Intent(this, TarifEkle.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }

    }
    public void menudenGecmise (MenuItem item) {
        if(YönetimSistemi.getCurrentKullanici()!=null){
            Intent intent = new Intent(this, Gecmis.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
    }

    public void menudenKullaniciBilgilerine(MenuItem item){
        Intent intent;
        if(YönetimSistemi.getCurrentKullanici() == null)
            Toast.makeText(getApplicationContext(),"Giriş yapmadınız" ,Toast.LENGTH_SHORT).show() ;
        else {
            intent = new Intent(this, KullaniciBilgileri.class);
            startActivity(intent);
        }
    }

    public void menudenCikisa(MenuItem item){
        YönetimSistemi.setCurrentKullanici((Kullanici) null);
        System.exit(0);
    }
    /* ------------- */

    public void anaEkrandanAramaya(View view) {
        Log.i("TAG", "deneme");
        Intent intent = new Intent(this, IngredientActivity.class);
        startActivity(intent);
    }

    public void anaEkrandanKategorilere(View view) {
        Intent intent = new Intent(this, KategorilerActivity.class);
        startActivity(intent);
    }

    public void anaEkrandanRastgeleye(View view) {
        Yemek yemek = YönetimSistemi.RastgeleOner();
        Intent intent = new Intent(this, YemekTarifi.class);
        intent.putExtra("yemekID",yemek.getCode());
        startActivity(intent);
    }

    public void anaEkrandanKullaniciyaOzele(View view){
        if(YönetimSistemi.getCurrentKullanici() == null){
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Yemek> oneri = YönetimSistemi.kullaniciyaOzelYemekOner(YönetimSistemi.getCurrentKullanici());
        ArrayList<Integer> oneriIDleri = new ArrayList<>(50);
        System.out.println("önerilen yemekler: " + oneri);
        Intent intent;
        for (Yemek yemek : oneri) {
            oneriIDleri.add(yemek.getCode());
        }
        System.out.println("önerilen yemek IDleri: " + oneriIDleri);
        if(oneri.size() == 0)
            intent = new Intent(this, TarifBulunamadi.class);
        else{
            intent = new Intent(this, FerhatMain.class);
            intent.putExtra("aramaSonucu", oneriIDleri);
        }
        startActivity(intent);

    }
}
