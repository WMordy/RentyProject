package com.example.rentyproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentyproject.Model.User;
import com.example.rentyproject.R;
import com.example.rentyproject.data.APIfactory;

public class SignUpActivity extends AppCompatActivity {
    TextView username ;
    TextView lastName ;
    TextView phone ;
    TextView password ;
    TextView email ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }




    public void CreateAccount(View v ){
        username = (TextView) findViewById(R.id.newFirstname);
        lastName = (TextView) findViewById(R.id.lastName);
        phone = (TextView) findViewById(R.id.phone);
        password = (TextView) findViewById(R.id.newPassword);
        email = (TextView) findViewById(R.id.email);
        if(inputcheck()){

            User user = new User(username.getText().toString(),
                    lastName.getText().toString(),
                    password.getText().toString(),
                    phone.getText().toString(),
                    email.getText().toString());
            APIfactory apIfactory  = new APIfactory(this).getInstance(this);
            apIfactory.CreateAccount(user);
            Toast.makeText(this,"Creating Account "+ username.getText().toString(),Toast.LENGTH_SHORT);
            // TODO do sign up request here
            clearCredintials();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else{
            clearCredintials();
        }



    }
    public void Discard(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    public void clearCredintials(){
        username.setText("");
        password.setText("");
        phone.setText("");
        email.setText("");
    }
    public boolean inputcheck(){
        if(username.getText().toString().length() <= 0 || password.getText().toString().length() <= 0
                || phone.getText().toString().length() <= 0 || email.getText().toString().length() <= 0 ){
            return false ;
        }
        return true ;
    }
}