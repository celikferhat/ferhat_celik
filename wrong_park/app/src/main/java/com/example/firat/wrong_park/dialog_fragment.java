package com.example.firat.wrong_park;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class dialog_fragment extends DialogFragment {
    private TextView textView;
    private String _konum;
    private String _mesaj;
    private String _mesaj_konusu;
    private String _time;


   public void mesaj_al(String konum,String mesaj,String mesaj_konusu,String time){
       _konum = konum;
       _mesaj = mesaj;
       _mesaj_konusu = mesaj_konusu;
       _time = time;



   }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialoglayout,container,false);
        textView=view.findViewById(R.id.dialog_text);
        textView.setText("Konum: "+ _konum+"\n"+"Mesaj: "+_mesaj+
                "\nMesaj Konusu: "+_mesaj_konusu+"\nZaman: "+_time);


        return view;
    }
}
