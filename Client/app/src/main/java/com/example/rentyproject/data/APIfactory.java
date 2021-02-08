package com.example.rentyproject.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rentyproject.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class APIfactory {

    private final String URL_PREFIX = "http://192.168.1.5:5001" ;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private Context context = null ;
    public static APIfactory apIfactory = null ;
    public  static MutableLiveData<Boolean> isAccountCreated = new MutableLiveData<>() ;

    public  APIfactory(Context context){
        context= context.getApplicationContext() ;
        mRequestQueue =  Volley.newRequestQueue(context.getApplicationContext());

    }

    public static synchronized APIfactory getInstance(Context cont){
        if(apIfactory == null){
            apIfactory = new APIfactory(cont);
        }
        return apIfactory ;
    }

    public void CreateAccount(User user){
        String postUrl = URL_PREFIX + "/api/signup";
        boolean[] value = {false};
        JSONObject postData = new JSONObject();
        try {
            postData.put("firstName", user.getFirstName());
            postData.put("lastName", user.getLastName());
            postData.put("phone", user.getPhone());
            postData.put("email", user.getEmail());
            postData.put("password", user.getPassword());
            postData.put("avatar", "https://cdn0.iconfinder.com/data/icons/avatars-6/500/Avatar_boy_man_people_account_client_male_person_user_work_sport_beard_team_glasses-512.png");


        } catch (JSONException e) {

            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                    System.out.println(response);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        mRequestQueue.add(jsonObjectRequest);
        return ;

    }


}
