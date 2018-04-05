package com.marvel.jr.supers.screens.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.marvel.jr.supers.CustomApplication
import com.marvel.jr.supers.R
import com.marvel.jr.supers.extensions.loadImage
import com.marvel.jr.supers.model.Superhero
import com.marvel.jr.supers.screens.detail.ui.HeroDetailPresenter
import kotlinx.android.synthetic.main.activity_hero_detail.*
import javax.inject.Inject

class HeroDetailActivity : AppCompatActivity(), HeroView {

    companion object {
        const val SUPERHERO_ID = "SUPERHERO_ID"
    }

    @Inject
    lateinit var heroDetailPresenter: HeroDetailPresenter

    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        (application as CustomApplication).createHeroDetailComponent().inject(this)
        heroDetailPresenter.setView(this)

        id = intent.extras[SUPERHERO_ID] as Long

        if (savedInstanceState != null) {
            id = savedInstanceState.getLong(SUPERHERO_ID)
        }

        heroDetailPresenter.getSuperheroById(id)
    }

    override fun showHero(hero: Superhero) {
        superheroImage.loadImage(hero.photo)
        realName.text = hero.realName
        name.text = hero.name
        height.text = hero.height
        groups.text = hero.groups
        abilities.text = hero.abilities
        power.text = hero.power
    }

    override fun hideMainContent() {
        mainContent.visibility = View.GONE
    }

    override fun showHeroNotFound() {
        heroNotFoundMessage.visibility = View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putLong(SUPERHERO_ID, id)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as CustomApplication).releaseHeroDetailComponent()
        heroDetailPresenter.release()
    }
}
