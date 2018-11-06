package com.pokeapi.lpiem.pokeapiandroid.Provider;

import com.facebook.AccessToken;
import com.facebook.login.LoginResult;

public interface FacebookApiInterface {

    /**
     * Déconnexion d'un réseau social
     */
    void logout();

    /**
     * Récupération des données
     * @return l'objet du profil
     */
    Object getData(LoginResult loginResult, AccessToken accessToken);

    /**
     * Vérifie si l'utilisateur est connecté
     * @return 1=> Connecté 0=> Pas connecté
     */
    boolean isConnected();
}
