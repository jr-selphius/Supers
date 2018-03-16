package com.marvel.jr.supers;

import android.app.Application;

import com.marvel.jr.supers.di.ApplicationComponent;
import com.marvel.jr.supers.di.ApplicationModule;
import com.marvel.jr.supers.di.DaggerApplicationComponent;

public class CustomApplication extends Application {

    private ApplicationComponent component;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
