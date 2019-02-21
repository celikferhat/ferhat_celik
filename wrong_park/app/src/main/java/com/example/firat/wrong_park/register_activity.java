package com.example.firat.wrong_park;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class register_activity extends AppCompatActivity {


    public static final int REQUEST_STORAGE=1;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        if( checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_STORAGE);
        }
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
        }


        auth=FirebaseAuth.getInstance();




    }



private void createuser(String email,String password){

    auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                        FirebaseUser user = auth.getCurrentUser();

                        EditText isimsoyisim = findViewById(R.id.isimsoyisim);
                        EditText plaka = findViewById(R.id.plaka);
                        EditText eposta = findViewById(R.id.kullaniciadi);


                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("User_Informations");
                        register_inf reg = new register_inf(plaka.getText().toString(),eposta.getText().toString(),isimsoyisim.getText().toString());

                        myRef.child(user.getUid()).setValue(reg);
                        System.out.println(user.getUid().toString());
                        finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                        Toast.makeText(register_activity.this, "kayıt yapılamadı.E-mail biçimi hatalı olabilir",
                                Toast.LENGTH_SHORT).show();
                        Log.e("LoginActivity", "Failed Registration", e);
                    }

                    // ...
                }
            });

}






    public void registerbutton(View view ){


        EditText parola = findViewById(R.id.kullaniciparola);
        EditText eposta = findViewById(R.id.kullaniciadi);


        TextView epostahata = findViewById(R.id.epostahata);
        TextView parolahata = findViewById(R.id.parolahata);



        boolean isthereAT=false;
        for(int i = 0 ; eposta.getText().length()>i;i++){

        if (eposta.getText().toString().charAt(i) == '@')
            isthereAT=true;

        }

        if(!isthereAT)
        {
            epostahata.setText("Girdi tipi hatalı");
            epostahata.setTextColor(Color.rgb(255,0,0));

        }else {
                epostahata.setText("");

            if(parola.getText().length() < 8){
                parolahata.setText("Parola 8 karakterden uzun olmalı");
                parolahata.setTextColor(Color.rgb(255,0,0));

                }
                else {

                boolean buyukhaf=false;

                for(int j = 0 ; parola.getText().length()>j;j++){

                    if ( parola.getText().toString().charAt(j) >= 'A' &&  parola.getText().toString().charAt(j) <= 'Z'   )
                        buyukhaf=true;

                }
                if(!buyukhaf){
                    parolahata.setText("Parola içerisinde büyük harf bulunmalı");


                }
                else {

                    boolean kucukharf=false;

                    for(int j = 0 ; parola.getText().length()>j;j++){

                        if ( parola.getText().toString().charAt(j) >= 'a' &&  parola.getText().toString().charAt(j) <= 'z'   )
                            kucukharf=true; }

                    if(!kucukharf){
                        parolahata.setText("büyük ve küçük harf olmalı");
                    }else{
                        parolahata.setText("");


                        /****************/

                        createuser(eposta.getText().toString(),parola.getText().toString());




                        /*****************/


                    }


            }

        }

    }

}

public void geridon(View view){

    Intent intent = new Intent(this,login_activity.class);
    startActivity(intent);
    finish();

}




}
