package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Types {
    @SerializedName("slot")
    @Expose
    var slot: Int = 0

    @SerializedName("type")
    @Expose
    var type: Type? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Types

        if (slot != other.slot) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = slot
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }
}
