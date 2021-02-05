package com.example.rentyproject;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FieldsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FieldsFragment extends Fragment {
    CardView cardView ;
    LinearLayout field ;
    LinearLayout subField ;
    LinearLayout subFieldList ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FieldsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FieldsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FieldsFragment newInstance(String param1, String param2) {
        FieldsFragment fragment = new FieldsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*        View rootView = inflater.inflate(R.layout.fragment_fields, container, false);
        cardView = (CardView) rootView.findViewById(R.id.fieldCardView);
        field  = (LinearLayout) rootView.findViewById(R.id.FieldLayout);
        subField = (LinearLayout) rootView.findViewById(R.id.subfieldLayout);
        subFieldList = (LinearLayout) rootView.findViewById(R.id.subFieldsList);
        ArrayList<View> subfields = new ArrayList();
        subfields.add(subField);
        for(int i = 0 ; i < 5 ; i++){
            View subView  = LayoutInflater.from(getActivity()).inflate(R.layout.subfield, null, false);
            LinearLayout dynamicSubField = subView.findViewById(R.id.subfieldLayout);
            subView.setId(i);
            TextView subText = subView.findViewById(R.id.subfieldname);
            subText.setText("SubField" + i);
            subfields.add(subView);
            subFieldList.addView(subView);
        }
        field.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(subField.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    for(int j = 0 ; j<subfields.size() ; j++){
                        subfields.get(j).setVisibility(View.GONE);
                    }

                }
                else{
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    for(int j = 0 ; j<subfields.size() ; j++){
                        subfields.get(j).setVisibility(View.VISIBLE);
                    }

                }
            }
        });*/
        LinearLayout rootView =(LinearLayout) inflater.inflate(R.layout.fragment_fields, container, false);
        for(int j = 0 ; j < 3 ; j++){
            CardView fieldCard =(CardView)  LayoutInflater.from(getActivity()).inflate(R.layout.fieldcard,container,false);
            CardView.LayoutParams layoutParams = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 14, 0, 14);
            fieldCard.setLayoutParams(layoutParams);
            fieldCard.setId(j*j);
            LinearLayout dynamicField = (LinearLayout) fieldCard.findViewById(R.id.FieldLayout);
            dynamicField.setId(j);
            ArrayList<View> dynamicSubfields = new ArrayList<>();

            LinearLayout dynamicSubFieldList = (LinearLayout) fieldCard.findViewById(R.id.subFieldsList);
            dynamicSubFieldList.setId(j*j*j);
            for(int i = 0 ; i < 5 ; i++){
                View subView  = LayoutInflater.from(getActivity()).inflate(R.layout.subfield, null, false);
               // LinearLayout dynamicSubField = subView.findViewById(R.id.subfieldLayout);
                subView.setId(i);
                TextView subText = subView.findViewById(R.id.subfieldname);
                subText.setText("SubField" + i*i);
                dynamicSubfields.add(subView);
                dynamicSubFieldList.addView(subView);
            }
            dynamicField.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(dynamicSubfields.get(0).getVisibility() == View.VISIBLE){
                        TransitionManager.beginDelayedTransition(fieldCard,
                                new AutoTransition());
                        for(int j = 0 ; j<dynamicSubfields.size() ; j++){
                            dynamicSubfields.get(j).setVisibility(View.GONE);
                        }

                    }
                    else{
                        TransitionManager.beginDelayedTransition(fieldCard,
                                new AutoTransition());
                        for(int j = 0 ; j<dynamicSubfields.size() ; j++){
                            dynamicSubfields.get(j).setVisibility(View.VISIBLE);
                        }

                    }
                }
            });
            LinearLayout rootFields = rootView.findViewById(R.id.rootFields);
            rootFields.addView(fieldCard);


        }

        return rootView ;
    }
}