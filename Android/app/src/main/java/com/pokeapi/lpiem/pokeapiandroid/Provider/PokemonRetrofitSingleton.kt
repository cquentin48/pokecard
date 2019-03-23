package com.pokeapi.lpiem.pokeapiandroid.Provider

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonList
import com.pokeapi.lpiem.pokeapiandroid.provider.RetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.view.fragment.PokedexListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PokemonRetrofitSingleton {
    var pokemonList = MutableLiveData<PokemonList>()

   /**
     * Fetch pokemonList from api
     */
    fun getPokeList(context: Context){
        val callPokemon = RetrofitSingleton.buildInstance().getPokemonListData()

        callPokemon.enqueue(object : Callback<PokemonList> {

            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (response.isSuccessful) {
                    val returnedData = response.body()
                    pokemonList.postValue(returnedData)
                } else {
                    Log.d(context.getString(R.string.error_tag), context.getString(R.string.fetching_data_error))
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.e(context.getString(R.string.error_tag), t.localizedMessage)
                t.printStackTrace()
            }
        })
    }
}