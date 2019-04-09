package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.pokeapi.lpiem.pokeapiandroid.model.firebase.PokemonCollection
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.ErrorMessageReturn
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.ReturnMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

object FirebaseDatabaseSingleton {

    /**
     * Add pokemon to collection list in firebase
     * @param nickname nickname chosen by the user
     */
    fun addPokemonToCollection(nickname: String, pokemonId:Int){
        CraftingSingleton.isPokemonCrafted.postValue(false)
        RetrofitSingleton.retrofitInstance.addPokemonToFirebase(FirebaseSingleton.firebaseUser.uid,
                                                                pokemonId,
                                                                nickname,
                                                                SimpleDateFormat("dd-MM-yyyy HH_mm_ss").format(Date())).enqueue(
                object:Callback<ErrorMessageReturn>{
                    override fun onFailure(call: Call<ErrorMessageReturn>, t: Throwable) {
                        Log.e("Error",t.localizedMessage)
                    }

                    override fun onResponse(call: Call<ErrorMessageReturn>, response: Response<ErrorMessageReturn>) {
                        if(!response.isSuccessful){
                            Log.e("Error",response.message())
                        }
                    }

                }
        )
    }
}