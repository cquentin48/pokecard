package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import android.content.Context
import com.pokeapi.lpiem.pokeapiandroid.Provider.FirebaseSingleton

class LoginModelView {
    /**
     * Initialisation of firebase authentification
     */
    fun initFirebaseAuthentification(context: Context){
        FirebaseSingleton.initFirebaseAuth(context)
    }

    /**
     * Check if the user is currently signed in
     * @return [true] connected [false] not connected
     */
    fun checkIfUserIsAlreadyConnected():Boolean{
        return FirebaseSingleton.checkUser()
    }

    /**
     * Update the currently logged in user in the base
     */
    fun updateCurrentUser(){
        FirebaseSingleton.setCurrentUser()
    }
}