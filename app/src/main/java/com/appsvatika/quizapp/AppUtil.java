package com.appsvatika.quizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.GridView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by raja.pandey on 11/5/2017.
 */

public class AppUtil {




    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static ArrayList<Item> buildArray(Context ctx)
    {
        ArrayList<Item> gridArray = new ArrayList<Item>();

        Bitmap computer_science = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.computer_science);
        Bitmap mathematics = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.mathematics);
        Bitmap gadgets = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.gadgets);
        Bitmap video_games = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.video_game);
        Bitmap animals = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.animals);
        Bitmap art = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.art);
        Bitmap board_games = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.board_games);
        Bitmap books = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.books);
        Bitmap television = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.television);
        Bitmap vehicles = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.vehicles);
        Bitmap general_knowledge = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.general_knowledge);
        Bitmap theatres = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.theatres);
        Bitmap sports = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.sports);
        Bitmap cartoon = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.cartoon);
        Bitmap celebrities = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.celebrities);
        Bitmap politics = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.politics);
        Bitmap nature = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.nature);
        Bitmap music = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.music);
        Bitmap history = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.history);
        Bitmap geography = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.geography);
        Bitmap film = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.film);
        Bitmap comics = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.comics);
        Bitmap mythology = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.mythology);
        Bitmap anime_manga = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.anime_manga);

        gridArray.add(new Item(general_knowledge,"GK"));
        gridArray.add(new Item(books,"Books"));
        gridArray.add(new Item(film,"Film"));
        gridArray.add(new Item(music,"Music"));
        gridArray.add(new Item(theatres,"Theatres"));
        gridArray.add(new Item(television,"Television"));
        gridArray.add(new Item(video_games,"Video Games"));
        gridArray.add(new Item(board_games,"Board Games"));
        gridArray.add(new Item(nature,"Nature"));
        gridArray.add(new Item(computer_science,"Computer"));
        gridArray.add(new Item(mathematics,"Mathematics"));
        gridArray.add(new Item(mythology,"Mythology"));
        gridArray.add(new Item(sports,"Sports"));
        gridArray.add(new Item(geography,"Geography"));
        gridArray.add(new Item(history,"History"));
        gridArray.add(new Item(politics,"Politics"));
        gridArray.add(new Item(art,"Art"));
        gridArray.add(new Item(celebrities,"Celebrities"));
        gridArray.add(new Item(animals,"Animals"));
        gridArray.add(new Item(vehicles,"Vehicles"));
        gridArray.add(new Item(comics,"Comics"));
        gridArray.add(new Item(gadgets,"Gadgets"));
        gridArray.add(new Item(anime_manga,"Anime"));
        gridArray.add(new Item(cartoon,"Cartoon"));



        return gridArray;
    }



}
