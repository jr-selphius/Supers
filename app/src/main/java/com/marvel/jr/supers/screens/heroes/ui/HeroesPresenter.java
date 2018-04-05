package com.marvel.jr.supers.screens.heroes.ui;

import com.marvel.jr.supers.screens.heroes.domain.GetSuperheroesUseCase;
import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.screens.BasePresenter;

import java.util.List;

public class HeroesPresenter extends BasePresenter<HeroesView> {

    private GetSuperheroesUseCase getSuperheroesUseCase;

    public HeroesPresenter(GetSuperheroesUseCase getSuperheroesUseCase) {
        this.getSuperheroesUseCase = getSuperheroesUseCase;
    }

    public void getSuperheroes() {

        view.showProgressView();

        getSuperheroesUseCase.execute(new GetSuperheroesUseCase.Callback() {
            @Override
            public void onSuperheroesObtained(List<Superhero> superheroes) {

                view.hideProgressView();

                if (superheroes == null || superheroes.isEmpty()) {
                    view.showEmptyView();
                } else {
                    view.showHeroes(superheroes);
                }
            }
        });
    }

    public void heroClicked(long id) {
        view.navigateToDetail(id);
    }
}
