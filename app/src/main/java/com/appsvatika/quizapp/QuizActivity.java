package com.appsvatika.quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizActivity extends AppCompatActivity {
	int score=0;
	int qid=0;
	int qcount=0;
	 String url;
	TextView txtQuestion;
	Button opt1,opt2,opt3,opt4;
	Question currentQ, cansQ;

    private AdView mAdView;

    String token;
     boolean qtype=true;
	ArrayList<Question> question = new ArrayList<Question>();
	ArrayList<Question> cquestion = new ArrayList<Question>();
	int cat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		 setContentView(R.layout.activity_quiz);

        mAdView = findViewById(R.id.adViewq);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    		txtQuestion=(TextView)findViewById(R.id.textView1);
		ImageButton  share = (ImageButton) findViewById(R.id.share);
		ImageButton  star = (ImageButton) findViewById(R.id.star);
    	    opt1 =  findViewById(R.id.opt1);
            opt2 =  findViewById(R.id.opt2);
            opt3 =  findViewById(R.id.opt3);
            opt4 =  findViewById(R.id.opt4);

        Bundle bundle = getIntent().getExtras();
        cat = bundle.getInt("cat");



        LoadQ();


            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=AppsVatika"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            });

            share.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {



					Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String shareBody = txtQuestion.getText().toString();

                    shareBody = "Question : "+shareBody+System.getProperty("line.separator")+System.getProperty("line.separator");


                             String a = opt1.getText().toString();
                            String b = opt2.getText().toString();
                            String c = opt3.getText().toString();
                            String d = opt4.getText().toString();

                            List<String> temp = new ArrayList<>();
                            if(!a.equals(""))
                                temp.add(a);
                            if(!b.equals(""))
                                temp.add(b);
                            if(!c.equals(""))
                                temp.add(c);
                            if(!d.equals(""))
                                temp.add(d);
                            String []temp2 = {"Option A : ","Option B : ","Option C : ","Option D : "};

                            int t = 0;
                            String options = "";


                            for(String flag : temp)
                            {
                                if(!options.equals(""))
                                    options += System.getProperty("line.separator") +temp2[t]+ flag;
                                else
                                    options = temp2[t] + flag;
                                t++;
                            }
                            shareBody = shareBody + options;









                            shareBody = shareBody+System.getProperty("line.separator")+System.getProperty("line.separator")+"Find More On "+"https://goo.gl/41fTNQ";


                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "QuizApp Question");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

                    startActivity(Intent.createChooser(sharingIntent, "Ask via"));
				}
			});

		Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/custom.ttf");

		txtQuestion.setTypeface(custom_font);
        opt1.setTypeface(custom_font);
        opt2.setTypeface(custom_font);
        opt3.setTypeface(custom_font);
        opt4.setTypeface(custom_font);



            opt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    try{
                        handleButtonClick(1);
                        opt1.setAlpha(1);
                    }
                    catch (Exception err)
                    {
                        networkDialog();
                    }



                      }
            });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    handleButtonClick(2);
                    opt2.setAlpha(1);
                }
                catch (Exception err)
                {
                    networkDialog();
                }


            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    handleButtonClick(3);
                    opt3.setAlpha(1);
                }
                catch (Exception err)
                {
                    networkDialog();
                }




			}

        });

        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    handleButtonClick(4);
                    opt4.setAlpha(1);
                }
                catch (Exception err)
                {
                    networkDialog();
                }

            }
        });



    }


    void handleButtonClick(int b)
	{



        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);


       // Toast.makeText(QuizActivity.this,cquestion.get(qid-1).getANSWER(),Toast.LENGTH_LONG).show();

        if(opt1.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
        opt1.setBackgroundResource(R.drawable.button_c);
        if(opt2.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            opt2.setBackgroundResource(R.drawable.button_c);
        if(opt3.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            opt3.setBackgroundResource(R.drawable.button_c);
        if(opt4.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            opt4.setBackgroundResource(R.drawable.button_c);


        if(b==1)
        {
            if (opt1.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            score++;
            else
            opt1.setBackgroundResource(R.drawable.button_w);
        }

        if(b==2)
        {
            if (opt2.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
                score++;
            else
                opt2.setBackgroundResource(R.drawable.button_w);
        }
        if(b==3)
        {
            if (opt3.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
                score++;
            else
                opt3.setBackgroundResource(R.drawable.button_w);
        }
        if(b==4)
        {
            if (opt4.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
                score++;
            else
                opt4.setBackgroundResource(R.drawable.button_w);
        }
		if(qcount==9)
		{


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(QuizActivity.this,ResultActivity.class);

                    qcount=0;

                    i.putExtra("Score",score);
                    i.putExtra("Category",cat);
                    i.putExtra("token",token);
                    startActivityForResult(i,1);


                }
            }, 1000);


		}
		else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    setQuestionView();

                }
            }, 1000);


		}
	}





    @Override
    protected void onResume() {
        super.onResume();
           }

    void parseJson(String out) {


		try {
			JSONObject resJson = new JSONObject(out);

              String rescode=	resJson.getString("response_code");
		      int code =Integer.parseInt(rescode);
			if(code==0)
			{
				String result=	resJson.getString("results");
				JSONArray resArray = new JSONArray(result);

				for (int i=0; i<resArray.length();i++)
				{
					JSONObject qjson = resArray.getJSONObject(i);
			 		String q = qjson.getString("question");
					String cans = qjson.getString("correct_answer");
					String ians = qjson.getString("incorrect_answers");
					JSONArray ans = new JSONArray(ians);
					String ians1 = ans.getString(0);
					String ians2;
					String ians3;
					if(cans.equalsIgnoreCase("True") || cans.equals("False"))

					{
						ians2 = "";
						ians3="";
					}

					else
					{
					ians2 = ans.getString(1);
					ians3 = ans.getString(2);
					}

					Random rand = new Random();

					int  n = rand.nextInt(4) + 1;

					Question que=null;

					switch(n)
					{
					case 1 : que = new Question(q,ians1,ians2,ians3,cans);
					break;
					case 2 :  que = new Question(q,ians1,ians2,cans,ians3);
					break;
					case 3 :  que = new Question(q,ians1,cans,ians3,ians2);
					break;
					case 4 :  que = new Question(q,cans,ians2,ians3,ians1);
					break;

					}
					Question cq = new Question(q,cans);

					question.add(que);
					cquestion.add(cq);

				}

                setQuestionView();

			}

			else if(code==1)
			{

              //  Toast.makeText(this, "This category is Locked for 1 hour", Toast.LENGTH_LONG).show();

                //finish();
                ((Token)getApplication()).setToken(null);
                LoadQ();




				}
			else if(code==3)
			{


                ((Token)getApplication()).setToken(null);
                        LoadQ();

				}
			else if(code==4)
			{

                ((Token)getApplication()).setToken(null);
                LoadQ();
			}

			else {

				Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	
	private void setQuestionView()
	{



        currentQ=question.get(qid);
        cansQ = cquestion.get(qid);
		
		txtQuestion.setText(Html.fromHtml(currentQ.getQUESTION()));
		opt1.setText(Html.fromHtml(currentQ.getOPTA()));
		opt2.setText(Html.fromHtml(currentQ.getOPTB()));
		opt3.setText(Html.fromHtml(currentQ.getOPTC()));
		opt4.setText(Html.fromHtml(currentQ.getANSWER()));

		if(opt1.getText().toString().equals("")) {
            opt1.setVisibility(View.GONE);
            qtype=false;
        }
		else {
            opt1.setVisibility(View.VISIBLE);
        }
		if(opt2.getText().toString().equals("")) {
            opt2.setVisibility(View.GONE);
            qtype=false;
        }
		else {
            opt2.setVisibility(View.VISIBLE);
        }
		if(opt3.getText().toString().equals("")) {
            qtype=false;
            opt3.setVisibility(View.GONE);
        }
		else {
            opt3.setVisibility(View.VISIBLE);

        }
		if(opt4.getText().toString().equals("")) {
            qtype=false;
            opt4.setVisibility(View.GONE);
        }
		else {
            opt4.setVisibility(View.VISIBLE);
        }

        opt1.setAlpha(0.5f);
        opt2.setAlpha(0.5f);
        opt3.setAlpha(0.5f);
        opt4.setAlpha(0.5f);
        opt1.setBackgroundResource(R.drawable.button_b);
        opt2.setBackgroundResource(R.drawable.button_b);
        opt3.setBackgroundResource(R.drawable.button_b);
        opt4.setBackgroundResource(R.drawable.button_b);

        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
        qcount++;
        qid++;
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Bundle bundle = getIntent().getExtras();
        cat = bundle.getInt("cat");

        LoadQ();


    }

    void LoadQ()
    {
        score=0;
        token = ((Token)getApplication()).getToken();

        if ((token!=null))
        url =  "token="+token+"&amount=10"+"&category="+cat;
        else
            url ="amount=10"+"&category="+cat;
        url = "https://opentdb.com/api.php?"+url;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);

                            if(response!=null)
                            {
                                parseJson(response);
                            }

                            else{
                               networkDialog();
                            }






                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(),"Loading... Please wait...",Toast.LENGTH_LONG).show();

                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Loading... Please wait...",Toast.LENGTH_LONG).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void networkDialog() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        LoadQ();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setMessage("Network error occured: Retry?").setPositiveButton("Yes",dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }


}
