package com.secpisir.secpisir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Rastgele extends Activity {
    String yemek="Revani";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rastgele);
    }

    public void yemegiGecmiseEkle(View view) {
        if(YönetimSistemi.getCurrentKullanici()!=null) {
            YönetimSistemi.getCurrentKullanici().getGecmis().add(yemek);
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
                !YönetimSistemi.getCurrentKullanici().getKaraListe().contains(yemek)) {
            YönetimSistemi.getCurrentKullanici().favorilereEkle(yemek);
            //k.favorilereEkle(yemek.getIsim());
            Toast.makeText(getApplicationContext(), yemek +" Favorilere eklendi",Toast.LENGTH_LONG).show();
        }
        else if(YönetimSistemi.getCurrentKullanici()!=null &&
                YönetimSistemi.getCurrentKullanici().getKaraListe().contains(yemek)) {
            Toast.makeText(getApplicationContext(), yemek +" Eklemek istediğiniz yemek "+
                    "kara listenizde mevcut!Eklenemedi...",Toast.LENGTH_LONG).show();
        }
        else if(YönetimSistemi.getCurrentKullanici()!=null&&
                YönetimSistemi.getCurrentKullanici().getFavoriListe().contains(yemek)){
            Toast.makeText(getApplicationContext(), "Eklemek istediğiniz yemek"+
                    " zaten favorilerinizde mevcut.",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
    }

    public void yemegiKaralisteyeEkle(View view){
        if(YönetimSistemi.getCurrentKullanici()!=null &&
                !YönetimSistemi.getCurrentKullanici().getFavoriListe().contains(yemek)) {
            YönetimSistemi.getCurrentKullanici().getKaraListe().add(yemek);
            //  k.karaListeyeEkle(yemek.getIsim());
            Toast.makeText(getApplicationContext(), "Kara listeye eklendi",Toast.LENGTH_LONG).show();
        }
        else if(YönetimSistemi.getCurrentKullanici()!=null &&
                YönetimSistemi.getCurrentKullanici().getFavoriListe().contains(yemek)) {
            Toast.makeText(getApplicationContext(), yemek +" Eklemek istediğiniz yemek "+
                    "favorilerinizde mevcut.Eklenemedi...!",Toast.LENGTH_LONG).show();
        }
        else if(YönetimSistemi.getCurrentKullanici()!=null &&
                YönetimSistemi.getCurrentKullanici().getKaraListe().contains(yemek)){
            Toast.makeText(getApplicationContext(), "Eklemek istediğiniz yemek"+
                    " zaten kara listede mevcut.",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Bu özellik yalnızca kayıtlı"+
                    " kullanıcılar içindir...",Toast.LENGTH_SHORT).show();
        }
    }
}
