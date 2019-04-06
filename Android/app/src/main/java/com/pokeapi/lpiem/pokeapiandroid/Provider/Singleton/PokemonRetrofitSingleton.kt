package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.content.res.Resources
import android.util.Log
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.R
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
        returnedData.infos.add(0, SinglePokemonBasicInfo(if(rawData.types.size>1)"Types" else "Type",initPokemonTypes(rawData.types)))
        returnedData.infos.add(1, SinglePokemonBasicInfo("Taille",(rawData.height.toFloat()/10).toString()+" m"))
        returnedData.infos.add(2, SinglePokemonBasicInfo("Poids",(rawData.weight.toFloat()/10).toString()+" kg"))
        return returnedData
    }

    fun initPokemonTypes(rawData: MutableList<String>):String{
        var returnedString = ""
        rawData.mapIndexed { index, type ->
            returnedString += if(index == 0){
                type
            }else{
                "/$type"
            }
        }
        return returnedString
    }

    fun getPokemonName(rawData:PokemonDataRetrofit):String{
        return rawData.name
    }

    fun getPokemonId(rawData:PokemonDataRetrofit):String{
        return rawData.id.toString()+"/865"
    }

    fun getPokemonSpriteURL(rawData:PokemonDataRetrofit):String{
        return rawData.sprites
    }
}