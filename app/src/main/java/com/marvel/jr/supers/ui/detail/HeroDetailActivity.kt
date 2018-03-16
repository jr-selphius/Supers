package com.marvel.jr.supers.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.marvel.jr.supers.CustomApplication
import com.marvel.jr.supers.R
import com.marvel.jr.supers.model.Superhero
import javax.inject.Inject

class HeroDetailActivity : AppCompatActivity(), HeroView {

    companion object {
        val SUPERHERO_ID = "SUPERHERO_ID"
    }

    @Inject
    lateinit var heroDetailPresenter: HeroDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        (application as CustomApplication).component.inject(this)
        heroDetailPresenter.setView(this)

        val id = intent.extras[SUPERHERO_ID] as Long
        heroDetailPresenter.getSuperheroById(id)
    }

    override fun showHero(hero: Superhero) {
        Toast.makeText(this, "The id clicked is : ${hero.name}", Toast.LENGTH_LONG).show()
    }
}
