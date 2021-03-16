package com.example.rentyproject.Activities;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentyproject.Model.Post;
import com.example.rentyproject.R;
import com.example.rentyproject.data.APIfactory;
import com.securepreferences.SecurePreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class MyPostsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final List<String> spinnerArray1 =  new ArrayList<String>();
    private final List<String> spinnerArray2 =  new ArrayList<String>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String USERNAME = "USERNAME";
    public MyPostsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPostsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPostsFragment newInstance(String param1, String param2) {
        MyPostsFragment fragment = new MyPostsFragment();
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
        SharedPreferences prefs = new SecurePreferences(getContext());
        String value = prefs.getString( "username", null );
        Toast.makeText(getContext(),"hello "+value,Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myPosts =  inflater.inflate(R.layout.fragment_my_posts, container, false);
        CardView cardView = myPosts.findViewById(R.id.newPostCard);
        Button newPostButton = (Button) myPosts.findViewById(R.id.newPostButton);
        LinearLayout newPostCard = (LinearLayout) myPosts.findViewById(R.id.mynewPostCard);


        spinnerArray1.add("Field  1");
        spinnerArray1.add("Field  2");
        spinnerArray1.add("Field  3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity().getBaseContext(), android.R.layout.simple_spinner_item, spinnerArray1);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) myPosts.findViewById(R.id.field);
        sItems.setAdapter(adapter);
        spinnerArray2.add("sub Field  1");
        spinnerArray2.add("sub Field  2");
        spinnerArray2.add("sub Field  3");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                getActivity().getBaseContext(), android.R.layout.simple_spinner_item, spinnerArray2);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems2 = (Spinner) myPosts.findViewById(R.id.spinner2);
        sItems2.setAdapter(adapter2);
        newPostButton.setOnClickListener(view->{
            if(newPostCard.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView,
                        new AutoTransition());
                newPostCard.setVisibility(View.GONE);
                newPostButton.setVisibility(View.VISIBLE);


            }
            else{
                TransitionManager.beginDelayedTransition(cardView,
                        new AutoTransition());
                newPostCard.setVisibility(View.VISIBLE);
                newPostButton.setVisibility(View.GONE);


            }
        });
        Button cancelButton = (Button) myPosts.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(view->{
            if(newPostCard.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView,
                        new AutoTransition());
                newPostCard.setVisibility(View.GONE);
                newPostButton.setVisibility(View.VISIBLE);


            }
            else{
                TransitionManager.beginDelayedTransition(cardView,
                        new AutoTransition());
                newPostCard.setVisibility(View.VISIBLE);
                newPostButton.setVisibility(View.GONE);


            }
        });
        LoadOldPosts(myPosts,container);
        Button publishPost = (Button)myPosts.findViewById(R.id.post);
        publishPost.setOnClickListener(view->{
            String field = sItems.getSelectedItem().toString();
            String price = ((TextView) myPosts.findViewById(R.id.price)).getText().toString();
            String city = ((TextView) myPosts.findViewById(R.id.city)).getText().toString();
            String desc = ((TextView) myPosts.findViewById(R.id.description)).getText().toString();
            SharedPreferences prefs = new SecurePreferences(getContext());
            String value = prefs.getString( "username", null );
            Post p = new Post(value,field,price,city,desc);
            PublishPost(p);
            ((TextView) myPosts.findViewById(R.id.price)).setText("");
            ((TextView) myPosts.findViewById(R.id.city)).setText("");
            ((TextView) myPosts.findViewById(R.id.description)).setText("");

        });
        return myPosts ;
    }
    public void PublishPost(Post p){
        APIfactory apIfactory = new APIfactory(getContext());
        Log.i("new Post","publishing");
        apIfactory.PublishPost(p);

    }
    public void LoadOldPosts( View rootView ,ViewGroup container ){


        LinearLayout myOldPostsHolder = (LinearLayout) rootView.findViewById(R.id.myPostsList);
        for(int i = 0 ; i < 5 ; i++ ){
            View oldPost =  LayoutInflater.from(
                    getActivity()).inflate(R.layout.my_old_post,container,false);

            myOldPostsHolder.addView(oldPost);

        }


    }


}