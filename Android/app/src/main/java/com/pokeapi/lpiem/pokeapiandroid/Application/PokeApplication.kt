package com.pokeapi.lpiem.pokeapiandroid.Application

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.twitter.sdk.android.core.Twitter

class PokeApplication : Application() {
    private var singleton: AppProviderSingleton? = null
    var pokemonList:List<PokemonData>?= null

    override fun onCreate() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        Twitter.initialize(this)
        singleton = AppProviderSingleton
        super.onCreate()
    }
}
