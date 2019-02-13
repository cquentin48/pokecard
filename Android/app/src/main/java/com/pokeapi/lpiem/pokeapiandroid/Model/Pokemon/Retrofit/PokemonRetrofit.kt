package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonRetrofit {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("sprite")
    var sprite: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonRetrofit

        if (name != other.name) return false
        if (sprite != other.sprite) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (sprite?.hashCode() ?: 0)
        return result
    }

}
