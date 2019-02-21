package com.example.firat.wrong_park;

import android.app.NotificationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class bildirim extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mNotificationManager.cancel(001);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirim);




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Sikayetler");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    Iterator<DataSnapshot> kll = data.getChildren().iterator();
                    while (kll.hasNext()){

                    kullanici_bilgileri kb = kll.next().getValue(kullanici_bilgileri.class);

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    final String userid = user.getUid();
                    final String splaka = kb._Plaka;
                    final String mesaj = kb._mesaj.toString();
                    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                    DatabaseReference myRef2 = database2.getReference();
                    myRef2.child("User_Informations").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data2 : dataSnapshot.getChildren()) {
                                String uid = data2.getKey();
                                if (userid.equals(uid)) {
                                    if (splaka.equals(data2.getValue(register_inf.class)._Plaka)) {
                                        TextView plaka = findViewById(R.id.bildirim_plaka);
                                        TextView sebep = findViewById(R.id.bildirim_sebep);

                                        plaka.setText(splaka);
                                        sebep.setText(mesaj);


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
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



    }

    public void bildirim_onay(View view){

        finish();
    }



}
