package com.marvel.jr.supers.ui.detail;


import com.marvel.jr.supers.domain.GetSuperheroByIdUseCase;
import com.marvel.jr.supers.model.Superhero;

public class HeroDetailPresenter {

    private HeroView view;
    private GetSuperheroByIdUseCase getSuperheroByIdUseCase;

    public HeroDetailPresenter(GetSuperheroByIdUseCase getSuperheroByIdUseCase) {
        this.getSuperheroByIdUseCase = getSuperheroByIdUseCase;
    }

    public void setView(HeroView view) {
        this.view = view;
    }

    public void getSuperheroById(long id) {
        getSuperheroByIdUseCase.execute(id, new GetSuperheroByIdUseCase.Callback() {
            @Override
            public void onSuperheroByIdObtained(Superhero superhero) {
                view.showHero(superhero);
            }
        });
    }
}
