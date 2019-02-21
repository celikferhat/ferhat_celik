package com.example.firat.wrong_park;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        String userid =  intent.getExtras().get("reqcode").toString();
        String plaka  =  intent.getExtras().get("plaka").toString();
        String time = intent.getExtras().get("time").toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Notifications");

        notify n = new notify(plaka,time);
        myRef.child(userid).setValue(n);







    }
}
