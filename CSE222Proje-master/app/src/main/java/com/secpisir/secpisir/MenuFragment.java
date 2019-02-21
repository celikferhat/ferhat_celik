package com.secpisir.secpisir;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View navigationView = getView();
        if (navigationView != null) {
            ((NavigationView)navigationView).setItemIconTintList(null);
        }
    }

    /*public void menudenKaralisteye(MenuItem item) {
        Intent intent = new Intent(getActivity(), Karaliste.class);
        startActivity(intent);
    }

    public void menudenFavorilere(MenuItem item) {
        Intent intent = new Intent(getActivity(), Favoriler.class);
        startActivity(intent);
    }

    public void menudenGecmise (MenuItem item) {
        Intent intent = new Intent(getActivity(), Gecmis.class);
        startActivity(intent);
    }

    public void anaEkrandanAramaya(View view) {
        Log.i("TAG", "deneme");
        Intent intent = new Intent(getActivity(), IngredientActivity.class);
        startActivity(intent);
    }

    public void anaEkrandanKategorilere(View view) {
        Intent intent = new Intent(getActivity(), KategorilerActivity.class);
        startActivity(intent);
    }*/
}
