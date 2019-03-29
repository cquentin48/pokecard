package com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("/public/index.php/pokemon/{id}")
    fun getPokemonById(
            @Path("id") id: Int
    ): Call<PokemonDataRetrofit>

    /*@GET("/api/v2/pokemon-species/{id}")
    fun getPokemonSpecies(
            @Path("id") id: Int
    ): Call<Species>*/

    @GET("/public/index.php/pokemonlist/{pageId}")
    fun getPokemonListData(
            @Path("pageId") pageId: Int,
            @Query("pageSize")pageSize:Int
    ): Single<PokemonList>

    @GET("/pokemon/")
    fun getPokemonById(): Call<PokemonRetrofit>

    @GET("public/index.php/types/all")
    fun getAllTypes(): Call<TypeList>

    @POST("public/index.php/craft/pokemon")
    fun generateRandomPokemon(
            @Path("firstType") firstType:Int,
            @Path("secondType") secondType:Int
    ): Call<PokemonRetrofit>
}
