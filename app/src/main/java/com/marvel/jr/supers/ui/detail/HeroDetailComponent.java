package com.marvel.jr.supers.ui.detail;

import dagger.Subcomponent;

@HeroDetailScope
@Subcomponent(modules = HeroDetailModule.class)
public interface HeroDetailComponent {
    void inject(HeroDetailActivity target);
}
