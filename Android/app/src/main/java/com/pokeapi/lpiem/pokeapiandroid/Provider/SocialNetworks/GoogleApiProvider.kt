package com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.pokeapi.lpiem.pokeapiandroid.View.MainActivity

class GoogleApiProvider : GoogleApiInterface {
    override fun logIn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var googleSignInClient: GoogleSignInClient? = null

    override fun logOut() {

    }

    fun initGoogleApi() {
        googleSignInClient = MainActivity.initGoogleSignInApi()
    }
}
