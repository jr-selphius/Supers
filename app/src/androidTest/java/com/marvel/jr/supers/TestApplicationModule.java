package com.marvel.jr.supers;

import android.support.test.espresso.idling.concurrent.IdlingThreadPoolExecutor;

import com.marvel.jr.supers.di.ApplicationModule;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.appflate.restmock.RESTMockServer;

public class TestApplicationModule extends ApplicationModule {

    public TestApplicationModule(CustomApplication application) {
        super(application);
    }

    @Override
    public ThreadPoolExecutor provideThreadPoolExecutor() {

        final int POOL_SIZE = 2;
        final int MAX_POOL_SIZE = 4;
        final int TIMEOUT = 30;

        return new IdlingThreadPoolExecutor(
                "ThreadPoolExecutor",
                POOL_SIZE,
                MAX_POOL_SIZE,
                TIMEOUT,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(POOL_SIZE),
                Executors.defaultThreadFactory());
    }

    @Override
    public String provideEndpoint() {
        return RESTMockServer.getUrl();
    }
}
