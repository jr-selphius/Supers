package com.marvel.jr.supers.ui.heroes

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.marvel.jr.supers.R
import com.marvel.jr.supers.model.Superhero
import com.marvel.jr.supers.ui.BaseActivity
import com.marvel.jr.supers.ui.detail.HeroDetailActivity
import kotlinx.android.synthetic.main.activity_heroes_list.*
import java.util.*

class HeroesListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes_list)

        val superhero = Superhero(10,"Spiderman", "https://i.annihil.us/u/prod/marvel/i/mg/c/10/537ba5ff07aa4/standard_xlarge.jpg", "peter", "1.80m", "Strength", "Sentido aracnido", "vengadores")
        val heroesList = Arrays.asList(superhero, superhero, superhero)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = HeroesAdapter(heroesList) {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(id: Long) {
        val intent = Intent(this, HeroDetailActivity::class.java)
        intent.putExtra(HeroDetailActivity.SUPERHERO_ID, id)
        startActivity(intent)
    }
}
