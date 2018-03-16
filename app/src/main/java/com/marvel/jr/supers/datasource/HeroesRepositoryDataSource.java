package com.marvel.jr.supers.datasource;

import com.marvel.jr.supers.datasource.local.LocalDataSource;
import com.marvel.jr.supers.datasource.remote.RemoteDataSource;

public interface HeroesRepositoryDataSource extends RemoteDataSource, LocalDataSource {
}
