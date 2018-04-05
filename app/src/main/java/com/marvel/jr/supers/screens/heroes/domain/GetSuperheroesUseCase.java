package com.marvel.jr.supers.screens.heroes.domain;

import android.os.Handler;
import android.os.Looper;

import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.model.Superhero;

import java.util.List;

public class GetSuperheroesUseCase {

    public interface Callback {
        void onSuperheroesObtained(List<Superhero> superheroes);
    }

    private final HeroesRepository heroesRepository;

    public GetSuperheroesUseCase(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public void execute(final Callback callback) {
        new Thread(new Runnable() {
            @Override public void run() {
                getSuperheroes(callback);
            }
        }).start();
    }

    private void getSuperheroes(final Callback callback) {
        final List<Superhero> superheroes = heroesRepository.getSuperheroes();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override public void run() {
                callback.onSuperheroesObtained(superheroes);
            }
        });
    }
}
