package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks

import java.net.URI

class GoogleProfile(//Pseudo
        var username: String?, //E-mail
        var email: String?, //Prénom
        var firstName: String?, //Nom de famille
        var familyName: String?, //Surnom
        var givenName: String?, //Identifiant
        var id: String?, //URI du compte
        var accountURI: URI?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GoogleProfile

        if (username != other.username) return false
        if (email != other.email) return false
        if (firstName != other.firstName) return false
        if (familyName != other.familyName) return false
        if (givenName != other.givenName) return false
        if (id != other.id) return false
        if (accountURI != other.accountURI) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (familyName?.hashCode() ?: 0)
        result = 31 * result + (givenName?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (accountURI?.hashCode() ?: 0)
        return result
    }
}
