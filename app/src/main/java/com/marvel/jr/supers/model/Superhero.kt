package com.marvel.jr.supers.model

import com.google.gson.annotations.Expose

data class Superhero(@Expose val name: String, @Expose val photo: String,
                     @Expose val realName: String, @Expose val height: String,
                     @Expose val power: String, @Expose val abilities: String,
                     @Expose val groups: String)