package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CraftingSingleton {
    val pokemonGenerated = MutableLiveData<PokemonRetrofit>()
    var isPokemonCrafted = MutableLiveData<Boolean>()

    /**
     * Generate random pokemon based on the types chosen
     */
    fun generateRandomPokemon(firstType:Int, secondType:Int){
        val api = RetrofitSingleton.retrofitInstance
        api.generateRandomPokemon(firstType, secondType,FirebaseSingleton.firebaseUser.uid).enqueue(object: Callback<PokemonRetrofit>{
            override fun onFailure(call: Call<PokemonRetrofit>, t: Throwable) {
                Log.e("Error",t.localizedMessage)
            }

            override fun onResponse(call: Call<PokemonRetrofit>, response: Response<PokemonRetrofit>) {
                if(response.isSuccessful){
                    pokemonGenerated.postValue(response.body())
                    isPokemonCrafted.postValue(true)
                }else{
                    println(response.message())
                }
            }
        })
    }

    init {
        isPokemonCrafted.postValue(false)
    }
}