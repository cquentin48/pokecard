package com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("/public/index.php/pokemon/{id}")
    fun getPokemonById(
            @Path("id") id: Int
    ): Call<PokemonDataRetrofit>

    @GET("/public/index.php/pokemonlist/{pageId}")
    fun getPokemonListData(
            @Path("pageId") pageId: Int,
            @Query("pageSize")pageSize:Int
    ): Single<PokemonList>

    @GET("public/index.php/types/all")
    fun getAllTypes(): Call<TypeList>

    @GET("public/index.php/craft/pokemon/{firstTypeId}/{secondTypeId}")
    fun generateRandomPokemon(
            @Path("firstTypeId") firstType:Int,
            @Path("secondTypeId") secondType:Int
    ): Call<PokemonRetrofit>
}
