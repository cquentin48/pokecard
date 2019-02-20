package com.pokeapi.lpiem.pokeapiandroid.Model.Retrofit.Pokemons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonList(
        @SerializedName("pokemonList")
        @Expose
        val pokemonList: List<PokemonRetrofit>
)
