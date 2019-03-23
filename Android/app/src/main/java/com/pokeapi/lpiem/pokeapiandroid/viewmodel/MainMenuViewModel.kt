package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.pokeapi.lpiem.pokeapiandroid.Provider.FirebaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.R

class MainMenuViewModel {
    private lateinit var backgroundDrawerLayout: DrawerLayout

    //Getters/Setters
    var BackgroundDrawerLayout:DrawerLayout
    get() = backgroundDrawerLayout
    set(newValue){
        backgroundDrawerLayout = newValue
    }

    fun loggingOut(context: Context){
        FirebaseSingleton.loggingOut(context)
    }

    /**
     * Display a toast about a menu not been implemented
     */
    fun displayToastNotYetImplemented(context:Context) {
        Toast.makeText(context, context.getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()
    }

    fun getImageURL(context: Context):String{
        return FirebaseSingleton.getImageURL(context)
    }

    fun getUsername():String{
        return FirebaseSingleton.getUsername()
    }
}