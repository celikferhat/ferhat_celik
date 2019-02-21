package com.example.asus.msql_park;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




public class bildirim extends AppCompatActivity {

        protected String pic_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirim);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mNotificationManager.cancel(001);


        new UpdateTask().execute();
        new Upload_to_ftp().execute();



    }

    public void bildirim_onay(View view) {
        finish();

    }

    private class UpdateTask extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {

            try {

                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_deneme?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat", "FerhaT123");

                Statement st = conn.createStatement();
                String query = "SELECT * FROM sikayetler";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){

                    if (rs.getString("plaka").equals(  getIntent().getExtras().get("plaka").toString()   )){
                        if(rs.getInt("durum") == 0)
                           publishProgress(rs.getString("plaka"),rs.getString("start_time"),rs.getString("mesaj"),rs.getString("resim_url"));

                    }
                }


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
            TextView plaka = findViewById(R.id.bildirim_plaka);
            TextView  sebep = findViewById(R.id.bildirim_sebep);
            TextView saat = findViewById(R.id.textView);
            int a=1;
            if (values != null) {
                for (String value : values) {
                    if(a==1)
                        plaka.setText(value);
                    if(a==2)
                        saat.setText(value);
                    if(a==3)
                        sebep.setText(value);
                    if(a==4)
                        pic_url=value;


                    a++;
                }

            }



        }


    }


    private class Upload_to_ftp extends AsyncTask<InputStream, InputStream,InputStream> {

        protected InputStream doInBackground(InputStream... urls) {
            FTPClient ftp = null;





            try {
                //Ben dosyayı random isimlendirmeyi tercih ettim. Siz fname' e istediğiniz ismi verebilirsiniz.


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



                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ftp.retrieveFile(pic_url+".jpeg", outputStream);

                InputStream inputstream = new ByteArrayInputStream(outputStream.toByteArray());




                publishProgress(inputstream);










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
        protected void onProgressUpdate(InputStream... values) {

            if (values != null) {


                for (InputStream value : values) {

                       ImageView image = findViewById(R.id.imageView);

                       image.setImageBitmap(BitmapFactory.decodeStream(value));



                }
            }



        }



    }




}
