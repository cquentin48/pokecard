package com.pokeapi.lpiem.pokeapiandroid.Provider

import android.util.Log
import com.pokeapi.lpiem.pokeapiandroid.View.Fragment.PokedexListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.firebase.auth.FirebaseUser
import com.pokeapi.lpiem.pokeapiandroid.Model.Retrofit.Pokemons.PokemonList
import com.pokeapi.lpiem.pokeapiandroid.R


object AppProviderSingleton {
    private lateinit var firebaseUser:FirebaseUser
    var User:FirebaseUser
        get() = firebaseUser
        set(newValue){
            firebaseUser = newValue
        }

    /**
     * Fetch data from webservice
     * @param pokedexView fragment support for the data to be loaded
     */
    fun getPokeList(pokedexView: PokedexListView){
        RetrofitSingleton.buildInstance()!!.getPokemonListData().enqueue(object : Callback<PokemonList> {

            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (response.isSuccessful) {
                    val returnedData = response.body()
                    pokedexView.initPokedex(returnedData!!.pokemonList)
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
