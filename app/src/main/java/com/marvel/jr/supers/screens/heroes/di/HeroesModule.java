package com.marvel.jr.supers.screens.heroes.di;

import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.screens.heroes.domain.GetSuperheroesUseCase;
import com.marvel.jr.supers.screens.heroes.ui.HeroesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HeroesModule {

    @Provides
    public GetSuperheroesUseCase provideGetSuperheroesUseCase(HeroesRepository repo) {
        return new GetSuperheroesUseCase(repo);
    }

    @Provides
    public HeroesPresenter provideHeroesPresenter(GetSuperheroesUseCase getSuperheroesUseCase) {
        return new HeroesPresenter(getSuperheroesUseCase);
    }
}
