package com.marvel.jr.supers.datasource;

import com.marvel.jr.supers.datasource.local.LocalDataSource;
import com.marvel.jr.supers.datasource.remote.RemoteDataSource;
import com.marvel.jr.supers.model.Superhero;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HeroesRepositoryTest {

    private final Superhero SUPERHERO = new Superhero(5, "asf", "asdf", "asdf", "asdf", "asf", "asdf", "asdf");
    private ArrayList<Superhero> SOME_SUPERHEROES;

    @Mock
    LocalDataSource localDataSource;

    @Mock
    RemoteDataSource remoteDataSource;

    private HeroesRepository heroesRepository;

    @Before
    public void setUp() {

        heroesRepository = new HeroesRepository(localDataSource, remoteDataSource);
        SOME_SUPERHEROES = new ArrayList<>();
        SOME_SUPERHEROES.add(SUPERHERO);
        SOME_SUPERHEROES.add(SUPERHERO);
    }

    @Test
    public void shouldHitRemoteDataSourceWhenNoLocalHeroes() {

        when(localDataSource.getSuperheroes()).thenReturn(new ArrayList<Superhero>());

        heroesRepository.getSuperheroes();

        verify(remoteDataSource).getSuperheroes();
    }

    @Test
    public void shouldInsertHeroesToLocalDataSourceWhenHitRemote() {

        when(localDataSource.getSuperheroes()).thenReturn(new ArrayList<Superhero>());
        when(remoteDataSource.getSuperheroes()).thenReturn(SOME_SUPERHEROES);

        heroesRepository.getSuperheroes();

        verify(localDataSource).addSuperheroes(SOME_SUPERHEROES);
    }

}