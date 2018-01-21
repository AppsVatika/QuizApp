package com.appsvatika.quizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity  {
    GridView gridView;
    private AdView mAdView;

    String token;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAdView = findViewById(R.id.adViewm);
        AdRequest adRequest = new AdRequest.Builder().build();



        mAdView.loadAd(adRequest);


        gridArray = AppUtil.buildArray(this);
        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);

        TextView tx = (TextView)findViewById(R.id.textViewm);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/custom.ttf");

        tx.setTypeface(custom_font);




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tv =(TextView) view.findViewById(R.id.item_text);//your textview id

                Intent i = new Intent(MainActivity.this,QuizActivity.class);
                    int cat = position+9;

                i.putExtra("cat",cat );


                startActivity(i);






            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}