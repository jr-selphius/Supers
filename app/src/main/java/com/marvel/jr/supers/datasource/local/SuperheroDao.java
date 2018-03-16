package com.marvel.jr.supers.datasource.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.marvel.jr.supers.model.Superhero;

import java.util.List;


@Dao
public interface SuperheroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Superhero> superheroes);

    @Query("SELECT * FROM superheroes")
    List<Superhero> getAll();

    @Query("SELECT * FROM superheroes WHERE id= :id")
    Superhero getSuperheroById(Long id);
}
