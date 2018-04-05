package com.marvel.jr.supers.navigation

import android.content.Context
import android.content.Intent
import com.marvel.jr.supers.screens.detail.HeroDetailActivity

class Navigator(private val context: Context) {

    fun startDetailActivity(id: Long) {
        val intent = Intent(context, HeroDetailActivity::class.java)
        intent.putExtra(HeroDetailActivity.SUPERHERO_ID, id)
        context.startActivity(intent)
    }
}
