package com.appsvatika.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by raja.pandey on 11/7/2017.
 */

public class SplashActivity extends AppCompatActivity {

    String url,token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);
        boolean isConnected =AppUtil.isNetworkAvailable(this);

        if(isConnected)
        {
            url = "https://opentdb.com/api_token.php?command=request";

           this.tokenReq(url);
            MobileAds.initialize(this, "ca-app-pub-3635689467443264~7872352523");



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);


                    startActivity(intent);


                }
            }, 2000);


              }

        else {

            Toast.makeText(SplashActivity.this,"Please connect to the internet",Toast.LENGTH_LONG).show();

        }

    }

    private void tokenReq(String url)
    {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);

                             token = json.get("token").toString();

                            ((Token)getApplication()).setToken(token);




                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(SplashActivity.this,"Network error "+e.getMessage(),Toast.LENGTH_LONG).show();

                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashActivity.this,"Network error "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


}
