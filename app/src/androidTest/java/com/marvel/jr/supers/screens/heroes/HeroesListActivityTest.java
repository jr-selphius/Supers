package com.marvel.jr.supers.screens.heroes;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.marvel.jr.supers.CustomApplication;
import com.marvel.jr.supers.R;
import com.marvel.jr.supers.TestApplicationModule;
import com.marvel.jr.supers.di.ApplicationComponent;
import com.marvel.jr.supers.model.Superhero;
import com.marvel.jr.supers.screens.heroes.ui.HeroesListActivity;
import com.marvel.jr.supers.screens.utils.recyclerview.RecyclerViewInteraction;
import com.marvel.jr.supers.screens.utils.recyclerview.RecyclerViewWithContentIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.appflate.restmock.RESTMockServer;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.appflate.restmock.utils.RequestMatchers.pathContains;

public class HeroesListActivityTest {

    private static final String MALFORMED_JSON = "malformed json";
    private static final String HERO_BODY = "{\"name\":\"Spiderman\",\"photo\":\"https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg\",\"realName\":\"Peter Benjamin Parker\",\"height\":\"1.77m\",\"power\":\"Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.\",\"abilities\":\"Peter is an accomplished scientist, inventor and photographer.\",\"groups\":\"Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws\"}";
    private static String SOME_SUPERHEROES_BODY = "{\"superheroes\":[" + HERO_BODY + ","+ HERO_BODY + "," + HERO_BODY + "]}";
    private static final String SUPERHEROES_EMPTY_BODY = "{\"superheroes\":[]}";

    private final Superhero superhero = new Superhero(10, "Spiderman", "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg", "Peter Benjamin Parker", "1.77m", "Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.", "Peter is an accomplished scientist, inventor and photographer.", "Avengers, formerly the Secret Defenders, New Fantastic Four, the Outlaws\"");
    private final List<Superhero> superheroes = Arrays.asList(superhero, superhero, superhero);

    @Rule public DaggerMockRule<ApplicationComponent> daggerRule =
            new DaggerMockRule<>(ApplicationComponent.class, new TestApplicationModule(
                    (CustomApplication) InstrumentationRegistry.getInstrumentation()
                    .getTargetContext()
                    .getApplicationContext())).set(
                        new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
                            @Override public void setComponent(ApplicationComponent component) {
                                CustomApplication app =
                                        (CustomApplication) InstrumentationRegistry.getInstrumentation()
                                                .getTargetContext()
                                                .getApplicationContext();
                                app.setApplicationComponent(component);
                            }
                        });

    @Rule
    public ActivityTestRule<HeroesListActivity> activityRule = new ActivityTestRule<>(HeroesListActivity.class, true, false);

    @Before
    public void setUp() {
        RESTMockServer.reset();
    }

    @After
    public void tearDown() {
        CustomApplication app = (CustomApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.getApplicationComponent().getRoomDatabase().clearAllTables();
    }

    @Test
    public void shouldShowListOfHeroesWhenThereAreSuperheroes() {

        MockResponse mockResponse = new MockResponse().setBody(SOME_SUPERHEROES_BODY);
        RESTMockServer.whenGET(pathContains("/")).thenReturn(mockResponse);

        HeroesListActivity heroesListActivity = activityRule.launchActivity(null);

        RecyclerViewWithContentIdlingResource idling = new RecyclerViewWithContentIdlingResource(heroesListActivity, R.id.recycler, superheroes.size());
        IdlingRegistry.getInstance().register(idling);

        RecyclerViewInteraction.<Superhero>onRecyclerView(withId(R.id.recycler))
                .withItems(superheroes)
                .check(new RecyclerViewInteraction.ItemViewAssertion<Superhero>() {
                    @Override public void check(Superhero superHero, View view, NoMatchingViewException e) {
                        matches(hasDescendant(withText(superHero.getName()))).check(view, e);
                    }
                });

        IdlingRegistry.getInstance().unregister(idling);
    }

    @Test
    public void scrollHeroes() {

        MockResponse mockResponse = new MockResponse().setBody(SOME_SUPERHEROES_BODY);
        RESTMockServer.whenGET(pathContains("/")).thenReturn(mockResponse);

        startActivity();

        onView(withId(R.id.recycler)).perform(RecyclerViewActions.scrollToPosition(superheroes.size()));
    }

    @Test
    public void shouldMessageWhenThereAreNotSuperheroes() {

        RESTMockServer.whenGET(pathContains("/")).thenReturn(new MockResponse().setBody(SUPERHEROES_EMPTY_BODY));

        startActivity();

        onView(withId(R.id.heroesEmptyView)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldMessageWhenStatus404() {

        RESTMockServer.whenGET(pathContains("/")).thenReturn(new MockResponse().setResponseCode(404));

        startActivity();

        onView(withId(R.id.heroesEmptyView)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldMessageWhenMalformedJson() {

        RESTMockServer.whenGET(pathContains("/")).thenReturn(new MockResponse().setBody(MALFORMED_JSON));

        startActivity();

        onView(withId(R.id.heroesEmptyView)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldOpenDetailActivity() {

        RESTMockServer.whenGET(pathContains("/")).thenReturn(new MockResponse().setBody(SOME_SUPERHEROES_BODY));

        startActivity();

        onView(withId(R.id.recycler)).
                perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    private void startActivity() {
        activityRule.launchActivity(null);
    }
}
