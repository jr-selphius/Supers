package com.marvel.jr.supers.screens.detail.domain;

import android.os.Handler;
import android.os.Looper;

import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.model.Superhero;

public class GetSuperheroByIdUseCase {

    public interface Callback {
        void onSuperheroByIdObtained(Superhero superhero);
    }

    private final HeroesRepository heroesRepository;

    public GetSuperheroByIdUseCase(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public void execute(final long id, final Callback callback) {
        new Thread(new Runnable() {
            @Override public void run() {
                getSuperhero(id, callback);
            }
        }).start();

    }

    private void getSuperhero(long id, final Callback callback) {
        final Superhero superhero = heroesRepository.getSuperhero(id);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override public void run() {
                callback.onSuperheroByIdObtained(superhero);
            }
        });

    }


}
