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
        getSuperheroesUseCase.execute(new GetSuperheroesUseCase.Callback() {
            @Override
            public void onSuperheroesObtained(List<Superhero> superheroes) {
                view.showHeroes(superheroes);
            }
        });
    }
}
