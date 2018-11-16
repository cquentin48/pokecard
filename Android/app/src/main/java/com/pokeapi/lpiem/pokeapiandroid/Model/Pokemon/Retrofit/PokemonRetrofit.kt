package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonRetrofit {

    @SerializedName("name")
    @Expose
    var name: String? = null

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
}
