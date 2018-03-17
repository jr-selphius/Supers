package com.marvel.jr.supers.ui.detail

import com.marvel.jr.supers.model.Superhero

interface HeroView {
    fun showHero(hero: Superhero)
    fun showHeroNotFound()
    fun hideMainContent()
}
