package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

class Forms {
    var name: String? = null

    var url: String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Forms

        if (name != other.name) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }
}
