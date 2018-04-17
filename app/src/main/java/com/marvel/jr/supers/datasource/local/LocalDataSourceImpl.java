package com.marvel.jr.supers.datasource.local;

import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public class LocalDataSourceImpl implements LocalDataSource {

    private SuperheroDao superheroDao;

    public LocalDataSourceImpl(SuperheroDao superheroDao) {
        this.superheroDao = superheroDao;
    }

    @Override
    public List<Superhero> getSuperheroes() {
        return superheroDao.getAll();
    }

    @Override
    public Superhero getSuperhero(Long id) {
        return superheroDao.getSuperheroById(id);
    }

    @Override
    public List<Long> addSuperheroes(List<Superhero> superheroes) {
        return superheroDao.insertAll(superheroes);
    }
}
