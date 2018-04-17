package com.marvel.jr.supers.di;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.espresso.IdlingRegistry;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.marvel.jr.supers.BuildConfig;
import com.marvel.jr.supers.CustomApplication;
import com.marvel.jr.supers.domain.UseCaseHandler;
import com.marvel.jr.supers.domain.UseCaseThreadPoolScheduler;
import com.marvel.jr.supers.datasource.HeroesRepository;
import com.marvel.jr.supers.datasource.local.AppDatabase;
import com.marvel.jr.supers.datasource.local.LocalDataSourceImpl;
import com.marvel.jr.supers.datasource.local.SuperheroDao;
import com.marvel.jr.supers.datasource.remote.RemoteDataSourceImpl;
import com.marvel.jr.supers.datasource.remote.SuperheroService;
import com.marvel.jr.supers.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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
    public OkHttpClient provideHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        if (BuildConfig.DEBUG) {
            IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttpClient));
        }
        return okHttpClient;
    }

    @Provides
    @Singleton
    public SuperheroService provideRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(application.getBaseUrl())
                .client(okHttpClient)
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
    public UseCaseThreadPoolScheduler provideThreadPoolScheduler() {
        return new UseCaseThreadPoolScheduler();
    }

    @Provides
    @Singleton
    public UseCaseHandler provideUseCaseHandler(UseCaseThreadPoolScheduler threadPoolScheduler) {
        return new UseCaseHandler(threadPoolScheduler);
    }


}
