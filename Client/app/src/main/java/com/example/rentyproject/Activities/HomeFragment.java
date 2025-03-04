package com.example.rentyproject.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rentyproject.R;
import com.example.rentyproject.data.APIfactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View fragment= inflater.inflate(R.layout.fragment_home, container, false);
        LoadPosts(fragment,container);
        return fragment ;
    }
    public void LoadPosts( View rootView ,ViewGroup container ){

        APIfactory apIfactory = new APIfactory(getActivity().getBaseContext());
        apIfactory.GetPosts().observe(this,postsArray->{
            if(postsArray.size()>0){
                Log.i("Get Posts","i got it");
                Log.i("Get Posts", String.valueOf(postsArray));
                try {
                    PostsLoader(postsArray,rootView,container);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void PostsLoader(ArrayList<JSONObject> arr, View rootView , ViewGroup container) throws JSONException {
        LinearLayout myOldPostsHolder =  rootView.findViewById(R.id.PostsList);
        myOldPostsHolder.removeAllViews();
        for(int i = 0 ; i < arr.size() ; i++ ){
            View oldPost =  LayoutInflater.from(
                    getActivity()).inflate(R.layout.post,container,false);
            TextView subField = oldPost.findViewById(R.id.subField);
            TextView city = oldPost.findViewById(R.id.cityName);
            TextView desc = oldPost.findViewById(R.id.descriptionTag);
            TextView price = oldPost.findViewById(R.id.priceTag);
            price.setText(arr.get(i).getString("title"));
            desc.setText(arr.get(i).getString("description"));
            city.setText(arr.get(i).getString("field"));
            subField.setOnClickListener(view->{
                Intent intent = new Intent(rootView.getContext(), FieldActivity.class);
                startActivity(intent);
            });
            myOldPostsHolder.addView(oldPost);

        }

    }
}