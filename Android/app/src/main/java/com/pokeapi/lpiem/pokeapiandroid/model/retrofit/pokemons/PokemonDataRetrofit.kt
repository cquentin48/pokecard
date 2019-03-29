package com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons

data class PokemonDataRetrofit(val id:Int,
                               val height:Int,
                               val sprites:String,
                               val types:MutableList<String>,
                               val weight:Int,
                               val name:String,
                               val pokedexEntry: String)