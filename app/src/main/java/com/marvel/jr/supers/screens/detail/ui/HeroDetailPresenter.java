package com.marvel.jr.supers.screens.detail.ui;


import com.marvel.jr.supers.UseCase;
import com.marvel.jr.supers.UseCaseHandler;
import com.marvel.jr.supers.screens.detail.domain.GetSuperheroByIdUseCase;
import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.screens.BasePresenter;
import com.marvel.jr.supers.screens.detail.HeroView;

public class HeroDetailPresenter extends BasePresenter<HeroView> {

    private final UseCaseHandler useCaseHandler;
    private GetSuperheroByIdUseCase getSuperheroByIdUseCase;

    public HeroDetailPresenter(UseCaseHandler useCaseHandler, GetSuperheroByIdUseCase getSuperheroByIdUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getSuperheroByIdUseCase = getSuperheroByIdUseCase;
    }

    public void getSuperheroById(long id) {

        useCaseHandler.execute(getSuperheroByIdUseCase, new GetSuperheroByIdUseCase.RequestValue(id), new UseCase.UseCaseCallback<GetSuperheroByIdUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetSuperheroByIdUseCase.ResponseValue response) {
                Superhero superhero = response.getSuperhero();
                if (superhero != null) {
                    view.showHero(superhero);
                } else {
                    view.hideMainContent();
                    view.showHeroNotFound();
                }
            }

            @Override
            public void onError() {
                view.hideMainContent();
                view.showHeroNotFound();
            }
        });
    }
}
