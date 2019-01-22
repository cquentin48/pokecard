package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonList{
    @SerializedName("count")
    @Expose
    private lateinit var pokemonList : List<PokemonRetrofit>

    var PokemonList:List<PokemonRetrofit>
    get() = pokemonList
    set(value){
        pokemonList = value
    }
}
