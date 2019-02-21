package com.secpisir.secpisir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class KullaniciBilgileri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kullanici_bilgileri);

        TextView k_soyadi = findViewById(R.id.soyad);
        TextView ad = findViewById(R.id.ad);
        TextView mail = findViewById(R.id.email);
        String adi = YönetimSistemi.getCurrentKullanici().getIsim();
        String soyadi = YönetimSistemi.getCurrentKullanici().getSoyad();
        Kullanici k = YönetimSistemi.getCurrentKullanici();

        ad.append(adi);
        k_soyadi.append(soyadi);
        mail.append(k.getEmail());
    }
}
