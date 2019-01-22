package com.pokeapi.lpiem.pokeapiandroid.View

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SinglePokemonListRetrofit {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("sprite")
    @Expose
    var sprite: String? = null
}