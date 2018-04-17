package com.marvel.jr.supers.screens.heroes.ui;

import com.marvel.jr.supers.domain.UseCase;
import com.marvel.jr.supers.domain.UseCaseHandler;
import com.marvel.jr.supers.screens.heroes.domain.GetSuperheroesUseCase;
import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.screens.BasePresenter;

import java.util.List;

public class HeroesPresenter extends BasePresenter<HeroesView> {

    private final UseCaseHandler useCaseHandler;
    private final GetSuperheroesUseCase getSuperheroesUseCase;

    public HeroesPresenter(UseCaseHandler useCaseHandler, GetSuperheroesUseCase getSuperheroesUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getSuperheroesUseCase = getSuperheroesUseCase;
    }

    public void getSuperheroes() {

        view.showProgressView();

        useCaseHandler.execute(getSuperheroesUseCase,
                new GetSuperheroesUseCase.RequestValue(),
                new UseCase.UseCaseCallback<GetSuperheroesUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetSuperheroesUseCase.ResponseValue response) {

                view.hideProgressView();

                List<Superhero> superheroes = response.getSuperheroes();
                if (superheroes == null || superheroes.isEmpty()) {
                    view.showEmptyView();
                } else {
                    view.showHeroes(superheroes);
                }
            }

            @Override
            public void onError() {
                view.hideProgressView();
                view.showEmptyView();
            }
        });
    }

    public void heroClicked(long id) {
        view.navigateToDetail(id);
    }
}
