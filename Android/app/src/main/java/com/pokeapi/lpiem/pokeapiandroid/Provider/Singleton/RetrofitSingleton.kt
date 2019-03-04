package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import com.google.gson.GsonBuilder
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonAPI

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    private var instance: PokemonAPI? = null
    val POKEMONBASEURL = "https://pokeapi.co/"
    val OWNAPIBASEURL = "https://walkemon.herokuapp.com/"

    fun getInstance(): PokemonAPI? {
        if (instance == null) {
            instance = buildInstance()
        }
        return instance
    }

    private fun buildInstance(): PokemonAPI {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(OWNAPIBASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(PokemonAPI::class.java)
    }
}
