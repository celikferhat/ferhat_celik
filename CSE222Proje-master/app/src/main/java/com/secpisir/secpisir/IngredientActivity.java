package com.secpisir.secpisir;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class IngredientActivity extends AppCompatActivity implements IngredientFragment.OnFragmentInteractionListener {

    LinearLayout basket;
    SearchView searchView;
    ListView searchIngredient;
    ArrayAdapter<String> adapter;
    ArrayList<Malzeme> secilenMalzemeler = new ArrayList<>(5);
    MenuItem item;

    private void addFragment(int id, String buttonName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        IngredientFragment ingredientFragment = IngredientFragment.newInstance(buttonName);
        fragmentTransaction.add(id, ingredientFragment);
        IngredientFragment fr = (IngredientFragment) fragmentManager.findFragmentById(id);
        fragmentTransaction.commit();
        //TODO seperate commit from that function


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ingredient);

        searchIngredient = findViewById(R.id.search_ingredient_list);
        searchView = findViewById(R.id.search_actionbar);

        ArrayList<String> ingredientArray = YönetimSistemi.getMalzemeIsimleri();
        //ArrayList<String> ingredientArray = new ArrayList<>();
        //ingredientArray.addAll(Arrays.asList(getResources().getStringArray(R.array.dummy_ingredients)));

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ingredientArray);

        searchIngredient.setAdapter(adapter);
        //addFragment(R.id.ingredient_container);

        basket = (LinearLayout) findViewById(R.id.basket_linear_layout);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        item = menu.findItem(R.id.search_actionbar);
        final SearchView searchView = (SearchView) item.getActionView();

        final IngredientActivity cntx = this;
        final LinearLayout linearly = findViewById(R.id.basket_linear_layout);

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {

            @Override
            public boolean onSuggestionSelect(int position) {
                searchView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Log.i("TAG", "onSuggestionClick: ");
                Cursor cursor= searchView.getSuggestionsAdapter().getCursor();
                cursor.moveToPosition(position);
                String suggestion = cursor.getString(2);//2 is the index of col containing suggestion name.
                searchView.setQuery(suggestion,true);//setting suggestion
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchIngredient.setVisibility(View.GONE);
                FrameLayout tempLay = new FrameLayout(cntx);
                int id = View.generateViewId();
                tempLay.setId(id);
                linearly.addView(tempLay);
                //TODO: change line below back if necessary
                //addFragment(id, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }


        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchIngredient.setVisibility(View.VISIBLE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchIngredient.setVisibility(View.GONE);
                return false;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    searchView.setIconified(true);
            }
        });

        searchIngredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Object entry = parent.getAdapter().getItem(position);
                //searchIngredient.setVisibility(View.GONE);
                FrameLayout tempLay = new FrameLayout(cntx);
                int idNew = View.generateViewId();
                tempLay.setId(idNew);
                linearly.addView(tempLay);
                /* --- */
                for (int i = 0; i < YönetimSistemi.getMalzemeler().size(); i++) {
                    if(entry.toString().equals(YönetimSistemi.getMalzeme(i).toString())) {
                        if (secilenMalzemeler.size() < 4) {
                            boolean exists = false;
                            for (int j = 0; j < secilenMalzemeler.size(); j++) {
                                if (entry.toString().equals(secilenMalzemeler.get(j).toString())) {
                                    Toast.makeText(searchView.getContext(), R.string.zaten_var, Toast.LENGTH_SHORT).show();
                                    exists = true;
                                    break;
                                }

                            }

                            if (!exists) {
                                secilenMalzemeler.add(YönetimSistemi.getMalzeme(i));
                                addFragment(idNew, entry.toString());

                            }
                        }

                        else
                            Toast.makeText(searchView.getContext(), R.string.fazla_malzeme_secildi, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                /* --- */
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFragmentClose(IngredientFragment fr) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fr);
        for (int i = 0; i < secilenMalzemeler.size(); i++) {
            if (secilenMalzemeler.get(i).toString().equals(fr.getIngredientTextButton().getText().toString()))
                secilenMalzemeler.remove(i);
            break;
        }

        fragmentTransaction.commit();
    }

    public void yemekOnerisineGec(View view){
        if (secilenMalzemeler.size() == 0) {
            Toast.makeText(view.getContext(), R.string.malzemeler_bos, Toast.LENGTH_SHORT).show();
            return;
        }


        if (basket.getChildCount() > 0)
            basket.removeAllViews();

        System.out.println("secilen malzemeler:" + secilenMalzemeler);
        ArrayList<Yemek> sonuc = YönetimSistemi.malzemedenYemekOner(secilenMalzemeler);
        if(sonuc.size() == 0){
            Intent intent = new Intent(this, TarifBulunamadi.class);
            startActivity(intent);
        }
        else {
            System.out.println("sonuc size:" + sonuc.size() + " sonuc :" + sonuc);
            Intent intent = new Intent(this, FerhatMain.class);
            ArrayList<Integer> sonucIDleri = new ArrayList<>(sonuc.size());
            for (int i = 0; i < sonuc.size(); i++) {
                sonucIDleri.add(sonuc.get(i).getCode());
                System.out.println("Added to sonucIDleri :" + sonucIDleri.get(i));
            }
            System.out.println("sonucIDleri :" + sonucIDleri);
            intent.putExtra("aramaSonucu", sonucIDleri);
            startActivity(intent);
        }
    }


}