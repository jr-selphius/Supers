package com.marvel.jr.supers;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.marvel.jr.supers.screens.heroes.ui.HeroesListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;

public class HeroesListActivityTest {

    @Rule
    public ActivityTestRule<HeroesListActivity> activityRule = new ActivityTestRule<>(HeroesListActivity.class, true, false);

    @Rule
    public IdlingResourceRule idlingResourceRule = new IdlingResourceRule();

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Before
    public void setUp() {
        TestCustomApplication app = (TestCustomApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.setBaseUrl(mockWebServerRule.server.url("/").toString());
    }

    @Test
    public void shouldShowListOfHeroes() {

        mockWebServerRule.server.enqueue(new MockResponse().setBody("{\"superheroes\":[{\"name\":\"Spiderman\",\"photo\":\"https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg\",\"realName\":\"Peter Benjamin Parker\",\"height\":\"1.77m\",\"power\":\"Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.\",\"abilities\":\"Peter is an accomplished scientist, inventor and photographer.\",\"groups\":\"Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws\"},{\"name\":\"Captain Marvel\",\"photo\":\"https://i.annihil.us/u/prod/marvel/i/mg/c/10/537ba5ff07aa4/standard_xlarge.jpg\",\"realName\":\"Carol Danvers\",\"height\":\"1.80m\",\"power\":\"Ms. Marvel's current powers include flight, enhanced strength, durability and the ability to shoot concussive energy bursts from her hands.\",\"abilities\":\"Ms. Marvel is a skilled pilot \\u0026 hand to hand combatant\",\"groups\":\"Avengers, formerly Queen's Vengeance, Starjammers\"}]}"));

        activityRule.launchActivity(null);
    }

}
