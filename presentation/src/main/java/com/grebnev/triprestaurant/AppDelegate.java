package com.grebnev.triprestaurant;

import android.app.Application;

import com.grebnev.triprestaurant.di.AppComponent;
import com.grebnev.triprestaurant.di.DaggerAppComponent;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

public class AppDelegate extends Application {

    private static AppComponent appComponent;
    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();

        cicerone = Cicerone.create();
    }


}
