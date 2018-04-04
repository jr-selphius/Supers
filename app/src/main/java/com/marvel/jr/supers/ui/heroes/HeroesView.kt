package com.marvel.jr.supers.ui.heroes

import com.marvel.jr.supers.model.Superhero

interface HeroesView {
    fun showHeroes(heroes: List<Superhero>)
    fun showEmptyView()
    fun showProgressView()
    fun hideProgressView()
    fun navigateToDetail(id: Long)
}
