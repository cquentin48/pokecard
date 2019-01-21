package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.Achievement
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit

import java.net.URI

class Profile {
    var username: String? = null
    var email: String? = null
    var pokemonRetrofitOwned: List<PokemonRetrofit>? = null
    var achievementsList: List<Achievement>? = null
    var avatarImage: URI? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profile

        if (username != other.username) return false
        if (email != other.email) return false
        if (pokemonRetrofitOwned != other.pokemonRetrofitOwned) return false
        if (achievementsList != other.achievementsList) return false
        if (avatarImage != other.avatarImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (pokemonRetrofitOwned?.hashCode() ?: 0)
        result = 31 * result + (achievementsList?.hashCode() ?: 0)
        result = 31 * result + (avatarImage?.hashCode() ?: 0)
        return result
    }
}
