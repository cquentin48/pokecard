package com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonList(
        @SerializedName("singlePokemonListPokedex")
        @Expose
        val singlePokemonListPokedex: List<SinglePokemonRetrofitPokedex>
)
