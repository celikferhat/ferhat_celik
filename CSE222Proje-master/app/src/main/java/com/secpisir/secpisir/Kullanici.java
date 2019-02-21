package com.secpisir.secpisir;


import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class Kullanici {

    private BinarySearchTree<String> favoriler;
    private BinarySearchTree<String> karaListe;
    private Stack<String> gecmis= new Stack<>();
    private boolean girisYapildi = false;

    private String isim;
    private String soyad;
    private String sifre;
    private String email;

    public Kullanici(String isim, String soyad, String sifre, String email
            , String favoriler, String karaliste,String gecmis){
        this.email=email;
        this.isim=isim;
        this.soyad=soyad;
        this.sifre=sifre;

        this.favoriler = setListe(favoriler);
        this.karaListe = setListe(karaliste);
        setGecmis(gecmis);
    }


    public boolean karaListeyeEkle(String yemek)  {
        if (favoriler.contains(yemek))
            return false;
        Boolean check = karaListe.add(yemek);
        new ekle().execute(yemek,"kara");
        return check;
    }

    public boolean karaListedenCıkar(String yemek) {
        Boolean check=karaListe.remove(yemek);

        return check;
    }
    //
    public boolean favorilereEkle(String yemek) {


        if (karaListe.contains(yemek))
            return false;
        Boolean check=favoriler.add(yemek);

        new ekle().execute(yemek,"fav");


        return check;
    }
    //
    public boolean favorilerdenCıkar(String yemek) {
        Boolean check=favoriler.remove(yemek);

        return check;
    }

    boolean gecmiseEkle(String yemek){
        Boolean check=gecmis.add(yemek);
        new ekle().execute(yemek,"gecmis");
        return check;
    }
    boolean gecmistenSil(String yemek){
        Boolean check=gecmis.remove(yemek);
        /*try {
            YönetimSistemi yönetimSistemi = new YönetimSistemi();
            yönetimSistemi.listeyeKullanicilariYaz();        } catch (IOException e) {
            return false;
        }*/
        return check;
    }
    public String getListe(AbstractList<String> liste){
        StringBuilder sb= new StringBuilder();
        int i=0;
        do {
            sb.append(liste.get(i));
            i++;
        }while(i<liste.size()&&sb.append("-")!=null);
        return sb.toString();
    }

    private BinarySearchTree<String> setListe(String yemekler) {
        //TODO String ile arama sorun olursa yemek objesi oluşturulacak
        BinarySearchTree<String> yemekListesi=new BinarySearchTree<>() ;
        String[] data = yemekler.split("-");
        for(int i=0;i<data.length;++i)
        {
            if(data[i].length() > 1)
                yemekListesi.add(data[i]);


        }
        return yemekListesi;
    }

    void  setGecmis(String gecmis) {
        String[] data = gecmis.split("-");
        for(String s : data)
        {

            if(s.length() >1)
              this.gecmis.push(s);


        }
    }
    public Stack<String> getGecmis() {
        return gecmis;
    }
    public String getGecmis(int ignore){
        StringBuilder sb=new StringBuilder();
        Iterator<String> iter=gecmis.iterator();
        if(iter.hasNext())
        {
            do {
                sb.append(iter.next());
            }while (iter.hasNext() && sb.append("-")!=null);
        }else
            sb.append(0);
        return sb.toString();
    }
    public BinarySearchTree<String> getKaraListe(){
        return karaListe;
    }
    public BinarySearchTree<String> getFavoriListe() {
        return favoriler;
    }



    String getEmail() { return email; }

    String getSifre() { return sifre; }



    void setSifre(String sifre) { this.sifre = sifre; }

    public String getIsim() { return isim; }

    public void setIsim(String isim) { this.isim = isim; }

    public String getSoyad() { return soyad; }

    public void setSoyad(String soyad) { this.soyad = soyad; }




    private class ekle extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {




            try {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_ferhat?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat1", "FerhaT2018");

                Statement st = conn.createStatement();
                String query = "SELECT * FROM kullanici_bilgileri";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){

                    if (rs.getString("user_id").equals( MainScreen.user_id.toString()  ) ) {
                        if(urls[1].equals("fav"))
                            st.executeUpdate("UPDATE kullanici_bilgileri SET favoriler="+"'"+rs.getString("favoriler")+"-"+urls[0]+"'"+" WHERE user_id='"+MainScreen.user_id+"'"  );
                        if(urls[1].equals("kara"))
                            st.executeUpdate("UPDATE kullanici_bilgileri SET kara_liste="+"'"+rs.getString("kara_liste")+"-"+urls[0]+"'"+" WHERE user_id='"+MainScreen.user_id+"'"  );
                        if(urls[1].equals("gecmis"))
                            st.executeUpdate("UPDATE kullanici_bilgileri SET gecmis="+"'"+rs.getString("gecmis")+"-"+urls[0]+"'"+" WHERE user_id='"+MainScreen.user_id+"'"  );




                    }
                }





                conn.close();





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


            for(String s : values)
            {



            }


        }



    }



}
