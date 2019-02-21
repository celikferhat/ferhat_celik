package com.secpisir.secpisir;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class YönetimSistemi extends AppCompatActivity {
    static Context context;
    static Map<String,String> kullaniciAdlari = new HashMap<>();
    private static Map<String,String> kullaniciEmailleri = new HashMap<>();
    private static Stack<Yemek> EklenenYemekler;
    private static Stack<Icecek> EklenenIcecekler;
    private static ArrayList<Yemek> yemekler = new ArrayList<>(100);
    private static ArrayList<Icecek> icecekler;
    //private static PriorityQueue<Malzeme> SıkKullanılanlar;// heap;
    private static ArrayList<Malzeme> malzemeler = new ArrayList<>(40);
    static Set<Kullanici> kullaniciSet = new HashSet<>();
    //TODO: remove below
    //public static ArrayList<Kullanici> kullanicilar;
    private static ListGraph yemeklerCizgesi = new ListGraph(50, false);
    private static InputStream kullanicilarStream;
    private static InputStream yemeklerStream;
    private static OutputStream kullaniciOutputStream;

    private static Kullanici currentKullanici = null;
    public static Kullanici getKullanici(){ return currentKullanici; }
    public static Kullanici getCurrentKullanici(){ return currentKullanici; }
    public static Kullanici setCurrentKullanici(Kullanici k){
        Kullanici temp = currentKullanici;
        currentKullanici = null;
        return temp; }

    YönetimSistemi() {    }
    YönetimSistemi(Context context) { YönetimSistemi.context = context;   }

    public static ArrayList<Yemek> getYemekler() {
        return yemekler;
    }
    public static ArrayList<Malzeme> getMalzemeler() { return malzemeler; }

    public static void setKullaniciOutputStream(OutputStream op) { YönetimSistemi.kullaniciOutputStream = op; }

    /**current kullanıcı için setter*/
    public void setCurrentKullanici(String email,String favoriler,String karaliste,String gecmis) {

                //String sp = ";";
                //favoriler.split(sp)



                Kullanici yeni = new Kullanici("","","",email,favoriler,karaliste,gecmis);
                currentKullanici=yeni;


    }
    public static int getYemek(String isim){
        for(Yemek yemek:yemekler)
            if (yemek.getIsim().equals(isim))
                return yemek.getCode();
        return -1;
    }

    public void setYemekInputStream(InputStream is){ yemeklerStream = is;}
    public void setKullaniciInputStream(InputStream is){ kullanicilarStream = is; }
   
    public static Set<Kullanici> getKullaniciSet() {
        return kullaniciSet;
    }

    public static Yemek getYemek(int index){
        if(index > yemekler.size())
            throw new IndexOutOfBoundsException();
        return yemekler.get(index);
    }
    public static Malzeme getMalzeme(int index){
        return malzemeler.get(index);
    }
    public static ArrayList<String> getMalzemeIsimleri(){
        ArrayList<String> result = new ArrayList<>(malzemeler.size());
        for (Malzeme malzeme : malzemeler) {
            result.add(malzeme.getIsim());
        }
        return result;
    }

    public static ArrayList<Integer> kategoridenYemekIDleri(String kategori){
        ArrayList<Integer> result = new ArrayList<>(yemekler.size()/5);
        for (Yemek yemek : yemekler) {
            if(yemek.getKategori().equals(kategori)){
                result.add(yemek.getCode());
            }
        }
        return result;
    }

    private static ArrayList<Yemek> malzemedenYemekOnerRecursive(ArrayList<Yemek> yemek, ArrayList<Malzeme> malzeme) throws IllegalArgumentException{
        if(malzeme.size() < 1 )
            throw new IllegalArgumentException("Malzemenin size'ını düzgün gönder");
        System.out.println("Gelen malzemeler:" + malzeme);
        ArrayList<Yemek> temp = new ArrayList<>();
        for (int i = 0 ;i < yemek.size() ;i ++){
            System.out.println("yemek.get(i):" + yemek.get(i) + " and malzeme " + malzeme.get(malzeme.size()-1));
            if(yemek.get(i).containsMalzeme(malzeme.get(malzeme.size()-1))){
                if(currentKullanici != null && currentKullanici.getKaraListe().contains(yemek.get(i).getIsim()))
                    continue;
                else {
                    System.out.println("ture");
                    temp.add(yemek.get(i));
                }
            }
        }
        malzeme.remove(malzeme.size()-1);
        if (malzeme.size() == 0)
            return temp;
        temp = malzemedenYemekOnerRecursive(temp,malzeme);
        return temp;
    }

    public static ArrayList<Yemek> malzemedenYemekOner(ArrayList<Malzeme> secilenMalzemeler){
        ArrayList<Yemek> result = new ArrayList<>(yemekler);
        result = malzemedenYemekOnerRecursive(result, secilenMalzemeler);
        return result;
    }

    public static Yemek RastgeleOner(){
        Random random = new Random();
        int index = random.nextInt(yemekler.size());
        return yemekler.get(index);
    }

    public static Boolean girisYap(){
        return true;/////
    }

    public static Stack<Tüketilebilir> SonEklenenleriGöster(){
        Stack<Tüketilebilir> temp = new Stack<>();
        return temp;//////
    }

    static boolean kullaniciDogrula(String kullaniciAdi, String sifre){
        return kullaniciAdlari.containsKey(kullaniciAdi) && kullaniciAdlari.get(kullaniciAdi).equals(sifre);
    }
/*
    public Menu BuGununOnerisi(){

    }

    public Menu KullanıcıyaOzelMenuOner(Kullanici kullanıcı){

    }
    public boolean tarifKabul(Yemek yeniyemek){

    }
*/

    public static ArrayList<Yemek> kullaniciyaOzelYemekOner(Kullanici kullanici){
        ArrayList<Yemek> favorilerListesi = new ArrayList<>(30);
        ArrayList<Yemek> result = new ArrayList<>(20);
        String[] favoriler = kullanici.getFavoriListe().toString().split("-");
        for (String s: favoriler) {
            for (Yemek yemek : yemekler) {
                if(s.equals(yemek.getIsim())) {
                    //System.out.println("adding " + yemek);
                    favorilerListesi.add(yemek);
                }
                //else
                //System.out.println(s + "was not equal to " + yemek.getIsim());
            }
        }
        PriorityQueue<OncelikliYemek> pq = new PriorityQueue<>(yemekler.size(), OncelikliYemek.getComparator());
        int[] oncelikler = new int[yemekler.size()];

        for (int i = 0; i < yemekler.size(); i++) {
            Yemek yemek = yemekler.get(i);
            for (Yemek yemek1 : favorilerListesi) {
                Edge e = yemeklerCizgesi.getEdge(yemek.getCode(), yemek1.getCode());
                if (e != null) {
                    System.out.println("weight between " + yemek1 + " and " + yemek + ":" + e.getWeight());
                    oncelikler[i] += e.getWeight();
                }
            }
            System.out.println("total oncelik of "+ yemek.getIsim() +" " + oncelikler[i]);
            System.out.println();
            pq.offer(new OncelikliYemek(yemek, oncelikler[i]));
        }

        while (!pq.isEmpty()) {
            OncelikliYemek polledYemek = pq.poll();
            if (!result.contains(polledYemek.yemek))
                result.add(polledYemek.yemek);
        }

        return result;
    }





    public static void yemekTarifleriniDosyadanOku() {
        Scanner scanner = new Scanner(yemeklerStream);
        if(scanner.hasNext())
            //Skip the csv headings
            scanner.nextLine();
        int mealCode = 0, ingredientCode = 0;
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            Yemek yemek = new Yemek();
            //Yemek Adı;Malzemeler;Kategori;Kalori;Hazırlanış;Hazırlanma Süresi;
            String ingredientsWhole = line.split(";")[1];
            ArrayList<Malzeme> malzemeArrayList = new ArrayList<>(6);
            for (int i = 0; i < ingredientsWhole.split("-").length; i++) {
                Malzeme malzeme = new Malzeme();
                malzeme.setIsim(ingredientsWhole.split("-")[i]);
                malzeme.setKod(ingredientCode);
                malzemeArrayList.add(malzeme);
                if(!malzemeler.contains(malzeme)) {
                    /* Add to main ingredients list while reading from file */
                    //System.out.println("Added " + malzeme.getIsim() + " to malzemeler");
                    malzeme.setKod(ingredientCode);
                        System.out.println("NOT CONTAINS ADDED " + malzeme.getIsim()+ " with id " + malzeme.getKod());
                    malzemeler.add(malzeme);
                    System.out.println(malzemeler);
                    ++ingredientCode;

                }
                else {
                    //int existingMealCode = malzemeler.indexOf(malzeme)-1;
                    //    System.out.println("ADDED " + malzeme.getIsim() + " with id " + malzeme.getKod());
                    //malzeme.setKod(existingMealCode);
                }
            }
            yemek.setIsim(line.split(";")[0]);
            yemek.setMalzemeler(malzemeArrayList);
            yemek.setKategori(line.split(";")[2]);
            yemek.setKalori(Integer.parseInt(line.split(";")[3]));
            String tarif = line.split(";")[4].replace("\\n","\n");
            yemek.setTarif(tarif);
            yemek.setTarifSuresi(line.split(";")[5]);
            yemek.setResim(line.split(";")[6]);
            yemek.setCode(mealCode);
            yemekler.add(yemek);
            ++mealCode;
        }
        for (Yemek yemek : yemekler) {
            for (Yemek yemek1 : yemekler) {
                int ortakMalzemeler = yemeklerinOrtakMalzemeSayisi(yemek, yemek1);
                if((!yemek.equals(yemek1))) {
                    yemeklerCizgesi.insert(yemek.getCode(), yemek1.getCode(), ortakMalzemeler);
                    //System.out.println("inserted " + yemek + " " + yemek1 + " " + ortakMalzemeler);
                    if(yemek.toString().equals("Kakaolu Islak Kek") && yemek1.toString().equals("Keşkül")) {
                        System.out.println("Ortal malzemeleri: " + ortakMalzemeler);
                        System.out.println("graphtaki Ortak malzemeleri: " + yemeklerCizgesi.getEdge(yemek.getCode(),yemek1.getCode()).getWeight());
                    }
                }
            }
        }
        scanner.close();
        Yemek yemek = yemekler.get(18);
        Yemek yemek1 = yemekler.get(15);
        if(yemek.toString().equals("Kakaolu Islak Kek") && yemek1.toString().equals("Keşkül"))
            System.out.println("graphtaki Ortal malzemeleri: " + yemeklerCizgesi.getEdge(yemek.getCode(),yemek1.getCode()).getWeight());
    }

    static int yemeklerinOrtakMalzemeSayisi(Yemek first, Yemek second){
        int result = 0;
        for (Malzeme malzeme : first.getMalzemeler()) {
            for (Malzeme malzeme1 : second.getMalzemeler()) {
                if(malzeme.getIsim().equals(malzeme1.getIsim()))
                    ++result;
            }
        }
        return result;
    }

    static int cizgedenOrtakMalzemeler(Yemek first, Yemek second){
        Edge edge = yemeklerCizgesi.getEdge(first.getCode(),second.getCode());
        if(edge == null)
            return -1;
        System.out.println("first is " + first + " second is " + second + " and weight is " + edge.getWeight());
        return edge.getWeight();
    }

}
