package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.FacebookApiProvider
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.GoogleApiProvider
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.TwitterApiProvider

class AppProviderSingleton private constructor() {
    var facebookApiProvider: FacebookApiProvider? = null
    var googleApiProvider: GoogleApiProvider? = null
    var twitterApiProvider: TwitterApiProvider? = null

    init {
        facebookApiProvider = FacebookApiProvider()
    }

    companion object {
        val FACEBOOK = 1
        val TWITTER = 2
        val GOOGLE = 3
        val POKEAPI = 4

        private var instance: AppProviderSingleton? = null

        fun getInstance(): AppProviderSingleton {
            var instanceProvider: AppProviderSingleton = AppProviderSingleton()
            if (instance == null) {
                instance = instanceProvider
            }
            return instanceProvider
        }
    }
}
