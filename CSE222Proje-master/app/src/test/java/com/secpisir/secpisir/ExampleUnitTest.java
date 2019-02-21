package com.secpisir.secpisir;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void yemekleriDosyadanOku(){
        try {
            Process p = Runtime.getRuntime().exec("pwd");
            BufferedReader ips = new BufferedReader(new InputStreamReader(p.getInputStream()));
            System.out.println(ips.readLine());
            YönetimSistemi yönetimSistemi = new YönetimSistemi();
            InputStream kullanici = new FileInputStream("src//main//res//raw//kullanici.csv");
            InputStream yemek = new FileInputStream("src//main//res//raw//yemek.csv");
            yönetimSistemi.setKullaniciInputStream(kullanici);
            yönetimSistemi.setYemekInputStream(yemek);
            YönetimSistemi.yemekTarifleriniDosyadanOku();
            System.out.println(YönetimSistemi.getYemekler().get(0).getMalzemeler());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void yemeklerinOrtakMalzemeSayisi(){
        try {
            YönetimSistemi yönetimSistemi = new YönetimSistemi();
            InputStream yemek = new FileInputStream("src//main//res//raw//yemek.csv");
            yönetimSistemi.setYemekInputStream(yemek);
            YönetimSistemi.yemekTarifleriniDosyadanOku();
            Yemek yemek1 = YönetimSistemi.getYemek(18);
            Yemek yemek2 = YönetimSistemi.getYemek(15);
            System.out.println(YönetimSistemi.yemeklerinOrtakMalzemeSayisi(yemek1,yemek2));
            System.out.println(YönetimSistemi.cizgedenOrtakMalzemeler(yemek1,yemek2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void kullaniciyaOzelYemekOner(){
        try {
            YönetimSistemi yönetimSistemi = new YönetimSistemi();
            InputStream kullanici = new FileInputStream("src//main//res//raw//kullanici.csv");
            InputStream yemek = new FileInputStream("src//main//res//raw//yemek.csv");
            yönetimSistemi.setKullaniciInputStream(kullanici);
            yönetimSistemi.setYemekInputStream(yemek);
            YönetimSistemi.yemekTarifleriniDosyadanOku();
            YönetimSistemi.listedenKullanicilariOku();
            System.out.println(YönetimSistemi.getKullaniciSet().size());
            for (Object o : YönetimSistemi.getKullaniciSet()) {
                Kullanici k = (Kullanici)o;
                if(k.getKullaniciAdi().equals("ec") == false)
                    continue;
                ArrayList<Yemek> suggestions = YönetimSistemi.kullaniciyaOzelYemekOner(k);
                System.out.println("Suggestions for " + k.getIsim()+":"
                    + suggestions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void malzemedenYemekOner(){
        YönetimSistemi yönetimSistemi = new YönetimSistemi();
        try {
            InputStream kullanici = new FileInputStream("src//main//res//raw//kullanici.csv");
            InputStream yemek = new FileInputStream("src//main//res//raw//yemek.csv");
            yönetimSistemi.setKullaniciInputStream(kullanici);
            yönetimSistemi.setYemekInputStream(yemek);
            YönetimSistemi.yemekTarifleriniDosyadanOku();
            ArrayList<Malzeme> malzemes = new ArrayList<>(2);
            malzemes.add(YönetimSistemi.getMalzeme(30));
            ArrayList<Yemek> sonuc = YönetimSistemi.malzemedenYemekOner(malzemes);
            System.out.println(sonuc);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Test
    public void kullaniciDogrula(){
        try {
            initializeYönetimSistemi();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String kisim = "ssaricayir", ksifre = "s12sasa";
        System.out.print(YönetimSistemi.kullaniciDogrula(kisim,ksifre));
    }

    public void initializeYönetimSistemi() throws FileNotFoundException {
        YönetimSistemi yönetimSistemi = new YönetimSistemi();
        InputStream kullanici = new FileInputStream("src//main//res//raw//kullanici.csv");
        InputStream yemek = new FileInputStream("src//main//res//raw//yemek.csv");
        yönetimSistemi.setKullaniciInputStream(kullanici);
        yönetimSistemi.setYemekInputStream(yemek);
        YönetimSistemi.yemekTarifleriniDosyadanOku();
    }

    @Test
    public void priorityQueue(){
    }
}