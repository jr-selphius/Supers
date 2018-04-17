package com.marvel.jr.supers.screens.detail.domain;

import com.marvel.jr.supers.domain.UseCase;
import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.model.Superhero;

public class GetSuperheroByIdUseCase extends UseCase<GetSuperheroByIdUseCase.RequestValue, GetSuperheroByIdUseCase.ResponseValue> {

    private final HeroesRepository heroesRepository;

    public GetSuperheroByIdUseCase(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    @Override
    protected void executeUseCase(RequestValue requestValues) {
        Superhero superhero = heroesRepository.getSuperhero(requestValues.getId());
        getUseCaseCallback().onSuccess(new ResponseValue(superhero));
    }


    public static final class RequestValue implements UseCase.RequestValues {

        private long id;

        public RequestValue(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private Superhero superhero;

        public ResponseValue(Superhero superhero) {
            this.superhero = superhero;
        }

        public Superhero getSuperhero() {
            return superhero;
        }
    }
}
