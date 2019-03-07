package com.pokeapi.lpiem.pokeapiandroid.Provider

import android.util.Log
import com.google.gson.GsonBuilder
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonAPI

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

    fun getPokeList(pokedexView: PokedexListView){
        val pokemonAPI = RetrofitSingleton.getInstance()
        val callPokemon = instance!!.getPokemonListData()

        callPokemon.enqueue(object : Callback<PokemonList> {

            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (response.isSuccessful) {
                    val returnedData = response.body()
                    pokedexView.initPokedex(returnedData!!.PokemonList)
                } else {
                    Log.d(pokedexView.context!!.getString(R.string.error_tag), pokedexView.context!!.getString(R.string.fetching_data_error))
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.e(pokedexView.context!!.getString(R.string.error_tag), t.localizedMessage)
                t.printStackTrace()
            }
        })
    }
}
