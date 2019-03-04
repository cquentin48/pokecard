package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonList{
    @SerializedName("pokemonList")
    @Expose
    private lateinit var pokemonList : MutableList<PokemonRetrofit>

    @SerializedName("count")
    @Expose
    var count = 0

    var PokemonList:MutableList<PokemonRetrofit>
    get() = pokemonList
    set(value){
        pokemonList = value
    }
}
