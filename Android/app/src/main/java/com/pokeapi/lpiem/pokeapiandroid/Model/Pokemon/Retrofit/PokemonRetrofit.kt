package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonRetrofit {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("sprite")
    var sprite: String? = null

    @SerializedName("types")
    @Expose
    var typeList: List<Types>? = null

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("species")
    @Expose
    var species: Species? = null

    @SerializedName("forms")
    var formsList: List<Forms>? = null

    fun getTypeListIndex(i: Int): Types {
        return typeList!![i]
    }

    fun getFormListIndexI(i: Int): Forms {
        return formsList!![i]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonRetrofit

        if (name != other.name) return false
        if (sprite != other.sprite) return false
        if (typeList != other.typeList) return false
        if (id != other.id) return false
        if (species != other.species) return false
        if (formsList != other.formsList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (sprite?.hashCode() ?: 0)
        result = 31 * result + (typeList?.hashCode() ?: 0)
        result = 31 * result + id
        result = 31 * result + (species?.hashCode() ?: 0)
        result = 31 * result + (formsList?.hashCode() ?: 0)
        return result
    }

}
