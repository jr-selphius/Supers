package com.marvel.jr.supers.ui.heroes;

import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.domain.GetSuperheroesUseCase;

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
