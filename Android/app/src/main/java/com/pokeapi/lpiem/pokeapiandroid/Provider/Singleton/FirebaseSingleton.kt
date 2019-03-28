package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.view.activity.LogInActivity

object FirebaseSingleton {
    lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseAuthentification: FirebaseAuth

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

    /**
     * Return the image url of the user
     * @param context in wich activity/fragment the function is called
     */
    fun getImageURL(context: Context):String{
        return if(firebaseUser.photoUrl.toString() == "") context.getString(R.string.default_photo_url) else firebaseUser.photoUrl.toString()
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
