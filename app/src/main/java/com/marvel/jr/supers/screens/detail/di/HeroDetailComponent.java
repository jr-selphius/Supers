package com.marvel.jr.supers.screens.detail.di;

import com.marvel.jr.supers.screens.detail.HeroDetailActivity;

import dagger.Subcomponent;

@HeroDetailScope
@Subcomponent(modules = HeroDetailModule.class)
public interface HeroDetailComponent {
    void inject(HeroDetailActivity target);
}
