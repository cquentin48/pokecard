package com.pokeapi.lpiem.pokeapiandroid.Provider

import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pokeapi.lpiem.pokeapiandroid.View.Activity.LogInActivity

object FirebaseSingleton {
    private var instance:FirebaseSingleton
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseAuthentification: FirebaseAuth
    var FirebaseAuthentification:FirebaseAuth
    get() = this.firebaseAuthentification
    set(newValue){
        this.firebaseAuthentification
    }
    init {
        instance = this@FirebaseSingleton
    }

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
}
