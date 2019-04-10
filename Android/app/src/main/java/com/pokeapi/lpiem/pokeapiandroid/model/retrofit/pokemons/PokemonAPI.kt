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

    @GET("public/index.php/users/{userId}/pokemonList")
    fun getPokemoCollection(
            @Path("userId") userId:String
    ): Call<ArrayList<PokemonFirebase>>

    @GET("/public/index.php/exchanges/confirm/")
    fun confirmTrade(
    @Path("pokemonIdWanted") pokemonIdWanted : Int,
    @Path(" originalPokemonId")  originalPokemonId : Int,
    @Path("userId") userId : Int,
    @Path("friendUserId") friendUserId : Int,
    ) : Call<ClassQuiGèreLeRetourDesDonnées>
}
