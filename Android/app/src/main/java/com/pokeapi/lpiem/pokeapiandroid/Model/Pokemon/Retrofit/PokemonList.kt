package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonList : @ParameterName(name = "repos") List<PokemonList> {
    @SerializedName("pokemonList")
    @Expose
    private lateinit var pokemonList : List<PokemonRetrofit>

    @SerializedName("count")
    @Expose
    var count = 0

    var PokemonList:List<PokemonRetrofit>
    get() = pokemonList
    set(value){
        pokemonList = value
    }
}
