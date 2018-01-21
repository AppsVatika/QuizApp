package com.appsvatika.quizapp;

import android.app.Application;

/**
 * Created by raja.pandey on 12/29/2017.
 */

public class Token extends Application {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
