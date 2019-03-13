package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks

import java.text.SimpleDateFormat
import java.util.*


class Profile(username: String? = "Pseudonyme",
              email:String? = "email",
              avatarImage: String = "NoImage",
              registrationDate:String = SimpleDateFormat("dd/MM/yyyy").format(Date()),
              lastUserConnection:String = SimpleDateFormat("dd/MM/yyyy").format(Date()),
              isLoggedIn:Boolean = true,
              pokemonRetrofitOwnedList:HashMap<String,Int>? = hashMapOf(1.toString() to 0),
              achievementsList: List<Boolean>? = arrayListOf(false),
              distanceWalked:Float = 0.0f,
              friendsList: List<Boolean>? = arrayListOf(false)) {

    var username = username
    var email = email
    var distance = distanceWalked
    var lastUserConnection = lastUserConnection
    var isLoggedIn = isLoggedIn
    var pokemonCollection = pokemonRetrofitOwnedList
    var achievementsList = achievementsList
    var registrationDate = registrationDate
    var friendsList = friendsList
    var avatarImage = avatarImage
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profile

        if (username != other.username) return false
        if (email != other.email) return false
        if (distance != other.distance) return false
        if (lastUserConnection != other.lastUserConnection) return false
        if (isLoggedIn != other.isLoggedIn) return false
        if (pokemonCollection != other.pokemonCollection) return false
        if (achievementsList != other.achievementsList) return false
        if (registrationDate != other.registrationDate) return false
        if (friendsList != other.friendsList) return false
        if (avatarImage != other.avatarImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + distance.hashCode()
        result = 31 * result + lastUserConnection.hashCode()
        result = 31 * result + isLoggedIn.hashCode()
        result = 31 * result + (pokemonCollection?.hashCode() ?: 0)
        result = 31 * result + (achievementsList?.hashCode() ?: 0)
        result = 31 * result + registrationDate.hashCode()
        result = 31 * result + (friendsList?.hashCode() ?: 0)
        result = 31 * result + avatarImage.hashCode()
        return result
    }

    override fun toString(): String {
        return "Profile(username=$username, email=$email, distance=$distance, lastUserConnection=$lastUserConnection, isLoggedIn=$isLoggedIn, pokemonCollection=$pokemonCollection, achievementsList=$achievementsList, registrationDate=$registrationDate, friendsList=$friendsList, avatarImage='$avatarImage')"
    }
}
