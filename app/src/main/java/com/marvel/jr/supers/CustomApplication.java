package com.marvel.jr.supers;

import android.app.Application;

import com.marvel.jr.supers.di.ApplicationComponent;
import com.marvel.jr.supers.di.ApplicationModule;
import com.marvel.jr.supers.di.DaggerApplicationComponent;
import com.marvel.jr.supers.ui.detail.HeroDetailComponent;
import com.marvel.jr.supers.ui.detail.HeroDetailModule;
import com.marvel.jr.supers.ui.heroes.HeroesComponent;
import com.marvel.jr.supers.ui.heroes.HeroesModule;

public class CustomApplication extends Application {

    private ApplicationComponent applicationComponent;
    private HeroDetailComponent heroDetailComponent;
    private HeroesComponent heroesComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

    public HeroDetailComponent createHeroDetailComponent() {

        heroDetailComponent = applicationComponent.plus(new HeroDetailModule());

        return heroDetailComponent;
    }

    public HeroesComponent createHeroesComponent() {

        heroesComponent = applicationComponent.plus(new HeroesModule());

        return heroesComponent;
    }

    public void releaseHeroDetailComponent() {
        heroDetailComponent = null;
    }

    public void releaseHeroesComponent() {
        heroesComponent = null;
    }
}
