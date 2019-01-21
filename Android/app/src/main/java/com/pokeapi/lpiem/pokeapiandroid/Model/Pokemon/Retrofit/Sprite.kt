package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sprite {
    @SerializedName("front_default")
    @Expose
    var backDefault: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sprite

        if (backDefault != other.backDefault) return false

        return true
    }

    override fun hashCode(): Int {
        return backDefault.hashCode()
    }
}