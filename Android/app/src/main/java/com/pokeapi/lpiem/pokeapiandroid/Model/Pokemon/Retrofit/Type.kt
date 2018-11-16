package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Type {
    @SerializedName("name")
    @Expose
    var typeName: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}
