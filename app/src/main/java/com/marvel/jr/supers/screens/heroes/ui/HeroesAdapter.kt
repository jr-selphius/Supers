package com.marvel.jr.supers.screens.heroes.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marvel.jr.supers.R
import com.marvel.jr.supers.model.Superhero

class HeroesAdapter(private var heroesList: List<Superhero>, private val heroClick: (Long) -> Unit) : RecyclerView.Adapter<HeroViewHolder>() {

    fun swapHeroes(heroesList: List<Superhero>) {
        this.heroesList = heroesList
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bindHero(heroesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.hero_item_list, parent, false)
        return HeroViewHolder(view, heroClick)
    }

    override fun getItemCount(): Int = heroesList.size

}