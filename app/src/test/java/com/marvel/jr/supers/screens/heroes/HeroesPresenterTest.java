package com.marvel.jr.supers.screens.heroes;

import com.marvel.jr.supers.domain.UseCase;
import com.marvel.jr.supers.domain.UseCaseHandler;
import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.screens.heroes.domain.GetSuperheroesUseCase;
import com.marvel.jr.supers.screens.heroes.ui.HeroesPresenter;
import com.marvel.jr.supers.screens.heroes.ui.HeroesView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HeroesPresenterTest {

    private final Superhero SUPERHERO = new Superhero(5, "asf", "asdf", "asdf", "asdf", "asf", "asdf", "asdf");
    private ArrayList<Superhero> SOME_SUPERHEROES;

    @Mock
    HeroesView view;

    @Mock
    GetSuperheroesUseCase getSuperheroesUseCase;

    @Mock
    UseCaseHandler useCaseHandler;

    @Captor
    ArgumentCaptor<UseCase.UseCaseCallback<GetSuperheroesUseCase.ResponseValue>> captor;

    private HeroesPresenter heroesPresenter;


    @Before
    public void setUp() {
        heroesPresenter = new HeroesPresenter(useCaseHandler, getSuperheroesUseCase);
        heroesPresenter.setView(view);

        SOME_SUPERHEROES = new ArrayList<>(1);
        SOME_SUPERHEROES.add(SUPERHERO);
    }

    @Test
    public void shouldShowHeroesWhenThereAreHeroes() {

        heroesPresenter.getSuperheroes();

        verify(view).showProgressView();
        verify(useCaseHandler).execute(eq(getSuperheroesUseCase), any(GetSuperheroesUseCase.RequestValue.class), captor.capture());

        captor.getValue().onSuccess(new GetSuperheroesUseCase.ResponseValue(SOME_SUPERHEROES));

        verify(view).hideProgressView();
        verify(view).showHeroes(SOME_SUPERHEROES);
    }

    @Test
    public void shouldShowEmptyViewWhenNoHeroes() {

        heroesPresenter.getSuperheroes();

        verify(view).showProgressView();

        verify(useCaseHandler).execute(eq(getSuperheroesUseCase), any(GetSuperheroesUseCase.RequestValue.class), captor.capture());

        captor.getValue().onSuccess(new GetSuperheroesUseCase.ResponseValue(new ArrayList<Superhero>()));

        verify(view).hideProgressView();
        verify(view).showEmptyView();
    }

    @Test
    public void shouldShowEmptyViewWhenErrorIsNotifiedByCallbackWrapper() {

        heroesPresenter.getSuperheroes();

        verify(view).showProgressView();

        verify(useCaseHandler).execute(eq(getSuperheroesUseCase), any(GetSuperheroesUseCase.RequestValue.class), captor.capture());

        captor.getValue().onSuccess(new GetSuperheroesUseCase.ResponseValue(new ArrayList<Superhero>()));

        verify(view).hideProgressView();
        verify(view).showEmptyView();
    }

    @Test
    public void shouldNavigateToDetailWhenClicked() {

        heroesPresenter.heroClicked(anyLong());

        verify(view).navigateToDetail(anyLong());
    }
}