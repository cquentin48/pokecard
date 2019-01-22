package com.pokeapi.lpiem.pokeapiandroid.View

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexFunctionInterface {
    fun initPokedex()
}