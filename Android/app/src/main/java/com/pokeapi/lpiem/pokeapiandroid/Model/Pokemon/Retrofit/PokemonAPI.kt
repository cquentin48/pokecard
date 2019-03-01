package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonById(
            @Path("id") id: Int
    ): Call<PokemonRetrofit>

    @GET("/api/v2/pokemon-species/{id}")
    fun getPokemonSpecies(
            @Path("id") id: Int
    ): Call<Species>

    @GET("public/index.php/pokemonlist/{pageId}")
    fun getPokemonListData(
            @Path("pageId") pageId: Int,
            pageSize:Int
    ): Call<PokemonList>

    @GET("/pokemon/")
    fun getPokemonById(): Call<PokemonRetrofit>
}
