package com.secpisir.secpisir;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FerhatMain extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;
    protected static ArrayList<Yemek> yemekler;
    private BinarySearchTree<Yemek> yemeklerBST;

    private SliderAdapter SliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* ------ */
        ArrayList<Integer> yemekKodlari = getIntent().getIntegerArrayListExtra("aramaSonucu");
        System.out.println("yemek kodlari: " + yemekKodlari);
        yemekler = new ArrayList<>(yemekKodlari.size());
        for (int i = 0; i < yemekKodlari.size(); i++) {
            yemekler.add(YönetimSistemi.getYemek(yemekKodlari.get(i)));
        }
        System.out.println("ferhat yemekler: " + yemekler);

        String[] tarifler = new String[yemekler.size()];
        String[] yemekIsimleri = new String[yemekler.size()];
        for (int i = 0; i < tarifler.length; i++) {
            tarifler[i] = yemekler.get(i).getTarif();
            yemekIsimleri[i] = yemekler.get(i).getIsim();
            System.out.print(yemekIsimleri[i]);
        }
        System.out.println();
        /* ------ */
        setContentView(R.layout.slide_main);

        mSlideViewPager = findViewById(R.id.sliderViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);

        SliderAdapter = new SliderAdapter(this, yemekKodlari.size());
        //SliderAdapter.setSlideDescriptions(tarifler);
        SliderAdapter.setSlideHeading(yemekIsimleri);
        mSlideViewPager.setAdapter(SliderAdapter);
        //addDotindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

    }


    /*public void addDotindicator(int position){
        mDots = new TextView[10];
        mDotLayout.removeAllViews();
        for(int i = 0 ; i<mDots.length;i++){

            mDots[i] =new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);

        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }*/
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //addDotindicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void yemekTarifineGec(View view){
        Intent intent = new Intent(this, YemekTarifi.class);
        intent.putExtra("yemekID",yemekler.get( mSlideViewPager.getCurrentItem() ).getCode());
        startActivity(intent);
    }
}
