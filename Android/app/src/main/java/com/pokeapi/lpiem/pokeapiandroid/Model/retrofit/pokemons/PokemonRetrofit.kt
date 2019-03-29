package com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonRetrofit(
        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("sprite")
        @Expose
        val sprite: String,

        @SerializedName("id")
        @Expose
        val id:Int
)