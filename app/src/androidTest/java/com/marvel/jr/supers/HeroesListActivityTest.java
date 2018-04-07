package com.marvel.jr.supers;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.marvel.jr.supers.screens.heroes.ui.HeroesListActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class HeroesListActivityTest {

    private static final String MALFORMED_JSON = "malformed json";

    @Rule
    public ActivityTestRule<HeroesListActivity> activityRule = new ActivityTestRule<>(HeroesListActivity.class, true, false);

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Before
    public void setUp() {
        TestCustomApplication app = (TestCustomApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        String url = mockWebServerRule.server.url("/").toString();
        Log.d("TAG", url);
        app.setBaseUrl(url);
    }

    @After
    public void tearDown() {
        TestCustomApplication app = (TestCustomApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.getApplicationComponent().getRoomDatabase().clearAllTables();
    }

    @Test
    public void shouldShowListOfHeroesWhenThereAreSuperheroes() {

        mockWebServerRule.server.enqueue(new MockResponse().setBody("{\"superheroes\":[{\"name\":\"Spiderman\",\"photo\":\"https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg\",\"realName\":\"Peter Benjamin Parker\",\"height\":\"1.77m\",\"power\":\"Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.\",\"abilities\":\"Peter is an accomplished scientist, inventor and photographer.\",\"groups\":\"Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws\"},{\"name\":\"Captain Marvel\",\"photo\":\"https://i.annihil.us/u/prod/marvel/i/mg/c/10/537ba5ff07aa4/standard_xlarge.jpg\",\"realName\":\"Carol Danvers\",\"height\":\"1.80m\",\"power\":\"Ms. Marvel's current powers include flight, enhanced strength, durability and the ability to shoot concussive energy bursts from her hands.\",\"abilities\":\"Ms. Marvel is a skilled pilot \\u0026 hand to hand combatant\",\"groups\":\"Avengers, formerly Queen's Vengeance, Starjammers\"}]}"));

        startActivity();

        onView(withId(R.id.recycler)).check(matches(atPosition(0, hasDescendant(withText("Spiderman")))));
        onView(withId(R.id.recycler)).check(matches(atPosition(1, hasDescendant(withText("Captain Marvel")))));
    }

    @Test
    public void shouldMessageWhenThereAreNotSuperheroes() {

        mockWebServerRule.server.enqueue(new MockResponse().setBody("{\"superheroes\":[]}"));

        startActivity();

        onView(withId(R.id.heroesEmptyView)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldMessageWhenStatus404() {

        mockWebServerRule.server.enqueue(new MockResponse().setResponseCode(404));

        startActivity();

        onView(withId(R.id.heroesEmptyView)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldMessageWhenMalformedJson() {

        mockWebServerRule.server.enqueue(new MockResponse().setBody(MALFORMED_JSON));

        startActivity();

        onView(withId(R.id.heroesEmptyView)).check(matches(isDisplayed()));
    }

    private void startActivity() {
        activityRule.launchActivity(null);
    }

    private Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

}
