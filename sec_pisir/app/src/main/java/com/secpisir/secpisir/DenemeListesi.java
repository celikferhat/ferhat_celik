package com.secpisir.secpisir;

import java.util.LinkedList;


public class DenemeListesi {

    private LinkedList<String> deneme_karaliste=new LinkedList<>();

    public DenemeListesi()
    {
        create();
    }

    private void create()
    {

        deneme_karaliste.add("Pırasa");
        deneme_karaliste.add("Ispanak");
        deneme_karaliste.add("Domates Çorbası");
    }
    public LinkedList<String> getDeneme_liste(){  return deneme_karaliste;}
    public int size(){ return deneme_karaliste.size();}

}