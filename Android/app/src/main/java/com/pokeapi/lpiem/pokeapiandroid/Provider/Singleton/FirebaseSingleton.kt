package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.ErrorMessageReturn
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonCollectionFirebase
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonFirebase
import com.pokeapi.lpiem.pokeapiandroid.view.activity.LogInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object FirebaseSingleton {
    lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseAuthentification: FirebaseAuth
    val pokemonCollections = MutableLiveData<PokemonCollectionFirebase>()
    /**
     * initialisation of firebase authentification service
     * @param context which activity will init firebase authentification
     */
    fun initFirebaseAuth(context:Context){
        firebaseAuthentification = FirebaseAuth.getInstance(FirebaseApp.initializeApp(context)!!)
    }

    /**
     * Logging out function
     * @param context which activity to log out from
     */
    fun loggingOut(context: Context){
        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener {
                    context.startActivity(Intent(context, LogInActivity::class.java))
                }
    }

    fun initUser(){

    }

    /**
     * Load the collections of the pokemon
     */
    fun getPokemonCollections(userId:String){
        val api = RetrofitSingleton.retrofitInstance
        api.getPokemonCollection(userId).enqueue(
                object: Callback<PokemonCollectionFirebase>{
                    override fun onFailure(call: Call<PokemonCollectionFirebase>, t: Throwable) {
                        Log.e("Error",t.message)
                    }

                    override fun onResponse(call: Call<PokemonCollectionFirebase>, response: Response<PokemonCollectionFirebase>) {
                        if(response.isSuccessful){
                            pokemonCollections.postValue(response.body())
                        }
                    }
                }
        )
    }

    /**
     * Return the image url of the user
     * @param context in which activity/fragment the function is called
     */
    fun getImageURL(context: Context):String{
        return if(firebaseUser.photoUrl == null || firebaseUser.photoUrl.toString() == "") context.getString(R.string.default_photo_url) else firebaseUser.photoUrl.toString()
    }

    /**
     * Return the username of the user
     */
    fun getUsername():String{
        return if(firebaseUser.displayName == "") firebaseUser.email!! else firebaseUser.displayName!!
    }

    /**
     * Launch mainActivity after successfull login
     */
    fun checkUser() : Boolean{
        return FirebaseAuth.getInstance().currentUser!=null
    }

    /**
     * Update the current logged in user
     */
    fun setCurrentUser(){
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
    }
}
