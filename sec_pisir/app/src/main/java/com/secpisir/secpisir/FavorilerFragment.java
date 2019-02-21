package com.secpisir.secpisir;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class FavorilerFragment extends Fragment {
    private Button TextButton;
    private Button CloseButton;

    private static  String BUTTON_TEXT = "ButtonText";
    private OnFragmentInteractionListener mListener;

    public FavorilerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     * @param text button name.
     * @return A new instance of fragment FavorilerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavorilerFragment newInstance(String text) {
        FavorilerFragment fragment = new FavorilerFragment();
        Bundle args = new Bundle();
        args.putString(BUTTON_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //*******Karaliste için değişecek kısım
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.favoriler_fragment, container, false);

        TextButton = view.findViewById(R.id.text_button);
        CloseButton = view.findViewById(R.id.close_button);
        CloseButton.setOnClickListener(new FragmentClickListener(this));
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentClose(FavorilerFragment fr);
    }
    class FragmentClickListener implements View.OnClickListener {
        FavorilerFragment fr;
        FragmentClickListener(FavorilerFragment fr) {
            this.fr = fr;
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onFragmentClose(fr);
                YönetimSistemi.getCurrentKullanici().getFavoriListe().remove(TextButton.getText().toString());
                new sil().execute(TextButton.getText().toString(),"fav");
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






                        if(urls[1].equals("fav")){
                            String favs[] = rs.getString("favoriler").split("-");
                            String yeni="";
                            for(String s : favs)
                            {
                                if(!s.equals(urls[0]) && s.length()>1)
                                {
                                    yeni += "-"+s;
                                }



                            }






                            st.executeUpdate("UPDATE kullanici_bilgileri SET favoriler="+"'"+yeni+"'"+" WHERE user_id='"+MainScreen.user_id+"'"  );



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




        }



    }





}
