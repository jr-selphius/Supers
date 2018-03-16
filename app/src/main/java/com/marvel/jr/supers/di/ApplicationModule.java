package com.marvel.jr.supers.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.marvel.jr.supers.CustomApplication;
import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.datasource.local.AppDatabase;
import com.marvel.jr.supers.datasource.local.LocalDataSourceImpl;
import com.marvel.jr.supers.datasource.local.SuperheroDao;
import com.marvel.jr.supers.datasource.remote.RemoteDataSourceImpl;
import com.marvel.jr.supers.datasource.remote.SuperheroService;
import com.marvel.jr.supers.domain.GetSuperheroByIdUseCase;
import com.marvel.jr.supers.domain.GetSuperheroesUseCase;
import com.marvel.jr.supers.navigation.Navigator;
import com.marvel.jr.supers.ui.detail.HeroDetailPresenter;
import com.marvel.jr.supers.ui.heroes.HeroesPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final CustomApplication application;

    public ApplicationModule(CustomApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public Navigator provideNavigator(Context context) {
        return new Navigator(context);
    }

    @Provides
    @Singleton
    public SuperheroService provideRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.myjson.com")
                .build();

        return retrofit.create(SuperheroService.class);
    }

    @Provides
    @Singleton
    public RemoteDataSourceImpl provideRemoteDataSource(SuperheroService service) {
        return new RemoteDataSourceImpl(service);
    }

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "superheroes.database").build();
    }

    @Provides
    @Singleton
    public SuperheroDao provideDao(AppDatabase database) {
        return database.superheroDao();
    }

    @Provides
    @Singleton
    public LocalDataSourceImpl provideLocalDataSource(SuperheroDao dao) {
        return new LocalDataSourceImpl(dao);
    }

    @Provides
    @Singleton
    public HeroesRepository provideRepository(LocalDataSourceImpl localDataSource, RemoteDataSourceImpl remoteDataSource) {
        return new HeroesRepository(localDataSource, remoteDataSource);
    }

    @Provides
    public GetSuperheroesUseCase provideGetSuperheroesUseCase(HeroesRepository repo) {
        return new GetSuperheroesUseCase(repo);
    }

    @Provides
    public HeroesPresenter provideHeroesPresenter(GetSuperheroesUseCase getSuperheroesUseCase) {
        return new HeroesPresenter(getSuperheroesUseCase);
    }

    @Provides
    public GetSuperheroByIdUseCase provideGetSuperheroByIdUseCase(HeroesRepository repo) {
        return new GetSuperheroByIdUseCase(repo);
    }

    @Provides
    public HeroDetailPresenter provideHeroDetailPresenter(GetSuperheroByIdUseCase getSuperheroByIdUseCase) {
        return new HeroDetailPresenter(getSuperheroByIdUseCase);
    }

}
