package com.marvel.jr.supers.screens.detail.ui;


import com.marvel.jr.supers.screens.detail.domain.GetSuperheroByIdUseCase;
import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.screens.BasePresenter;
import com.marvel.jr.supers.screens.detail.HeroView;

public class HeroDetailPresenter extends BasePresenter<HeroView> {

    private GetSuperheroByIdUseCase getSuperheroByIdUseCase;

    public HeroDetailPresenter(GetSuperheroByIdUseCase getSuperheroByIdUseCase) {
        this.getSuperheroByIdUseCase = getSuperheroByIdUseCase;
    }

    public void getSuperheroById(long id) {
        getSuperheroByIdUseCase.execute(id, new GetSuperheroByIdUseCase.Callback() {
            @Override
            public void onSuperheroByIdObtained(Superhero superhero) {
                if (superhero != null) {
                    view.showHero(superhero);
                } else {
                    view.hideMainContent();
                    view.showHeroNotFound();
                }
            }
        });
    }
}
