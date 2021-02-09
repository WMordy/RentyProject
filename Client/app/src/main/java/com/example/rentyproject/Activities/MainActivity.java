package com.example.rentyproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentyproject.Model.User;
import com.example.rentyproject.R;
import com.example.rentyproject.data.APIfactory;
import com.securepreferences.SecurePreferences;

public class MainActivity extends AppCompatActivity {
    TextView username ;
    TextView password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (TextView)findViewById(R.id.newFirstname);
        password = (TextView)findViewById(R.id.password);

    }
    public void Login(View v ){
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        Toast.makeText(this, "loging in ", Toast.LENGTH_SHORT).show();
        if(inputcheck()){

            User user = new User( usernameValue ,passwordValue );
            //TODO backend stuff and trigger intent to home page
            APIfactory apIfactory = new APIfactory(this);
            apIfactory.Login(user).observe(this,loginState ->{
                if(loginState == 1)
                    GoHome();
                if(loginState >1)
                    clearCredintials();
            });

            SharedPreferences prefs = new SecurePreferences(getApplicationContext());
            prefs.edit().putString("username",user.getFirstName()).apply();


        }
        else{
            clearCredintials();
        }

    }

    public void SignUP(View v ){
        //TODO go to signUP page
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    public void GoHome(){
        Intent intent = new Intent(getApplicationContext(), HomeTabbedActivity.class);
        startActivity(intent);
    }


    public void clearCredintials(){
        username.setText("");
        password.setText("");

    }
    public boolean inputcheck(){
        if(username.getText().toString().length() <= 0 || password.getText().toString().length() <= 0  ){
            return false ;
        }
        return true ;
    }

}