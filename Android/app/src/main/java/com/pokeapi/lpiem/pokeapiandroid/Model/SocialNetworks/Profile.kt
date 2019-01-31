package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.Achievement
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit

import java.net.URI

open class Profile(_username: String? = "Pseudonyme",
                   _email:String? = "email",
                   _pokemonRetrofitOwnedList:List<PokemonRetrofit>? = arrayListOf(),
                   _achievementsList: List<Achievement>? = arrayListOf(),
                   _friendsList: List<Profile>? = arrayListOf(),
                   _avatarImage: String = "NoImage") {

    private var username: String? = _username
    private var email: String? = _email
    private var pokemonRetrofitOwned: List<PokemonRetrofit>? = _pokemonRetrofitOwnedList
    private var achievementsList: List<Achievement>? = _achievementsList
    private var friendsList: List<Profile>? = _friendsList
    private var avatarImage: String? = _avatarImage

    var Username:String
        get() = this.username!!
        set(value){
            username = value
        }
    var Email:String
        get() = this.email!!
        set(value){
            email = value
        }
    var PokemonRetrofitOwned: List<PokemonRetrofit>?
        get() = this.pokemonRetrofitOwned!!
        set(value){
        pokemonRetrofitOwned = value
        }
    var AchievementsList: List<Achievement>?
        get() = this.achievementsList!!
        set(value){
            achievementsList = value
        }
    var FriendsList: List<Profile>?
        get() = this.friendsList!!
        set(value){
            friendsList = value
        }
    var AvatarImage:String
        get() = this.avatarImage!!
        set(value){
            avatarImage = value
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profile

        if (username != other.username) return false
        if (email != other.email) return false
        if (pokemonRetrofitOwned != other.pokemonRetrofitOwned) return false
        if (achievementsList != other.achievementsList) return false
        if (friendsList != other.friendsList) return false
        if (avatarImage != other.avatarImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (pokemonRetrofitOwned?.hashCode() ?: 0)
        result = 31 * result + (achievementsList?.hashCode() ?: 0)
        result = 31 * result + (friendsList?.hashCode() ?: 0)
        result = 31 * result + (avatarImage?.hashCode() ?: 0)
        return result
    }


}
