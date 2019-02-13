package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import android.util.Log
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.*
import com.pokeapi.lpiem.pokeapiandroid.View.PokedexListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.firebase.auth.FirebaseUser


class AppProviderSingleton {
    private lateinit var firebaseUser:FirebaseUser
    var User:FirebaseUser
        get() = firebaseUser
        set(newValue){
            firebaseUser = newValue
        }

    var pokemonList:MutableList<PokemonData> ? = mutableListOf()

    fun addPokemonToList(newPokemon : PokemonData){
        pokemonList!!.add(newPokemon)
    }

    fun getPokeList(pokedexView: PokedexListView){
        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonListData()

        callPokemon.enqueue(object : Callback<PokemonList> {

            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (response.isSuccessful) {
                    val returnedData = response.body()
                    pokedexView.initPokedex(returnedData!!.PokemonList)
                } else {
                    Log.d("Error", "Error while fetching data")
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    companion object {

        private var initialized = false
        private var instance: AppProviderSingleton? = null

        fun getInstance(): AppProviderSingleton {
            return if (initialized) {
                instance!!
            } else {
                initialized = true
                instance = AppProviderSingleton()
                instance!!
            }
        }
    }
}
