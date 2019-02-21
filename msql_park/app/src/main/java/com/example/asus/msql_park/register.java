package com.example.asus.msql_park;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerbutton(View view) {




       control();

    }

    public void geridon(View view) {
        finish();
    }




    private class UpdateTask extends AsyncTask<String, String,String> {
        Integer userid;
        protected String doInBackground(String... urls) {

            EditText eposta = findViewById(R.id.kullaniciadi);
            EditText parola = findViewById(R.id.kullaniciparola);
            EditText username =  findViewById(R.id.isimsoyisim);
            EditText telefon =  findViewById(R.id.telno);
            EditText plaka = findViewById(R.id.plaka);


            try {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_deneme?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat", "FerhaT123");

                Statement st = conn.createStatement();




                    st.executeUpdate("INSERT INTO kullanici (user_eposta, user_name, user_pass, user_plaka  ,  user_telno) "
                            + " VALUE ('" + eposta.getText().toString() + "','" + username.getText().toString() + "','" + parola.getText().toString() + "','" + plaka.getText().toString() + "','" + telefon.getText().toString() + "')");

                    // note that i'm leaving "date_created" out of this insert statement


                    String query = "SELECT * FROM kullanici";

                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        if (rs.getString("user_eposta").equals(eposta))
                            userid = rs.getInt("user_id");
                    }
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
                    Toast.makeText(register.this, value, Toast.LENGTH_SHORT).show();
                }
            }
        }



    }




    private void control(){

        EditText eposta = findViewById(R.id.kullaniciadi);
        EditText parola = findViewById(R.id.kullaniciparola);
        TextView epostahata = findViewById(R.id.epostahata);
        TextView parolahata = findViewById(R.id.parolahata);




        boolean var=false;
        if(eposta.getText().toString().length() == 0)
        {

            epostahata.setText("E-posta boş olamaz");
            epostahata.setTextColor(Color.rgb(255,0,0));
        }else
        {   epostahata.setText("");


            for(int i = 0;i<eposta.getText().toString().length();i++){
                if(eposta.getText().toString().charAt(i) == '@')
                    var=true;
            }

            if(!var)
                Toast.makeText(register.this, "E-posta tipi hatalı", Toast.LENGTH_SHORT).show();
            else{
                if(parola.getText().toString().length() < 6){

                    parolahata.setText("Parola en az 6 karakter olamalı");
                    parolahata.setTextColor(Color.rgb(255,0,0));
                }
                else{
                    parolahata.setText("");
                    new UpdateTask().execute();

                }
            }


        }

    }

}
