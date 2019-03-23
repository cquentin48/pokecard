package com.pokeapi.lpiem.pokeapiandroid.provider

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val CUSTOMPOKEAPIBASEURL = "https://walkemon.herokuapp.com/"

object RetrofitSingleton {

    /**
     * Build an instance of the pokemonAPI which to Run
     * @return instance of pokemonAPI retrofit routes in order to fetch data from the api
     */
    fun buildInstance(): PokemonAPI {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(CUSTOMPOKEAPIBASEURL).addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(PokemonAPI::class.java)
    }
}
