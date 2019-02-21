package com.secpisir.secpisir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class YemekTarifi extends AppCompatActivity {
    private static Yemek yemek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.yemek_tarifi);
        Integer yemekID = getIntent().getIntExtra("yemekID", -1);
        System.out.println("yemekID " + yemekID);
        yemek = YönetimSistemi.getYemek(yemekID);
        TextView tw = findViewById(R.id.textViewYemekTarifi);
        TextView yemekadi = findViewById(R.id.yemekadi);
        if (yemek == null)
            throw new IllegalStateException();
        String content = "Kalori: " + yemek.getKalori() + "\n";
        String yemekAdi =  yemek.getIsim();
        content += yemek.getTarif();
        tw.setText(content);
        yemekadi.setText(yemekAdi);
    }

    public static void setYemek(Yemek y) {
        yemek = y;
        if (yemek == null)
            throw new IllegalStateException();
    }

    public static Yemek getYemek() {
        return yemek;
    }
    
    public void bilgi(View view) {
        Toast toast=Toast.makeText(getApplicationContext(),"Hazırlanış süresi: "+ yemek.getHazirlanisSuresi(),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }


    public void yemegiGecmiseEkle(View view) {
        if(YönetimSistemi.getCurrentKullanici()!=null) {
            YönetimSistemi.getCurrentKullanici().gecmiseEkle(yemek.getIsim());
            //k.gecmiseEkle(yemek.getIsim());
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
    }

    public void yemegiFavorilereEkle(View view) {
        if(YönetimSistemi.getCurrentKullanici()!=null &&
                !YönetimSistemi.getCurrentKullanici().getKaraListe().contains(yemek.getIsim())) {
            YönetimSistemi.getCurrentKullanici().favorilereEkle(yemek.getIsim());
            //k.favorilereEkle(yemek.getIsim());
            Toast.makeText(getApplicationContext(), yemek.getIsim() +" Favorilere eklendi",Toast.LENGTH_LONG).show();
        }
        else if(YönetimSistemi.getCurrentKullanici() == null){
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
        else if(YönetimSistemi.getCurrentKullanici().getFavoriListe().contains(yemek.getIsim())){
            Toast.makeText(getApplicationContext(), "Eklemek istediğiniz yemek"+
                    " zaten favorilerinizde mevcut.",Toast.LENGTH_SHORT).show();
        }
    }

    public void yemegiKaralisteyeEkle(View view){
        if(YönetimSistemi.getCurrentKullanici()!=null &&
        !YönetimSistemi.getCurrentKullanici().getFavoriListe().contains(yemek.getIsim())) {
            YönetimSistemi.getCurrentKullanici().karaListeyeEkle(yemek.getIsim());
          //  k.karaListeyeEkle(yemek.getIsim());
            Toast.makeText(getApplicationContext(), "Kara listeye eklendi",Toast.LENGTH_LONG).show();
        }
        else if(YönetimSistemi.getCurrentKullanici() == null){
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
        else if(YönetimSistemi.getCurrentKullanici().getKaraListe().contains(yemek.getIsim())){
            Toast.makeText(getApplicationContext(), "Eklemek istediğiniz yemek"+
                    " zaten kara listede mevcut.",Toast.LENGTH_SHORT).show();
        }
    }
}
