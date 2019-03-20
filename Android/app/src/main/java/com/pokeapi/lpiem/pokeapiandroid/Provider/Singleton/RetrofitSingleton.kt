package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel.ProfileFragmentAdapterModel
import com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel.ProfileSubFragmentRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonAPI
import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    private var instance: PokemonAPI? = null
    val POKEMONBASEURL = "https://pokeapi.co/"
    val OWNAPIBASEURL = "http://walkemon.herokuapp.com/"
    var infoList = MutableLiveData<ProfileSubFragmentRetrofit>()

    fun getInstance(): PokemonAPI? {
        if (instance == null) {
            instance = buildInstance()
        }
        return instance
    }

    private fun buildInstance(): PokemonAPI {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(OWNAPIBASEURL).addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(PokemonAPI::class.java)
    }

    fun getUserList(){
        val returnedData = RetrofitSingleton.getInstance()!!.getUserFriendsList(AppProviderSingleton.getInstance().User.uid)
        returnedData.enqueue(
                object : retrofit2.Callback<ProfileSubFragmentRetrofit>{
                    override fun onFailure(call: Call<ProfileSubFragmentRetrofit>, t: Throwable) {
                        Log.e("Error",t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ProfileSubFragmentRetrofit>, response: Response<ProfileSubFragmentRetrofit>) {
                        val data = response.body()
                        if(response.isSuccessful){
                            RetrofitSingleton.infoList.postValue(data)
                        }
                    }
                }
        )
    }
}
