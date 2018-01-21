package com.appsvatika.quizapp;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class ResultActivity extends Activity {
	private InterstitialAd mInterstitialAd;
    String token;
    int category;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		final TextView cat=(TextView)findViewById(R.id.textcat);
        TextView que=(TextView)findViewById(R.id.textque);
        TextView res=(TextView)findViewById(R.id.textRes);
		final Button cont = (Button) findViewById(R.id.rcontinue);
		final 	Button cate =(Button) findViewById(R.id.rcategory);

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-3635689467443264/2620149554");
		mInterstitialAd.loadAd(new AdRequest.Builder().build());

		Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/custom.ttf");

		cat.setTypeface(custom_font);
        que.setTypeface(custom_font);
        res.setTypeface(custom_font);
		cont.setTypeface(custom_font);
		cate.setTypeface(custom_font);
		//get score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("Score");
		category = b.getInt("Category");
        token = b.getString("token");

		cat.setText(""+AppUtil.buildArray(this).get(category-9).getTitle());
		que.setText("Question : "+10);
		res.setText("Score : "+score);

		cate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

               cate.setAlpha(1);
                final Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }


            }
		});

      cont.setOnClickListener(new View.OnClickListener() {
		  @Override
		  public void onClick(View view) {
              if (mInterstitialAd.isLoaded()) {
                  mInterstitialAd.show();
              } else {
                  Log.d("TAG", "The interstitial wasn't loaded yet.");
              }

            cont.setAlpha(1);

              Intent i = new Intent();


              i.putExtra("cat",category );
              //i.putExtra("token",token);



              setResult(1,i);

              finish();





          }
	  });

	}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
    }
}
