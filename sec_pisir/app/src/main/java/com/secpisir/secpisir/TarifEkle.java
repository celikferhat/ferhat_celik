package com.secpisir.secpisir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TarifEkle extends AppCompatActivity {

    Button ekle;
    EditText eklenecek_tarif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_ekle);

        ekle=findViewById(R.id.button_tarif_ekle);
        eklenecek_tarif=findViewById(R.id.edit_tarif_ekle);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eklenecek=ekle.getText().toString();
                if(!eklenecek.isEmpty())
                    ekle(view);
                else
                    Toast.makeText(getApplicationContext(),"Gerekli alanları doldurunuz." ,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ekle(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Tarifiniz bize başarı ile iletilmiştir." +
                "Teşekkür ederiz.",Toast.LENGTH_SHORT).show();
    }
}
