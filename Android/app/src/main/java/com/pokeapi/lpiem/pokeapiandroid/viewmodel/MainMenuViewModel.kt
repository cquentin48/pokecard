package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import android.content.Context
import android.widget.Toast
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.FirebaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.R

class MainMenuViewModel {

    /**
     * Sign out function
     */
    fun loggingOut(context: Context){
        FirebaseSingleton.loggingOut(context)
    }

    /**
     * Display a toast about a menu not been implemented
     */
    fun displayToastNotYetImplemented(context:Context) {
        Toast.makeText(context, context.getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()
    }

    /**
     * Return the image url of the user
     */
    fun getImageURL(context: Context):String{
        return FirebaseSingleton.getImageURL(context)
    }

    /**
     * Return the username of the user
     */
    fun getUsername():String{
        return FirebaseSingleton.getUsername()
    }
}