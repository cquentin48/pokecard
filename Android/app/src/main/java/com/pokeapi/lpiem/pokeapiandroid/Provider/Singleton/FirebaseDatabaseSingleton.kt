package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

    fun initUser(context:Context){
        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(FirebaseSingleton.firebaseUser.uid).addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("Error",p0.details)
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.value == null){
                    val data = hashMapOf<String,String>()
                    data["avatarImage"] = FirebaseSingleton.getImageURL(context)
                    data["distance"] = 0.toString()
                    data["username"] = FirebaseSingleton.firebaseUser.displayName!!
                    database.child("users").child(FirebaseSingleton.firebaseUser.uid).setValue(data)
                    database.child("users").child(FirebaseSingleton.firebaseUser.uid).child("pokemonCollection").child(1.toString()).child("creationDate").setValue("dsd")
                    database.child("users").child(FirebaseSingleton.firebaseUser.uid).child("pokemonCollection").child(1.toString()).child("nickname").setValue("dsdsd")
                }
            }

        })
    }
}