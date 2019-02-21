package com.example.firat.wrong_park;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class login_activity extends AppCompatActivity {
    public static final int REQUEST_STORAGE=1;
    private FirebaseAuth auth;
    private String userid;
    private String splaka;
    private String mesaj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        auth = FirebaseAuth.getInstance();


        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText parola = findViewById(R.id.loginparola);
                EditText email = findViewById(R.id.loginemail);

                if (email.getText().length() == 0 || parola.getText().length() == 0)
                    Toast.makeText(getApplicationContext(), "E posta ve ya parola boş olamaz!", Toast.LENGTH_SHORT).show();
                else {
                    auth.signInWithEmailAndPassword(email.getText().toString(), parola.getText().toString())
                            .addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = auth.getCurrentUser();

                                        /*////////////////////////////////////////////////////*/


                                        if(user.getUid().equals("u42ne6xOpPTfVbYinTM3OzIdM3u1")){
                                            Intent intent = new Intent(getApplicationContext(),admin_activity.class);
                                            startActivity(intent);
                                            finish();
                                        }else {Intent intent = new Intent(login_activity.this, lobi.class);
                                            startActivity(intent);
                                            finish();}
                                        /*///////////////////////////////////////////////////*/

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Toast.makeText(login_activity.this, "E posta ya da parola hatalı.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
            }
        });



    }





    private void part2(final String userid, String plaka , String message){

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database2.getReference();
        myRef2.child("User_Informations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data2 : dataSnapshot.getChildren()){
                    String uid = data2.getKey();
                    if(userid.equals(uid)){
                        if( splaka.equals( data2.getValue(register_inf.class)._Plaka) ){


                            Toast.makeText(getApplicationContext(),"İhbar edildiniz",Toast.LENGTH_SHORT).show();

                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }



    public void logintoregisterbutton(View view){

        Intent intent = new Intent(this,register_activity.class);

        startActivity(intent);





    }


}
