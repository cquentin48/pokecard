package com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks

import com.facebook.AccessToken
import com.facebook.login.LoginResult

interface FacebookApiInterface {

    /**
     * Vérifie si l'utilisateur est connecté
     * @return 1=> Connecté 0=> Pas connecté
     */
    val isConnected: Boolean

    /**
     * Déconnexion d'un réseau social
     */
    fun logout()

    /**
     * Récupération des données
     * @return l'objet du profil
     */
    fun getData(loginResult: LoginResult, accessToken: AccessToken): Any
}
