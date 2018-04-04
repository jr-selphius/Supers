package com.marvel.jr.supers.di;

import android.app.Application;

import com.marvel.jr.supers.ui.detail.HeroDetailComponent;
import com.marvel.jr.supers.ui.detail.HeroDetailModule;
import com.marvel.jr.supers.ui.heroes.HeroesComponent;
import com.marvel.jr.supers.ui.heroes.HeroesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application target);
    HeroDetailComponent plus(HeroDetailModule heroDetailModule);
    HeroesComponent plus(HeroesModule heroesModule);
}
