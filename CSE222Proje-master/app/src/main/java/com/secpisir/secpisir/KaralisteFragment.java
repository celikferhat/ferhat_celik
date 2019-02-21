package com.secpisir.secpisir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KaralisteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KaralisteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KaralisteFragment extends Fragment {

    String liste;
    private Button TextButton;
    private Integer user_id;
    private Button CloseButton;
    private static  String BUTTON_TEXT = "ButtonText";
    private KaralisteFragment.OnFragmentInteractionListener mListener;
    public KaralisteFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     * @param text button name.
     * @return A new instance of fragment KaralisteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KaralisteFragment newInstance(String text) {
        KaralisteFragment fragment = new KaralisteFragment();
        Bundle args = new Bundle();
        args.putString(BUTTON_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_karalistee, container, false);

        TextButton = view.findViewById(R.id.t_button);
        CloseButton = view.findViewById(R.id.c_button);
        CloseButton.setOnClickListener(new KaralisteFragment.FragmentClickListener(this));
        TextButton.setText(getArguments().getString(BUTTON_TEXT));

        TextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //Start your activity here
                Intent intent = new Intent(view.getContext(), YemekTarifi.class);
                intent.putExtra("yemekID",YönetimSistemi.getYemek(TextButton.getText().toString()));
                startActivity(intent);
            }
        });
        return view;
    }
    /*
        public void yemekTarifineGec(View view){
            Intent intent = new Intent(this, YemekTarifi.class);
            intent.putExtra("yemekID",BUTTON_TEXT);
            startActivity(intent);
        }*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof KaralisteFragment.OnFragmentInteractionListener) {
            mListener = (KaralisteFragment.OnFragmentInteractionListener) context;



        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentClose(KaralisteFragment fr);
    }

    class FragmentClickListener implements View.OnClickListener {
        KaralisteFragment fr;
        FragmentClickListener(KaralisteFragment fr) {
            this.fr = fr;
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onFragmentClose(fr);
                if (liste.equals("kara")){
                    YönetimSistemi.getCurrentKullanici().getKaraListe().remove(TextButton.getText().toString());
                    System.out.println("SİLİNDİ");
                    new sil().execute(TextButton.getText().toString(),"kara");

                }
                else{
                    YönetimSistemi.getCurrentKullanici().getGecmis().remove(TextButton.getText().toString());
                    new sil().execute(TextButton.getText().toString(),"gecmis");


                }
            }

        }
    }




    private class sil extends AsyncTask<String, String,String> {

        protected String doInBackground(String... urls) {




            try {
                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://89.19.30.91/u7404772_ferhat?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey&characterEncoding=UTF-8";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "u7404772_ferhat1", "FerhaT2018");

                Statement st = conn.createStatement();
                String query = "SELECT * FROM kullanici_bilgileri";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){

                    if (rs.getString("user_id").equals( MainScreen.user_id.toString()  ) ) {






                        if(urls[1].equals("kara")){
                            String favs[] = rs.getString("kara_liste").split("-");
                            String yeni="";
                            for(String s : favs)
                            {
                                if(!s.equals(urls[0]) && s.length() > 1)
                                {
                                    yeni += "-"+s;
                                }



                            }






                            st.executeUpdate("UPDATE kullanici_bilgileri SET kara_liste="+"'"+yeni+"'"+" WHERE user_id='"+MainScreen.user_id+"'"  );



                        }
                        if(urls[1].equals("gecmis")) {
                            String favs[] = rs.getString("gecmis").split("-");
                            String yeni="";
                            for(String s : favs)
                            {
                                if(!s.equals(urls[0]) && s.length() > 1)
                                {

                                    yeni += "-"+s;
                                }



                            }




                            st.executeUpdate("UPDATE kullanici_bilgileri SET gecmis=" + "'" + yeni + "'" + " WHERE user_id='" + MainScreen.user_id + "'");

                        }


                    }
                }





                conn.close();





            }
            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }




            return null;
        }





        @Override
        protected void onProgressUpdate(String... values) {


            for(String s : values)
            {



            }


        }



    }



}
