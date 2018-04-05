package com.marvel.jr.supers.screens.detail.di;

import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.screens.detail.domain.GetSuperheroByIdUseCase;
import com.marvel.jr.supers.screens.detail.ui.HeroDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HeroDetailModule {

    @Provides
    public GetSuperheroByIdUseCase provideGetSuperheroByIdUseCase(HeroesRepository repo) {
        return new GetSuperheroByIdUseCase(repo);
    }

    @Provides
    public HeroDetailPresenter provideHeroDetailPresenter(GetSuperheroByIdUseCase getSuperheroByIdUseCase) {
        return new HeroDetailPresenter(getSuperheroByIdUseCase);
    }
}
