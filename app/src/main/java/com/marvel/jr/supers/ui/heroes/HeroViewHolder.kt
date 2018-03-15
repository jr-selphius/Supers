package com.marvel.jr.supers.ui.heroes

import android.support.v7.widget.RecyclerView
import android.view.View
import com.marvel.jr.supers.extensions.loadImage
import com.marvel.jr.supers.model.Superhero
import kotlinx.android.synthetic.main.hero_item_list.view.*

class HeroViewHolder(private val view: View, private val itemClick:(Long) -> Unit) : RecyclerView.ViewHolder(view) {

    fun bindHero(superhero: Superhero) {
        with(view) {
            name.text = superhero.name
            realName.text = superhero.realName
            abilities.text = superhero.height
            groups.text = superhero.groups
            power.text = superhero.power
            meters.text = superhero.height
            photo.loadImage(superhero.photo)
            setOnClickListener {
                itemClick(superhero.id)
            }
        }
    }
}