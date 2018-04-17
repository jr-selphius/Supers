package com.marvel.jr.supers.datasource;

import com.marvel.jr.supers.datasource.local.LocalDataSource;
import com.marvel.jr.supers.datasource.remote.RemoteDataSource;
import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public class HeroesRepository implements HeroesRepositoryDataSource {

    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    public HeroesRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public List<Superhero> getSuperheroes() {

        List<Superhero> superheroes = localDataSource.getSuperheroes();

        if (superheroes.isEmpty()) {
            superheroes = remoteDataSource.getSuperheroes();
            if (superheroes != null && !superheroes.isEmpty()) {
                List<Long> ids = localDataSource.addSuperheroes(superheroes);
                for (int i = 0; i < ids.size(); ++i) {
                    superheroes.get(i).setId(ids.get(i));
                }
            }
        }

        return superheroes;
    }

    @Override
    public Superhero getSuperhero(Long id) {
        return localDataSource.getSuperhero(id);
    }

    @Override
    public List<Long> addSuperheroes(List<Superhero> superheroes) {
        return localDataSource.addSuperheroes(superheroes);
    }
}
