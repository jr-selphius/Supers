package com.marvel.jr.supers.screens.detail;

import com.marvel.jr.supers.screens.detail.domain.GetSuperheroByIdUseCase;
import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.screens.detail.ui.HeroDetailPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HeroDetailPresenterTest {

    public static final int ANY_ID = 5;
    private final Superhero SUPERHERO = new Superhero(5, "asf", "asdf", "asdf", "asdf", "asf", "asdf", "asdf");

    @Mock
    HeroView view;

    @Mock
    GetSuperheroByIdUseCase getSuperheroByIdUseCase;

    @Captor
    ArgumentCaptor<GetSuperheroByIdUseCase.Callback> captor;

    private HeroDetailPresenter heroDetailPresenter;

    @Before
    public void setUp() {
        heroDetailPresenter = new HeroDetailPresenter(getSuperheroByIdUseCase);
        heroDetailPresenter.setView(view);
    }

    @Test
    public void shouldShowHeroWhenExists() {

        heroDetailPresenter.getSuperheroById(ANY_ID);

        verify(getSuperheroByIdUseCase).execute(anyLong(), captor.capture());
        captor.getValue().onSuperheroByIdObtained(SUPERHERO);

        verify(view).showHero(SUPERHERO);
    }

    @Test
    public void shouldShowErrorWhenTheHeroIsNotFound() {

        heroDetailPresenter.getSuperheroById(ANY_ID);

        verify(getSuperheroByIdUseCase).execute(anyLong(), captor.capture());
        captor.getValue().onSuperheroByIdObtained(null);

        verify(view).showHeroNotFound();
        verify(view).hideMainContent();
    }


}