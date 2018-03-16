package com.marvel.jr.supers.datasource.remote;

import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public interface RemoteDataSource {

    List<Superhero> getSuperheroes();

}
