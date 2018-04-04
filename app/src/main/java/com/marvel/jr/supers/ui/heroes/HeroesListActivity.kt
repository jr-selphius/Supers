package com.marvel.jr.supers.ui.heroes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.marvel.jr.supers.CustomApplication
import com.marvel.jr.supers.R
import com.marvel.jr.supers.model.Superhero
import com.marvel.jr.supers.navigation.Navigator
import kotlinx.android.synthetic.main.activity_heroes_list.*
import javax.inject.Inject

class HeroesListActivity : AppCompatActivity(), HeroesView {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var heroesPresenter: HeroesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes_list)

        (application as CustomApplication).createHeroesComponent().inject(this)
        heroesPresenter.setView(this)

        heroesPresenter.getSuperheroes()
    }

    override fun showHeroes(heroes: List<Superhero>) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = HeroesAdapter(heroes) {
            heroesPresenter.heroClicked(it)
        }
    }

    override fun showEmptyView() {
        heroesEmptyView.visibility = View.VISIBLE
    }

    override fun showProgressView() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        progress.visibility = View.GONE
    }

    override fun navigateToDetail(id: Long) {
        navigator.startDetailActivity(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as CustomApplication).releaseHeroesComponent()
    }
}
