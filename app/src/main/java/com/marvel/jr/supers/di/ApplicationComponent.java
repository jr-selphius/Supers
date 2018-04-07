package com.marvel.jr.supers.di;

import android.app.Application;

import com.marvel.jr.supers.screens.detail.di.HeroDetailComponent;
import com.marvel.jr.supers.screens.detail.di.HeroDetailModule;
import com.marvel.jr.supers.screens.heroes.di.HeroesComponent;
import com.marvel.jr.supers.screens.heroes.di.HeroesModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application target);
    HeroDetailComponent plus(HeroDetailModule heroDetailModule);
    HeroesComponent plus(HeroesModule heroesModule);
    OkHttpClient getOkHttpClient();

}
