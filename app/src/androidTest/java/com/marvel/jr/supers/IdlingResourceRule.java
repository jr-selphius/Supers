package com.marvel.jr.supers;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.marvel.jr.supers.di.ApplicationComponent;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class IdlingResourceRule implements TestRule {

    private IdlingResource resource;

    public IdlingResourceRule() {
        CustomApplication customApplication = (CustomApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        ApplicationComponent component = customApplication.getApplicationComponent();
        resource = OkHttp3IdlingResource.create("okhttp", component.getOkHttpClient());
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Espresso.registerIdlingResources(resource);
                base.evaluate();
                Espresso.unregisterIdlingResources(resource);
            }
        };
    }
}
