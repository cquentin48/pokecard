package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.SerializedName

class Species {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
}
