package com.marvel.jr.supers.screens.heroes.di;

import com.marvel.jr.supers.screens.heroes.ui.HeroesListActivity;

import dagger.Subcomponent;

@HeroesScope
@Subcomponent(modules = HeroesModule.class)
public interface HeroesComponent {
    void inject(HeroesListActivity target);
}
