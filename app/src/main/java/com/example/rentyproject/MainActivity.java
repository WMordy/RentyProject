package com.example.rentyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView username ;
    TextView password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
    }
    public void Login(View v ){
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        Toast.makeText(this, "loging in ", Toast.LENGTH_SHORT).show();

        //TODO backend stuff and trigger intent to home page
    }

    public void SignUP(View v ){
        //TODO go to signUP page
    }

}