package com.example.firat.wrong_park;

import android.app.NotificationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class geri_bildirim extends AppCompatActivity {


    private notify n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geri_bildirim);
    }


    @Override
    protected void onStart() {
        super.onStart();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mNotificationManager.cancel(002);
    }

    public void evet(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Notifications");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    n = data.getValue(notify.class);
                    if (data.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef2 = database2.getReference("Sikayetler");
                        myRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                                for(DataSnapshot data2 : dataSnapshot.child(n._plaka).getChildren()){


                                    kullanici_bilgileri kbb = data2.getValue(kullanici_bilgileri.class);
                                    if(kbb._time.equals(n._zaman))
                                    {
                                        kbb._durum=2;
                                        myRef2.child(n._plaka).child( n._zaman ).setValue(kbb);
                                    }

                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });








                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
        finish();
    }

    public void hayır(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Notifications");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             for(DataSnapshot data : dataSnapshot.getChildren()){
                 n = data.getValue(notify.class);
                if (data.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef2 = database2.getReference("Sikayetler");
                    myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                            for(DataSnapshot data2 : dataSnapshot.child(n._plaka).getChildren()){


                                kullanici_bilgileri kbb = data2.getValue(kullanici_bilgileri.class);
                                if(kbb._time.equals(n._zaman))
                                {
                                    kbb._durum=1;
                                    myRef2.child(n._plaka).child( n._zaman ).setValue(kbb);
                                }

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });








                }

            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();







        finish();

    }
}
