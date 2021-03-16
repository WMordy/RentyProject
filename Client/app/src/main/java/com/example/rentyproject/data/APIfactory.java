package com.example.rentyproject.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rentyproject.Model.Post;
import com.example.rentyproject.Model.User;
import com.securepreferences.SecurePreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIfactory {

    private final String URL_PREFIX = "http://192.168.1.7:5001" ;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private Context context = null ;
    public static APIfactory apIfactory = null ;
    public  static MutableLiveData<Boolean> isAccountCreated = new MutableLiveData<>() ;
    public  static MutableLiveData<Integer> isLoggedIn  = new MutableLiveData<>() ;
    private int mStatusCode = 0;

    public  APIfactory(Context cont){
        context= cont.getApplicationContext() ;
        mRequestQueue =  Volley.newRequestQueue(context.getApplicationContext());
        isLoggedIn.setValue(0);

    }

    public static synchronized APIfactory getInstance(Context cont){
        if(apIfactory == null){
            apIfactory = new APIfactory(cont);
        }
        return apIfactory ;
    }

    public void LoginStatus(int t){
        isLoggedIn.setValue(t);
    }
    public MutableLiveData<Integer> Login(User user){
        String postUrl = URL_PREFIX + "/api/signin";

        JSONObject postData = new JSONObject();
        try {

            postData.put("email", user.getEmail());
            postData.put("password", user.getPassword());


        } catch (JSONException e) {

            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl,
                postData, response -> {
            try {
                JSONObject headers = response.getJSONObject("headers");
                System.out.println(headers.get("auth-token"));
                //Log.e("Login state",headers.get("auth-token").toString());
                //Log.e("Login state",headers.get("user-id").toString());
                SharedPreferences prefs = new SecurePreferences(context);
                prefs.edit().putString("username",headers.get("user-id").toString()).apply();
                prefs.edit().putString("token",headers.get("auth-token").toString()).apply();
                LoginStatus(1);

            } catch (JSONException e) {
                e.printStackTrace();
            }




        }, error -> {
            String json = null;

            NetworkResponse response = error.networkResponse;
            switch(response.statusCode){
                case 400:
                    LoginStatus(2);
                    System.out.println("it's out with 400");
                    break;
            }


        } ){@Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            JSONObject responseData = new JSONObject();
            try {
                responseData.put("headers",new JSONObject(response.headers));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return Response.success(responseData, HttpHeaderParser.parseCacheHeaders(response));
        }
          };
        mRequestQueue.add(jsonObjectRequest);
        return isLoggedIn ;
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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, response -> {
            try{
                System.out.println(response);
            }
            catch(Error err){
                System.out.println(mStatusCode);
            }

        }, error -> {
            String json = null;

            NetworkResponse response = error.networkResponse;

                switch(response.statusCode){
                    case 400:
                        System.out.println("it's out with 400");
                        break;
                }
                //Additional cases
        });

        mRequestQueue.add(jsonObjectRequest);
        return ;

    }
    public void PublishPost(Post p){
        String postUrl = URL_PREFIX + "/api/posts";

        JSONObject postData = new JSONObject();
        try {
            postData.put("title", p.getPrice());
            postData.put("field", p.getLocation());
            postData.put("description", p.getDescription());



        } catch (JSONException e) {
            Log.e("New Post","cant post "+e.getMessage());
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, response -> {
            try{
                System.out.println(response);
            }
            catch(Error err){
                System.out.println(mStatusCode);
            }

        }, error -> {
            String json = null;

            NetworkResponse response = error.networkResponse;

            switch(response.statusCode){
                case 400:
                    Log.e("New Post","cant post ");
                    break;
            }
           Log.e("new Post Hesders",response.allHeaders.toString());
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("user-id", p.getUserId());
                SharedPreferences prefs = new SecurePreferences(context);
                String value = prefs.getString( "token", null );
                params.put("auth-token", value);

                return params;
            }};

        mRequestQueue.add(jsonObjectRequest);
        return ;

    }



}
