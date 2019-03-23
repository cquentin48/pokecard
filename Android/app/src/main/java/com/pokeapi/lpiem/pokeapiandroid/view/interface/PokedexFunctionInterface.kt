package com.pokeapi.lpiem.pokeapiandroid.View.Interface

import com.pokeapi.lpiem.pokeapiandroid.Model.Retrofit.Pokemons.PokemonRetrofit

interface PokedexFunctionInterface {
    /**
     * Fetch data from webservice
     * @param pokedexView fragment support for the data to be loaded
     */
    fun initPokedex(pokemonImportData : List<PokemonRetrofit>)
}