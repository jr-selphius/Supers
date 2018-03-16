package com.marvel.jr.supers.di;

import com.marvel.jr.supers.ui.detail.HeroDetailActivity;
import com.marvel.jr.supers.ui.heroes.HeroesListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(HeroesListActivity target);
    void inject(HeroDetailActivity target);
}
