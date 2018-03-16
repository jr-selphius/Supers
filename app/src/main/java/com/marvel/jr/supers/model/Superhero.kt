package com.marvel.jr.supers.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "superheroes")
data class Superhero(@PrimaryKey(autoGenerate = true) var id: Long,
                     @ColumnInfo @Expose var name: String,
                     @ColumnInfo @Expose var photo: String,
                     @ColumnInfo(name = "real_name") @Expose var realName: String,
                     @ColumnInfo @Expose var height: String,
                     @ColumnInfo @Expose var power: String,
                     @ColumnInfo @Expose var abilities: String,
                     @ColumnInfo @Expose var groups: String)