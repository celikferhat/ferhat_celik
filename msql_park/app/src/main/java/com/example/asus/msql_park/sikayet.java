package com.example.asus.msql_park;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

public class sikayet extends AppCompatActivity {


    private String user___id;
    private String sikayet_id;
    protected static Bitmap bitmap1;
    String file_name="url";
    String url="url";

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


        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            }
        }

        setContentView(R.layout.activity_sikayet);



        final Spinner spinner = findViewById(R.id.spinner);

        final Spinner spinner2 = findViewById(R.id.spinner2);

        dataAdapterForknlr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, msj_knlr);
        dataAdapterForknlr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapterForknlr);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItem().toString().equals(msj_knlr[0])){


                    dataAdapterFormsj = new ArrayAdapter<String>(sikayet.this,android.R.layout.simple_spinner_item,a1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[1])){


                    dataAdapterFormsj = new ArrayAdapter<String>(sikayet.this,android.R.layout.simple_spinner_item,b1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[2])){


                    dataAdapterFormsj = new ArrayAdapter<String>(sikayet.this,android.R.layout.simple_spinner_item,c1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[3])){


                    dataAdapterFormsj = new ArrayAdapter<String>(sikayet.this,android.R.layout.simple_spinner_item,d1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[4])){


                    dataAdapterFormsj = new ArrayAdapter<String>(sikayet.this,android.R.layout.simple_spinner_item,e1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }
                if(spinner.getSelectedItem().toString().equals(msj_knlr[5])){


                    dataAdapterFormsj = new ArrayAdapter<String>(sikayet.this,android.R.layout.simple_spinner_item,f1);
                    dataAdapterFormsj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapterFormsj);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*---------------------------------------------------------------------*/
        user___id = getIntent().getExtras().get("userid").toString();
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




    public void gonder(View view){

        EditText plaka = findViewById(R.id.editText);
        Spinner mesaj_konusu = findViewById(R.id.spinner);
        Spinner mesaj = findViewById(R.id.spinner2);
        TextView konum = findViewById(R.id.anaekran_konum);


        if(plaka.getText().length()==0)
            Toast.makeText(getApplicationContext(),"Plaka boş olamaz",Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(getApplicationContext(), "Şikayetiniz başarıyla gönderildi.\n" +
                    "15 dk sonra bildirim alacaksınız.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(),BroadcastService.class);
            intent.putExtra("plaka",plaka.getText());



            UUID sukey = UUID.randomUUID();
            intent.putExtra("sikayet_id",sukey.toString());
            sikayet_id = sukey.toString();
            startService(intent);



            new UpdateTask().execute();
            if(bitmap1 != null) {
                UUID uniqueKey = UUID.randomUUID();
                file_name=uniqueKey.toString();
                url=file_name;




                new Upload_to_ftp().execute();



            }

        }


        SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putString("sikayet_id",sikayet_id);
        editor.putString("splaka",plaka.getText().toString());

        editor.commit();


            finish();
    }


    public void signout(View view){


        finish();

    }



    private class UpdateTask extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {
            EditText plaka = findViewById(R.id.editText);
            Spinner mesaj_konusu = findViewById(R.id.spinner);
            Spinner mesaj = findViewById(R.id.spinner2);
            TextView konum = findViewById(R.id.anaekran_konum);



            try {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_deneme?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat", "FerhaT123");

                Statement st = conn.createStatement();

                String []parts = Calendar.getInstance().getTime().toString().split(" ");


                st.executeUpdate("INSERT INTO sikayetler (plaka, userid, konu, mesaj  ,  gps,resim_url,start_time,sikayet_id) "
                        + " VALUE ('" + plaka.getText().toString() + "','" + Integer.valueOf( user___id) + "','" + mesaj_konusu.getSelectedItem().toString() + "','" + mesaj.getSelectedItem().toString() + "','" + konum.getText().toString() + "','"+url+ "','"+ parts[3]  +"','"+sikayet_id+ "')");


                conn.close();


                finish();


            }
            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }




            return null;
        }





        @Override
        protected void onProgressUpdate(String... values) {
            if (values != null) {
                for (String value : values) {
                    // shows a toast for every value we get
                    Toast.makeText(sikayet.this, value, Toast.LENGTH_SHORT).show();
                }
            }
        }



    }
    private class Upload_to_ftp extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {
            FTPClient ftp = null;
            String fname;
            try {
                //Ben dosyayı random isimlendirmeyi tercih ettim. Siz fname' e istediğiniz ismi verebilirsiniz.

                fname = file_name;
                //Yeni bir ftpClient tanımlıyorum daha sonra ftp adresi ve kullanıcı adı, şifre bilgilerini bu client' a atıyorum.
                ftp = new FTPClient();
                ftp.connect("94.73.151.83");
                ftp.login("u8024854", "OzguR2018");
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                //image in yükleneceği klasörü isterseniz bu fonksiyonla değiştirebilirsiniz(Alt klasör vb. Ben değiştirmedim.).
                ftp.changeWorkingDirectory("/public_html/hatali_park");
                int reply = ftp.getReplyCode();
                System.out.println("Ftp bağlantısından cevap alındı" + reply);
                if (FTPReply.isPositiveCompletion(reply)) {
                    System.out.println("Bağlantı başarılı"); }
                ftp.enterLocalPassiveMode(); //Burada image in dosya yolunu String'den File'a convert edin.

                File f = new File(getApplicationContext().getCacheDir(), "deneme1");
                f.createNewFile();

//Convert bitmap to byte array
                Bitmap bitmap = bitmap1;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();



                FileInputStream in3 = new FileInputStream(f);



                //Burası da dosyanın ismini ve uzantısını belirlediğiniz yer
                boolean result = ftp.storeFile(fname + ".jpeg", in3); in3.close(); //Başarılıysa result true dönücektir. Değise catch'e düşecektir.
                if (result)
                    Log.v("upload sonucu", "succeeded");
                System.out.println("SUCCESS");
                ftp.logout();
                ftp.disconnect();
            }
            catch (Exception e) {
                e.printStackTrace();
            }





            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }



    }





}