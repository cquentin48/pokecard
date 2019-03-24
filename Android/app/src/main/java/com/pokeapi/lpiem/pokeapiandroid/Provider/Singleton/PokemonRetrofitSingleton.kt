package com.pokeapi.lpiem.pokeapiandroid.Provider

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.RetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonAPI
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonDataRetrofit
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.SinglePokemonRetrofitPokedex
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PokemonRetrofitSingleton {
    var singlePokemonData = MutableLiveData<PokemonDataRetrofit>()

    fun loadSinglePokemonData(pokemonId:Int){
        val instance = RetrofitSingleton.retrofitInstance as PokemonAPI
        val callPokemon = instance.getPokemonById(pokemonId)

        callPokemon.enqueue(object: Callback<PokemonDataRetrofit>{
            override fun onFailure(call: Call<PokemonDataRetrofit>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
            }

            override fun onResponse(call: Call<PokemonDataRetrofit>, response: Response<PokemonDataRetrofit>) {
                if(response.isSuccessful) {
                    singlePokemonData.postValue(response.body())
                }
            }

        })
    }
}