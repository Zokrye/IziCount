package com.example.izicount;

import android.app.Application;
import android.content.Context;
import android.widget.Button;

public class IziCountApplication extends Application {

    private static Context sContext;

    public void onCreate(){
        super.onCreate();

        // Keep a reference to the application context
        sContext = getApplicationContext();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }
}