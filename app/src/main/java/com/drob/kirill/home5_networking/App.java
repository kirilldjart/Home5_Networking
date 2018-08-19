package com.drob.kirill.home5_networking;

import android.app.Application;

import com.drob.kirill.home5_networking.network.NetworkModule;
import com.drob.kirill.home5_networking.network.OmdbService;

public class App extends Application {
    public static OmdbService omdbService;
    @Override
    public void onCreate() {
        super.onCreate();
       omdbService =(new NetworkModule()).omdbService;
    }
}
