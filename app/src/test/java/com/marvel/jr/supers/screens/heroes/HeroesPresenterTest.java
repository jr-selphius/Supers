package com.marvel.jr.supers.screens.heroes;

import com.marvel.jr.supers.screens.heroes.domain.GetSuperheroesUseCase;
import com.marvel.jr.supers.model.Superhero;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HeroesPresenterTest {

    private final Superhero SUPERHERO = new Superhero(5, "asf", "asdf", "asdf", "asdf", "asf", "asdf", "asdf");
    private ArrayList<Superhero> SOME_SUPERHEROES;

    @Mock
    HeroesView view;

    @Mock
    GetSuperheroesUseCase getSuperheroesUseCase;

    @Captor
    ArgumentCaptor<GetSuperheroesUseCase.Callback> captor;

    private HeroesPresenter heroesPresenter;


    @Before
    public void setUp() {
        heroesPresenter = new HeroesPresenter(getSuperheroesUseCase);
        heroesPresenter.setView(view);

        SOME_SUPERHEROES = new ArrayList<>(1);
        SOME_SUPERHEROES.add(SUPERHERO);
    }

    @Test
    public void shouldShowEmptyViewWhenNoHeroes() {

        heroesPresenter.getSuperheroes();

        verify(view).showProgressView();
        verify(getSuperheroesUseCase).execute(captor.capture());
        captor.getValue().onSuperheroesObtained(null);

        verify(view).hideProgressView();
        verify(view).showEmptyView();
    }

    @Test
    public void shouldShowHeroesWhenThereAreHeroes() {

        heroesPresenter.getSuperheroes();

        verify(view).showProgressView();
        verify(getSuperheroesUseCase).execute(captor.capture());

        captor.getValue().onSuperheroesObtained(SOME_SUPERHEROES);

        verify(view).hideProgressView();
        verify(view).showHeroes(SOME_SUPERHEROES);
    }

    @Test
    public void shouldNavigateToDetailWhenClicked() {

        heroesPresenter.heroClicked(anyLong());

        verify(view).navigateToDetail(anyLong());
    }

}