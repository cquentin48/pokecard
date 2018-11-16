package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonById(
            @Path("id") id: Int
    ): Call<PokemonRetrofit>
}
