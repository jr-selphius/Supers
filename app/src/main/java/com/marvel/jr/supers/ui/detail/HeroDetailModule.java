package com.marvel.jr.supers.ui.detail;

import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.domain.GetSuperheroByIdUseCase;

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
