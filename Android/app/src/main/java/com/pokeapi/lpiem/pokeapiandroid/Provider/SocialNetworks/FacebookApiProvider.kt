package com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks

import android.os.Bundle

import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.FacebookProfile
import com.pokeapi.lpiem.pokeapiandroid.View.MainActivity

import org.json.JSONArray

class FacebookApiProvider : FacebookApiInterface {
    val callbackManager: CallbackManager

    override val isConnected: Boolean
        get() = if (AccessToken.getCurrentAccessToken() != null) {
            true
        } else {
            false
        }

    override fun logout() {
        LoginManager.getInstance().logOut()
    }

    override fun getData(loginResult: LoginResult, accessToken: AccessToken){}

    init {
        this.callbackManager = CallbackManager.Factory.create()
    }
}
