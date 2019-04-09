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

    @GET("public/index.php/users/{userId}/craft/pokemon/{firstTypeId}/{secondTypeId}")
    fun generateRandomPokemon(
            @Path("firstTypeId") firstType:Int,
            @Path("secondTypeId") secondType:Int,
            @Path("userId") userId:String
    ): Call<PokemonRetrofit>

    @GET("public/index.php/users/{userId}/pokemonList")
    fun getPokemonCollection(
            @Path("userId") userId:String
    ): Call<PokemonCollectionFirebase>

    @GET("/public/index.php/users/{userId}/add/{pokemonId}/{nickname}/{creationDate}")
    fun addPokemonToFirebase(
            @Path("userId") userId: String,
            @Path("pokemonId") pokemonId:Int,
            @Path("nickname") nickname:String,
            @Path("creationDate") creationDate: String
    ) : Call<ErrorMessageReturn>
}
