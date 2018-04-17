package com.marvel.jr.supers.screens.heroes.domain;

import com.marvel.jr.supers.domain.UseCase;
import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public class GetSuperheroesUseCase extends UseCase<GetSuperheroesUseCase.RequestValue, GetSuperheroesUseCase.ResponseValue> {

    private final HeroesRepository heroesRepository;

    public GetSuperheroesUseCase(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    @Override
    protected void executeUseCase(RequestValue requestValues) {
        List<Superhero> superheroes = heroesRepository.getSuperheroes();
        getUseCaseCallback().onSuccess(new ResponseValue(superheroes));
    }

    public static final class RequestValue implements UseCase.RequestValues { }
    public static final class ResponseValue implements UseCase.ResponseValue {

        private List<Superhero> superheroes;

        public ResponseValue(List<Superhero> superheroes) {
            this.superheroes = superheroes;
        }

        public List<Superhero> getSuperheroes() {
            return superheroes;
        }

    }
}
