package com.example.firat.wrong_park;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ana_ekran extends AppCompatActivity {

    public static final int REQUEST_CAMERA = 1;
    LocationManager locationManager;
    LocationListener locationListener;




    private ArrayAdapter<String> dataAdapterForknlr;
    private ArrayAdapter<String> dataAdapterFormsj;
    String [] msj_knlr = {
            "Engelli Park İşgali",
            "Otobüs Durak İşgali",
            "Kaldırım ve Banket İşgali",
            "Bisiklet Yolu İşgali",
            "Garaj ,Bina ve Depo Önü İşgali",
            "Dar Sokak Giriş Engeli"
    };

    String[] a1 = {
            "Engelli Otopark Alanını İşgal",
            "Engelli Yürüyüş Yolu",
            "Engelli Kaldırım İhlali"


    };

    String[] b1 = {
            "Otobüs Durağında Aracınız Var"

    };

    String [] c1 = {
            "Kaldırıma Araç Park Ettiniz",
            "Bankete Araç Park Ettiniz",
            "Kaldırım Başlangıç ve Bitişine Araç Park Ettiniz"

    };
    String [] d1 = {
      "Bisiklet Yoluna Park Ettiniz"
    };

    String [] e1 = {

            "Garaj Önüne Park Ettiniz",
            "Bina Önüne Park Ettiniz",
            "Depo Önüne Park Ettiniz"

    };

    String [] f1 = {

            "İtfaiye Giremiyor",
            "Ambulans Giremiyor",
            "Polis Giremiyor",
            "Arabamı Çıkaramıyorum"

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView konum = findViewById(R.id.anaekran_konum);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
        if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 123);
        }

        setContentView(R.layout.activity_ana_ekran);



        final Spinner spinner = findViewById(R.id.spinner);

        final Spinner spinner2 = findViewById(R.id.spinner2);

        dataAdapterForknlr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, msj_knlr);
        dataAdapterForknlr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapterForknlr);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItem().toString().equals(msj_knlr[0])){


                    dataAdapterFormsj = new ArrayAdapter<String>(ana_ekran.this,android.R.layout.simple_spinner_item,a1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[1])){


                    dataAdapterFormsj = new ArrayAdapter<String>(ana_ekran.this,android.R.layout.simple_spinner_item,b1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[2])){


                    dataAdapterFormsj = new ArrayAdapter<String>(ana_ekran.this,android.R.layout.simple_spinner_item,c1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[3])){


                    dataAdapterFormsj = new ArrayAdapter<String>(ana_ekran.this,android.R.layout.simple_spinner_item,d1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[4])){


                    dataAdapterFormsj = new ArrayAdapter<String>(ana_ekran.this,android.R.layout.simple_spinner_item,e1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[5])){


                    dataAdapterFormsj = new ArrayAdapter<String>(ana_ekran.this,android.R.layout.simple_spinner_item,f1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
/*---------------------------------------------------------------------*/

/*---------------------------------------------------------------------*/
    }













    public void anaekran_resimcek (View view){

        Intent intent = new Intent(this, resimcek.class);
        startActivity(intent);


    }







    public void refresh(View view){
        TextView text = findViewById(R.id.anaekran_konum);
        GPStracker gt = new GPStracker(getApplicationContext());
        Location l = gt.getLocation();
        if( l == null){

            Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            double lat = l.getLatitude();
            double lon = l.getLongitude();


            text.setText("GPS: LAT: " +lat +"\n"+"LON: "+lon);

        }




    }


    public void bildirimler(View view){

        Intent intent = new Intent(this,bildirim.class);
        startActivity(intent);


    }

    public void gonder(View view){

        EditText plaka = findViewById(R.id.editText);
        Spinner mesaj_konusu = findViewById(R.id.spinner);
        Spinner mesaj = findViewById(R.id.spinner2);
        TextView konum = findViewById(R.id.anaekran_konum);


        if(plaka.getText().length()==0)
            Toast.makeText(getApplicationContext(),"Plaka boş olamaz",Toast.LENGTH_SHORT).show();
        else {

            String []parts = Calendar.getInstance().getTime().toString().split(" ");
            kullanici_bilgileri k1 = new kullanici_bilgileri(plaka.getText().toString(), mesaj_konusu.getSelectedItem().toString()
                    , mesaj.getSelectedItem().toString(), konum.getText().toString(),parts[3],FirebaseAuth.getInstance().getCurrentUser().getUid(),0);

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Sikayetler");


            myRef.child(plaka.getText().toString()).child(parts[3]).setValue(k1);

            Toast.makeText(getApplicationContext(), "Şikayetiniz başarıyla gönderildi", Toast.LENGTH_SHORT).show();
        }


    }


    public void signout(View view){


        FirebaseAuth.getInstance().signOut();

        finish();

    }




}