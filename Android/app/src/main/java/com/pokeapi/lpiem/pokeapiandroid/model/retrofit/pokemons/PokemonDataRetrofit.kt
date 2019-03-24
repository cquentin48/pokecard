package com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons

data class PokemonDataRetrofit(val name:String,
                               val types:MutableList<PokemonTypes>,
                               val pokedexEntries: MutableList<PokedexEntries>,
                               val idPokedex:String,
                               val height:Float,
                               val weight:Float)