package com.grebnev.triprestaurant;

import android.app.Application;

import com.grebnev.triprestaurant.di.AppComponent;
import com.grebnev.triprestaurant.di.DaggerAppComponent;

public class AppDelegate extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
    }
}
