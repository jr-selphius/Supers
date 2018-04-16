/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marvel.jr.supers;

import android.os.Handler;
import android.support.test.espresso.idling.concurrent.IdlingThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

    private final Handler mHandler = new Handler();

    //public static final int POOL_SIZE = 2;

    //public static final int MAX_POOL_SIZE = 4;
    public static final int POOL_SIZE = 1;
    public static final int MAX_POOL_SIZE = 1;

    public static final int TIMEOUT = 30;

    private ThreadPoolExecutor mThreadPoolExecutor;

    public UseCaseThreadPoolScheduler() {
        //TODO: provide that as a mocked dependency, because it is registring itself as an idling resource in the production code, which is a bad practice.
        mThreadPoolExecutor = new IdlingThreadPoolExecutor(
                "asdf",
                POOL_SIZE,
                MAX_POOL_SIZE,
                TIMEOUT,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE),
                Executors.defaultThreadFactory());
    }

    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                                 final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(response);
            }
        });
    }

    @Override
    public <V extends UseCase.ResponseValue> void onError(
            final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onError();
            }
        });
    }

}