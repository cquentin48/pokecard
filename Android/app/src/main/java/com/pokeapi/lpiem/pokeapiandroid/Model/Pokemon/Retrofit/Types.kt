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
}
