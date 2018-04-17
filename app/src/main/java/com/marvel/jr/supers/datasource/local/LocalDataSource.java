package com.marvel.jr.supers.datasource.local;

import com.marvel.jr.supers.datasource.remote.RemoteDataSource;
import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public interface LocalDataSource extends RemoteDataSource {

    Superhero getSuperhero(Long id);
    List<Long> addSuperheroes(List<Superhero> superheroes);

}
