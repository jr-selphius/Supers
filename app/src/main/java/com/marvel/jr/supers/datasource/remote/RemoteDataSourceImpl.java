package com.marvel.jr.supers.datasource.remote;

import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.model.Superheroes;

import java.io.IOException;
import java.util.List;
import retrofit2.Response;

public class RemoteDataSourceImpl implements RemoteDataSource {

    public SuperheroService service;

    public RemoteDataSourceImpl(SuperheroService service) {
        this.service = service;
    }

    @Override
    public List<Superhero> getSuperheroes() {

        Response<Superheroes> response = null;

        try {
            response = service.getSuperheroes().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.body().getSuperheroes();
    }
}
