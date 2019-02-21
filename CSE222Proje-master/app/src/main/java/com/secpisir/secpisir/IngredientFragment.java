package com.secpisir.secpisir;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IngredientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IngredientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientFragment extends Fragment {
    private Button ingredientTextButton;
    private Button ingredientCloseButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String BUTTON_TEXT = "ButtonText";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String settText;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IngredientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment IngredientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IngredientFragment newInstance(String buttonText) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putString(BUTTON_TEXT, buttonText);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            settText = getArguments().getString(BUTTON_TEXT);
            //mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        // Inflate the layout for this fragment
        ingredientTextButton = view.findViewById(R.id.ingredient_text_button);
//
//        ingredientTextButton.setText(mParam1);
        ingredientCloseButton = view.findViewById(R.id.ingredient_close_button);

        ingredientCloseButton.setOnClickListener(new FragmentClickListener(this));
        ingredientTextButton.setText(getArguments().getString(BUTTON_TEXT));

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
        void onFragmentClose(IngredientFragment fr);
    }

    public Button getIngredientCloseButton() {
        return ingredientCloseButton;
    }

    public Button getIngredientTextButton() {
        return ingredientTextButton;
    }

    private class FragmentClickListener implements View.OnClickListener {
        IngredientFragment fr;
        FragmentClickListener(IngredientFragment fr) {
            this.fr = fr;
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onFragmentClose(fr);
            }

        }
    }


}