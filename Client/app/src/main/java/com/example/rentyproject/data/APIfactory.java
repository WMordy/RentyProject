package com.example.rentyproject.data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
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
    public  static MutableLiveData<Integer> isLoggedIn  = new MutableLiveData<>() ;
    private int mStatusCode = 0;

    public  APIfactory(Context context){
        context= context.getApplicationContext() ;
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




}
