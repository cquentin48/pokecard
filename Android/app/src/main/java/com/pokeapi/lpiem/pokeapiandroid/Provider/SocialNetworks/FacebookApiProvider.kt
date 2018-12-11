package com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks

import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

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
