package com.secpisir.secpisir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

public class MenuTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        NavigationView navigationView = findViewById(R.id.menuNavigationView);
        if (navigationView != null) {
            navigationView.setItemIconTintList(null);
        }
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        NavigationView navigationView = findViewById(R.id.menuNavigationView);
        if (navigationView != null) {
            navigationView.setItemIconTintList(null);
            System.err.println("ALRIGHT");
 //           Scanner scan = new Scanner(System.in);
//            scan.next();
        }
        else{
            navigationView.setItemIconTintList(null);
        }
    }

    public void menudenKaralisteye(MenuItem item){
        Intent intent = new Intent(this, Karaliste.class);
        startActivity(intent);
    }

    public void menudenFavorilere(MenuItem item) {
        Intent intent = new Intent(this, Favoriler.class);
        startActivity(intent);
    }

    public void menudenGecmise(MenuItem item) {
        Intent intent = new Intent(this, Gecmis.class);
        startActivity(intent);
    }

}
