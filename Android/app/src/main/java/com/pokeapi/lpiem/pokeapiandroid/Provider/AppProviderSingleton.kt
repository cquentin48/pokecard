package com.pokeapi.lpiem.pokeapiandroid.Provider

import android.content.Context
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
    private var firebaseSingleton: FirebaseSingleton

    init {
        firebaseSingleton = FirebaseSingleton
    }

    var User:FirebaseUser
        get() = firebaseUser
        set(newValue){
            firebaseUser = newValue
        }

    fun getPokeList(pokedexView: PokedexListView){
        RetrofitSingleton.getPokeList(pokedexView)
    }

    fun initFirebaseAuth(context: Context){
        firebaseSingleton = FirebaseSingleton
        firebaseSingleton.initFirebaseAuth(context)
    }
}
