package com.marvel.jr.supers.ui.detail

import android.os.Bundle
import android.widget.Toast
import com.marvel.jr.supers.R
import com.marvel.jr.supers.ui.BaseActivity

class HeroDetailActivity : BaseActivity() {

    companion object {
        val SUPERHERO_ID = "SUPERHERO_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        val id = intent.extras[SUPERHERO_ID]
        Toast.makeText(this, "The id clicked is : $id", Toast.LENGTH_LONG).show()
    }
}
