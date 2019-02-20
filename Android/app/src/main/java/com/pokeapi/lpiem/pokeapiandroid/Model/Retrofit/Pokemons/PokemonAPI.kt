package com.pokeapi.lpiem.pokeapiandroid.Model.Retrofit.Pokemons

import retrofit2.Call
import retrofit2.http.GET

interface PokemonAPI {

    /**
     * Fetch pokemon list for the pokedex fragment
     * @return list of pokemon name and sprites
     */
    @GET("/public/index.php/pokemon/")
    fun getPokemonListData(): Call<PokemonList>
}
