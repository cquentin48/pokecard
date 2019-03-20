package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel.ProfileFragmentAdapterModel
import com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel.ProfileSubFragmentRetrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("/public/index.php/pokemon/")
    fun getPokemonListData(): Call<PokemonList>

    @GET("/pokemon/")
    fun getPokemonById(): Call<PokemonRetrofit>

    @POST("/public/index.php/user/friends/load")
    fun getUserFriendsList(
            @Path("userId") userId:String
    ) : Call<ProfileSubFragmentRetrofit>
}
