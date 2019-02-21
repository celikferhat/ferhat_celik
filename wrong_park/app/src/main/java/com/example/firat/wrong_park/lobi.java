package com.example.firat.wrong_park;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class lobi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobi);





        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Sikayetler");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot data : dataSnapshot.getChildren()){

                    Iterator<DataSnapshot> kll = data.getChildren().iterator();
                        while (kll.hasNext()){
                        kullanici_bilgileri kb = kll.next().getValue(kullanici_bilgileri.class);

                        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                        final String splaka=kb._Plaka;


                        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                        DatabaseReference myRef2 = database2.getReference();
                        myRef2.child("User_Informations").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot data2 : dataSnapshot.getChildren()){
                                    String uid = data2.getKey();
                                    if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(uid)){
                                        if( splaka.equals( data2.getValue(register_inf.class)._Plaka) ){

                                            notification(splaka);


                                        }
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





                    }



                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference noti = database.getReference("Notifications");
                noti.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot data : dataSnapshot.getChildren()){

                            if(data.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                            {
                                notify nb = data.getValue(notify.class);
                                notification_time("Şikayetiniz üzerinden 15 dk geçti");
                            }




                        }





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });









            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });





    }



    private void notification(String plaka){




        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"personal notification");
        builder.setSmallIcon(R.drawable.ic_announcement);
        builder.setContentTitle("Hatalı Park Yaptınız!");
        builder.setContentText(plaka);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, bildirim.class), PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(contentIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(001, builder.build());





    }

    private void notification_time(String mesaj){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"personal notification");
        builder.setSmallIcon(R.drawable.ic_announcement);
        builder.setContentTitle("Lütfen geri bildirim yapın!");
        builder.setContentText(mesaj);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, geri_bildirim.class), PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(contentIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(002, builder.build());
    }

    public void destegeyolla(View view) {
    }

    public void kullanicibilgilerineyolla(View view) {
    }

    public void bildirimeyolla(View view) {
        Intent intent = new Intent(this,bildirim.class);
        startActivity(intent);
    }

    public void anaekranayolla(View view) {
        Intent intent = new Intent(this,ana_ekran.class);
        startActivity(intent);
    }
}
