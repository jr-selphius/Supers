package com.marvel.jr.supers.ui.heroes;

import dagger.Subcomponent;

@HeroesScope
@Subcomponent(modules = HeroesModule.class)
public interface HeroesComponent {
    void inject(HeroesListActivity target);
}
