package com.secpisir.secpisir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    int sliderSize;
    public int[] slide_images;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context, int sliderSize){
        this.context=context;
        this.sliderSize = sliderSize;
        slide_images = new int[sliderSize];
        /*for (int i = 0; i < sliderSize; i++) {
            if(i%2 == 0)
                slide_images[i] = R.drawable.code_icon;
            else
                slide_images[i] = R.drawable.eat_icon;
        }*/
        for (int i = 0;i < sliderSize;i++){
            if(FerhatMain.yemekler.get(i).getIsim().equals("Mercimek Çorbası")){
                slide_images[i] = R.drawable.mercimek_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Yayla Çorbası")){
                slide_images[i] = R.drawable.yayla_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Sütlü Sebze Çorbası")){
                slide_images[i] = R.drawable.sutlu_sebze_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Soğan Çorbası")){
                slide_images[i] = R.drawable.sogan_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Sebzeli Buğday Çorbası")){
                slide_images[i] = R.drawable.sebzeli_bugday_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Kremalı Tavuk Çorbası")){
                slide_images[i] = R.drawable.kremali_tavuk_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Karalahana Çorbası")){
                slide_images[i] = R.drawable.karalahana_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Ezogelin Çorbası")){
                slide_images[i] = R.drawable.ezogelin_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Domates Çorbası")){
                slide_images[i] = R.drawable.domates_corbasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Türlü")){
                slide_images[i] = R.drawable.turlu;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Tavuk Sote")){
                slide_images[i] = R.drawable.tavuk_sote;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Kuru Fasulye")){
                slide_images[i] = R.drawable.kuru_fasulye;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Karnıyarık")){
                slide_images[i] = R.drawable.karniyarik;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("İzmir Köfte")){
                slide_images[i] = R.drawable.izmir_kofte;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Hünkar Beğendi")){
                slide_images[i] = R.drawable.hunkar_begendi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Hamsili Pilav")){
                slide_images[i] = R.drawable.hamsili_pilav;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Fellah Köftesi")){
                slide_images[i] = R.drawable.fellah_koftesi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Zeytinyağlı Fasülye")){
                slide_images[i] = R.drawable.zeytinyagli_taze_fasulye;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Zeytinyağlı Enginar")){
                slide_images[i] = R.drawable.zeytinyagli_enginar;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Paçanga Böreği")){
                slide_images[i] = R.drawable.pacanga_boregi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Kabak Mücveri")){
                slide_images[i] = R.drawable.kabak_mucveri;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Fırında Kaşarlı Mantar")){
                slide_images[i] = R.drawable.firinda_kasarli_mantar;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Avcı Böreği")){
                slide_images[i] = R.drawable.avci_boregi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Şeftali Kompostosu")){
                slide_images[i] = R.drawable.seftali_kompostosu;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Sahlep")){
                slide_images[i] = R.drawable.sahlep;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Reyhan Şerbeti")){
                slide_images[i] = R.drawable.reyhan_serbeti;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Çilekli Limonata")){
                slide_images[i] = R.drawable.cilekli_limonata;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Atom")){
                slide_images[i] = R.drawable.atom;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Yoğurtlu Semizotu Salatası")){
                slide_images[i] = R.drawable.yogurtlu_semizotu_salatasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Roka Salatası")){
                slide_images[i] = R.drawable.roka_salatasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Patates Salatası")){
                slide_images[i] = R.drawable.patates_salatasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Makarna Salatası")){
                slide_images[i] = R.drawable.makarna_salatasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Kuskus Salatası")){
                slide_images[i] = R.drawable.kuskus_salatasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Kısır")){
                slide_images[i] = R.drawable.kisir;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Gavurdağı Salatası")){
                slide_images[i] = R.drawable.gavurdagi_salatasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Babagannus")){
                slide_images[i] = R.drawable.babagannus;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Sütlaç")){
                slide_images[i] = R.drawable.sutlac;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Şekerpare")){
                slide_images[i] = R.drawable.sekerpare;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Revani")){
                slide_images[i] = R.drawable.revani;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Mozaik Pasta")){
                slide_images[i] = R.drawable.mozaik_pasta;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Keşkül")){
                slide_images[i] = R.drawable.keskul;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Kakaolu Islak Kek")){
                slide_images[i] = R.drawable.kakaolu_islak_kek;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Islak Kurabiye")){
                slide_images[i] = R.drawable.islak_kurabiye;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("İrmik Helvası")){
                slide_images[i] = R.drawable.irmik_helvasi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Ayva Tatlısı")){
                slide_images[i] = R.drawable.ayva_tatlisi;

            }
            else if(FerhatMain.yemekler.get(i).getIsim().equals("Aşure")){
                slide_images[i] = R.drawable.asure;

            }



            else {
                slide_images[i] = R.drawable.code_icon;
            }
        }
    }

    public String[] slide_headings={"YEMEĞİN ADI","YEMEĞİN ADI","YEMEĞİN ADI"};
    // public String[] slide_decripsions={"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit."};

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

   /*    public void setSlideDescriptions(String[] strings){
        slide_decripsions = strings;
    }*/

    public void setSlideHeading(String[] strings){
        slide_headings = strings;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView= view.findViewById(R.id.slide_image);
        TextView slide_head= view.findViewById(R.id.slide_head);
        TextView slide_decs= view.findViewById(R.id.slide_desc);


        slideImageView.setImageResource(slide_images[position]);
        slide_head.setText(slide_headings[position]);

        Yemek yemek=YönetimSistemi.getYemek(YönetimSistemi.getYemek(slide_headings[position]));

        slide_decs.setText(yemek.getMalzemelerString());

        container.addView(view);



        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}



