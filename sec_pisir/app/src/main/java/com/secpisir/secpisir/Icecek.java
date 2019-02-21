package com.secpisir.secpisir;
import java.util.LinkedList;

public class Icecek extends Tüketilebilir{

    private LinkedList<Malzeme> malzemeler;
    private String tarif;

    public LinkedList<Malzeme> getMalzemeler() {
        return malzemeler;
    }

    @Override
    public String getMalzeme() {
        return null;
    }

    @Override
    public String getTarif() {
        return tarif;
    }

    @Override
    public int getKalori() {
        return Kalori;
    }
}
