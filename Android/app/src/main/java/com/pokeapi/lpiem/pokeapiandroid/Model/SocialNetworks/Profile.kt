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
}
