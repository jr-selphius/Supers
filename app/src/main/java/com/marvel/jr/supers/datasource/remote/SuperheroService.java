package com.marvel.jr.supers.datasource.remote;

import com.marvel.jr.supers.model.Superheroes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SuperheroService {

    @GET("/bins/bvyob/")
    Call<Superheroes> getSuperheroes();

}
