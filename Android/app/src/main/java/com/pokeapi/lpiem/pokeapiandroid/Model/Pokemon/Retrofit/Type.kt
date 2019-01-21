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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Type

        if (typeName != other.typeName) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = typeName?.hashCode() ?: 0
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }

    /**
     * Cloning function for the {Type} class
     */
    fun clone(): Type{
        var newType: Type?= Type()

        newType!!.typeName = this.typeName
        newType!!.url = this.url

        return newType
    }
}
