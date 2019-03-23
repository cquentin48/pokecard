package com.pokeapi.lpiem.pokeapiandroid.ViewModel

import android.content.Context
import com.pokeapi.lpiem.pokeapiandroid.Provider.FirebaseSingleton

class LoginModelView {
    /**
     * Initialisation of firebase authentification
     */
    fun initFirebaseAuthentification(context: Context){
        FirebaseSingleton.initFirebaseAuth(context)
    }
}