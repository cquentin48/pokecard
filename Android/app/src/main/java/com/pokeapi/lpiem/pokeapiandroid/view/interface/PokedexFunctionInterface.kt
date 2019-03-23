package com.pokeapi.lpiem.pokeapiandroid.view.`interface`

import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit

interface PokedexFunctionInterface {
    /**
     * Fetch data from webservice
     * @param pokedexView fragment support for the data to be loaded
     */
    fun initPokedex(pokemonImportData : List<PokemonRetrofit>)
}