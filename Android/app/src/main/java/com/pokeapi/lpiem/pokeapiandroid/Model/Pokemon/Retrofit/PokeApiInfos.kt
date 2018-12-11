package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokeApiInfos {
    @SerializedName("count")
    @Expose
    private var pokemonCount: Int = 0;

    @SerializedName("results")
    @Expose
    private var pokemonPageViewList: MutableList<PokemonPokeApiPageUrl> = mutableListOf();

    var PokeCount:Int
        get() = this.pokemonCount
        set(value) {this.pokemonCount = value}

    var PokemonPageViewList:MutableList<PokemonPokeApiPageUrl>
        get() = this.pokemonPageViewList
        set(value) {this.pokemonPageViewList = value}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokeApiInfos

        if (pokemonCount != other.pokemonCount) return false
        if (pokemonPageViewList != other.pokemonPageViewList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pokemonCount
        result = 31 * result + pokemonPageViewList.hashCode()
        return result
    }

    override fun toString(): String {
        return "PokeApiInfos(pokemonCount=$pokemonCount, pokemonPageViewList=$pokemonPageViewList)"
    }
}