package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import com.google.gson.GsonBuilder
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitSingleton {
    var retrofitInstance: PokemonAPI = buildInstance()
    val OWNAPIBASEURL = "https://walkemon.herokuapp.com/"

    private fun buildInstance(): PokemonAPI {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(OWNAPIBASEURL)
                .client(setTimeOut())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(PokemonAPI::class.java)
    }

    private fun setTimeOut(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build()
    }
}
