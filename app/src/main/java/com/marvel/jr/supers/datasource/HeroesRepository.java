package com.marvel.jr.supers.datasource;

import com.marvel.jr.supers.datasource.local.LocalDataSource;
import com.marvel.jr.supers.datasource.remote.RemoteDataSourceImpl;
import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public class HeroesRepository implements HeroesRepositoryDataSource {

    private LocalDataSource localDataSource;
    private RemoteDataSourceImpl remoteDataSource;

    public HeroesRepository(LocalDataSource localDataSource, RemoteDataSourceImpl remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public List<Superhero> getSuperheroes() {

        List<Superhero> superheroes = localDataSource.getSuperheroes();
        if (superheroes.isEmpty()) {
            superheroes = remoteDataSource.getSuperheroes();
            if (superheroes != null) {
                localDataSource.addSuperheroes(superheroes);
            }
        }

        return superheroes;
    }

    @Override
    public Superhero getSuperhero(Long id) {
        return localDataSource.getSuperhero(id);
    }

    @Override
    public void addSuperheroes(List<Superhero> superheroes) {
        localDataSource.addSuperheroes(superheroes);
    }
}
