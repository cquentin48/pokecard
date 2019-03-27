package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.RetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.model.adaptermodel.PokedexBasicInfosAdapter
import com.pokeapi.lpiem.pokeapiandroid.model.adaptermodel.SinglePokemonBasicInfo
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonDataRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PokemonRetrofitSingleton {
    var singlePokemonData = MutableLiveData<PokemonDataRetrofit>()

    fun loadSinglePokemonData(pokemonId:Int){
        val instance = RetrofitSingleton.retrofitInstance
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

    fun initBasicInfosData(rawData: PokemonDataRetrofit): PokedexBasicInfosAdapter {
        val returnedData = PokedexBasicInfosAdapter(mutableListOf())
        returnedData.infos.add(0, SinglePokemonBasicInfo("Types",rawData.types[0]))
        returnedData.infos.add(0, SinglePokemonBasicInfo("Taille",rawData.height.toString()))
        returnedData.infos.add(0, SinglePokemonBasicInfo("Poids",rawData.weight.toString()))
        return returnedData
    }

    fun getPokemonName(rawData:PokemonDataRetrofit):String{
        return rawData.name
    }

    fun getPokemonId(rawData:PokemonDataRetrofit):String{
        return rawData.id.toString()
    }

    fun getPokemonSpriteURL(rawData:PokemonDataRetrofit):String{
        return rawData.sprites
    }
}