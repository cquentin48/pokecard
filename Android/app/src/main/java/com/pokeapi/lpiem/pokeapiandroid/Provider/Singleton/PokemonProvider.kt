package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton


import android.util.Log

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController
import com.pokeapi.lpiem.pokeapiandroid.View.MainAppActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonProvider : Callback<PokemonRetrofit> {
    var pokemonRetrofitInput: PokemonRetrofit? = null
    var pokemonName: String? = null
    private var message: String? = null

    fun start(googleConnexionResult: MainAppActivity) {

        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonById(6)

        callPokemon.enqueue(object : Callback<PokemonRetrofit> {

            override fun onResponse(call: Call<PokemonRetrofit>, response: Response<PokemonRetrofit>) {
                if (response.isSuccessful) {
                    val pokemonRetrofitResulted = response.body()
                    pokemonName = "Nom du pokémon : " + pokemonRetrofitResulted!!.name!!
                    setPokemon(pokemonRetrofitResulted)
                    googleConnexionResult.addPokemonToList(pokemonRetrofitResulted)
                } else {
                    Log.d("Erreur", "Erreur de connexion")
                }
            }

            override fun onFailure(call: Call<PokemonRetrofit>, t: Throwable) {
                Log.e("Erreur", t.localizedMessage)
                t.printStackTrace()
            }
        })
        //        Log.d("Nom du pokémon",this.pokemonName+"");
    }

    fun setPokemon(poke: PokemonRetrofit?) {
        this.pokemonRetrofitInput = poke
    }

    @Synchronized
    private fun fetchData(response: Response<PokemonRetrofit>) {
        val rawPokemonRetrofit = response.body()

        message += "pokemonList people : \n\n"
        // changesList.forEach(people -> System.out.println(people.name));  // lambda expression (enable java 1.8 in project structure  - available only since AP 24...
        Log.d("Nom du pokémon", rawPokemonRetrofit!!.name)
    }


    override fun onResponse(call: Call<PokemonRetrofit>, response: Response<PokemonRetrofit>) {
        if (response.isSuccessful) {
            fetchData(response)
        } else {
            println("Erreur : " + response.errorBody()!!)
            Log.d("Erreur", response.errorBody()!!.toString() + "")
        }
    }

    override fun onFailure(call: Call<PokemonRetrofit>, t: Throwable) {
        t.printStackTrace()
    }
}

