package com.example.rentyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        LinearLayout myPostsHolder =  findViewById(R.id.Posts);
        for(int i = 0 ; i < 5 ; i++ ){
            View oldPost =  LayoutInflater.from(
                    this).inflate(R.layout.post,null,false);

            myPostsHolder.addView(oldPost);

        }
    }


}