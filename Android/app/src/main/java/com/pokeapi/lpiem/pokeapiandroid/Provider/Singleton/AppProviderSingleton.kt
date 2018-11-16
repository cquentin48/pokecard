package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import android.util.Log
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.Profile
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.FacebookApiProvider
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.GoogleApiProvider
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.TwitterApiProvider
import com.pokeapi.lpiem.pokeapiandroid.View.MainAppActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppProviderSingleton private constructor() {
    var facebookApiProvider: FacebookApiProvider? = null
    var googleApiProvider: GoogleApiProvider? = null
    var twitterApiProvider: TwitterApiProvider? = null
    var userProfile: Profile = Profile()

    var pokemonList:MutableList<PokemonData> ?= mutableListOf<PokemonData>()

    init {
        facebookApiProvider = FacebookApiProvider()
    }    val listPokemon:MutableList<PokemonData> = mutableListOf<PokemonData>()


    fun getPokeInfos(mainAppActivity: MainAppActivity, pokeIndex:Int){
        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonById(pokeIndex)

        callPokemon.enqueue(object : Callback<PokemonRetrofit> {

            override fun onResponse(call: Call<PokemonRetrofit>, response: Response<PokemonRetrofit>) {
                if (response.isSuccessful()) {
                    var pokemonDataReturned = response.body()
                    var pokemon = PokemonRetrofit()
                    pokemon.name = pokemonDataReturned!!.name
                    pokemon.id = pokeIndex
                    pokemon.species = pokemonDataReturned!!.species
                    pokemon.typeList = pokemonDataReturned!!.typeList

                    mainAppActivity.showPokemon(pokemon)
                } else {
                    Log.d("Erreur", "Erreur de connexion")
                }
            }

            override fun onFailure(call: Call<PokemonRetrofit>, t: Throwable) {
                Log.e("Erreur", t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    fun getPokedexEntry(mainAppActivity: MainAppActivity, pokeIndex:Int){
        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonSpecies(pokeIndex)

        callPokemon.enqueue(object : Callback<Species> {

            override fun onResponse(call: Call<Species>, response: Response<Species>) {
                if (response.isSuccessful()) {
                    var pokemonDataReturned = response.body()
                    var pokemon = PokemonRetrofit()
                    pokemon.name = pokemonDataReturned!!.name
                    pokemon.id = pokeIndex



                    mainAppActivity.showPokemon(pokemon)
                } else {
                    Log.d("Erreur", "Erreur de connexion")
                }
            }

            override fun onFailure(call: Call<Species>, t: Throwable) {
                Log.e("Erreur", t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    fun getPokemonSpecies(mainAppActivity: MainAppActivity, pokeIndex:Int){
        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonSpecies(pokeIndex)

        callPokemon.enqueue(object : Callback<Species> {

            override fun onResponse(call: Call<Species>, response: Response<Species>) {
                if (response.isSuccessful()) {
                    var returnedData = response.body()!!

                    mainAppActivity.updatePokemonData(pokeIndex,returnedData)
                } else {
                    Log.d("Erreur", "Erreur de connexion")
                }
            }

            override fun onFailure(call: Call<Species>, t: Throwable) {
                Log.e("Erreur", t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    companion object {
        val FACEBOOK = 1
        val TWITTER = 2
        val GOOGLE = 3
        val POKEAPI = 4

        private var instance: AppProviderSingleton? = null

        fun getInstance(): AppProviderSingleton {
            var instanceProvider: AppProviderSingleton = AppProviderSingleton()
            if (instance == null) {
                instance = instanceProvider
            }
            return instanceProvider
        }
    }
}
