package com.marvel.jr.supers.ui.heroes;

import com.marvel.jr.supers.domain.GetSuperheroesUseCase;
import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public class HeroesPresenter {

    private HeroesView view;
    private GetSuperheroesUseCase getSuperheroesUseCase;

    public HeroesPresenter(GetSuperheroesUseCase getSuperheroesUseCase) {
        this.getSuperheroesUseCase = getSuperheroesUseCase;
    }

    public void setView(HeroesView view) {
        this.view = view;
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
