package com.example.rentyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    TextView username ;
    TextView phone ;
    TextView password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }




    public void CreateAccount(View v ){
        username = (TextView) findViewById(R.id.newUsername);
        phone = (TextView) findViewById(R.id.phone);
        password = (TextView) findViewById(R.id.newPassword);
        if(inputcheck()){
            Toast.makeText(this,"Creating Account ",Toast.LENGTH_SHORT);
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
    }
    public boolean inputcheck(){
        if(username.getText().toString().length() <= 0 || password.getText().toString().length() <= 0 || phone.getText().toString().length() <= 0 ){
            return false ;
        }
        return true ;
    }
}