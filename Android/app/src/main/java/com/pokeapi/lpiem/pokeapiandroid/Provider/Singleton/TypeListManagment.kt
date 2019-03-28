package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.TypeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TypeListManagment {
    val typeList = MutableLiveData<TypeList>()
    var types = mutableListOf<String>()

    fun loadAllTypes(){
        val api = RetrofitSingleton.retrofitInstance
        api.getAllTypes().enqueue(object: Callback<TypeList>{
            override fun onFailure(call: Call<TypeList>, t: Throwable) {
                Log.e("Error",t.localizedMessage)
            }

            override fun onResponse(call: Call<TypeList>, response: Response<TypeList>) {
                if(response.isSuccessful){
                    typeList.postValue(response.body())
                }
            }

        })
    }

    fun updateTypesChosen(id:Int, value:String){
        types[id] = value
    }
}