package com.secpisir.secpisir;
import android.support.annotation.NonNull;

import java.util.ArrayList;

public class Yemek extends Tüketilebilir implements Comparable<Yemek> {

    private ArrayList<Malzeme> malzemeler;
    private String hazirlanis;
    private String kategori;
    private String isim;
    private String hazirlanisSuresi;
    private int code;
    private String resim;

    public Yemek(){

    }
    public Yemek(ArrayList<Malzeme> yeniMalzemeler, String kategori ,String hazirlanis,String hazirlanisSuresi,String resim){

        malzemeler = yeniMalzemeler;
        this.kategori = kategori;
        this.hazirlanis = hazirlanis;
        this.hazirlanisSuresi = hazirlanisSuresi;
        this.resim=resim;

    }

    public boolean containsMalzeme(Malzeme malzeme){
        for (Malzeme malzeme1 : malzemeler) {
            if(malzeme1.equals(malzeme))
                return true;
        }
        return false;
    }

    public ArrayList<Malzeme> getMalzemeler() {
        return malzemeler;
    }

    public String getMalzemelerString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Malzemeler:"+"\n");
        for (Malzeme malzeme:malzemeler)
            sb.append(" "+malzeme.getIsim()+"\n");
        return sb.toString();
    }

    public String getKategori(){
        return kategori;
    }

    @Override
    public String getMalzeme() {
        return null;
    }

    @Override
    public String getTarif() {
        return hazirlanis;
    }

    @Override
    public int getKalori() {
        return Kalori;
    }

    public String getIsim() {
        return isim;
    }

    public int getCode() {
        return code;
    }

    public String getHazirlanisSuresi(){return hazirlanisSuresi;}

    public void setTarif(String hazirlanis) {
        this.hazirlanis = hazirlanis;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setTarifSuresi(String hazirlanisSuresi) {
        this.hazirlanisSuresi = hazirlanisSuresi;
    }

    public void setMalzemeler(ArrayList<Malzeme> malzemeler) {
        this.malzemeler = malzemeler;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setKalori(int kalori){
        this.Kalori = kalori;
    }
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return isim;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Yemek))
            return false;
        return isim.equals(((Yemek) obj).isim);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public int compareTo(@NonNull Yemek o) {
        return isim.compareTo(o.isim);
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
}
