package com.example.firat.wrong_park;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class admin_activity extends AppCompatActivity {

    private ArrayList<kullanici_bilgileri> bilgiler;
    private ListView listView;
    private Integer i = 0;
    private String[] a;
    private ArrayList<String> plakalar;
    private ArrayList<String> saatler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activity);
        plakalar=new ArrayList<>();
        saatler = new ArrayList<>();


        listView = findViewById(R.id.admin_list);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Sikayetler");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                bilgiler = new ArrayList<kullanici_bilgileri>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Iterator<DataSnapshot> iter = data.getChildren().iterator();
                    while (iter.hasNext()) {

                        kullanici_bilgileri kb = iter.next().getValue(kullanici_bilgileri.class);



                            plakalar.add(kb._Plaka);

                            saatler.add(kb._time);
                        String []hour = kb._time.split(":");
                        String id = hour[0]+hour[1]+hour[2];


                        start(Integer.valueOf( hour[0] ),Integer.valueOf( hour[1] ) ,Integer.valueOf(id) ,
                                kb._userid,kb._Plaka,kb._time);



                        i++;
                        bilgiler.add(kb);
                    }

                }


                a = new String[i];
                for (int j = 0; i > j; j++) {

                    a[j] = bilgiler.get(j)._Plaka;


                }

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, android.R.id.text1, a);
                listView.setAdapter(null);
                listView.setAdapter(arrayAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        dialog_fragment dialog_fragment = new dialog_fragment();
                        dialog_fragment.mesaj_al(bilgiler.get(i)._konum, bilgiler.get(i)._mesaj, bilgiler.get(i)._mesaj_konu,bilgiler.get(i)._time);
                        dialog_fragment.show(getSupportFragmentManager(), "a");







                    }
                });


                i = 0;




            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });





    }


    public void start(Integer saat , Integer dakika,Integer id,String userid,String plaka,String time) {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Date dat = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY,saat);
        cal_alarm.set(Calendar.MINUTE,dakika+1);
        cal_alarm.set(Calendar.SECOND,0);
        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }
        PendingIntent pendingIntent;
        Intent myIntent = new Intent(getApplicationContext(), AlarmReceiver.class);

        myIntent.putExtra("reqcode",userid);
        myIntent.putExtra("plaka",plaka);
        myIntent.putExtra("time",time);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), id, myIntent, 0);

        manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
    }











}

