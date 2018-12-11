package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonPokeApiPageUrl(pokeName:String, pokePageView:String) {
    @SerializedName("name")
    @Expose
    private var pokemonName: String = pokeName;

    @SerializedName("url")
    @Expose
    private var pokemonPageView: String = pokePageView;

    var PokemonName:String
        get() = this.pokemonName
        set(value) {this.pokemonName = value}

    var PokemonPageView:String
        get() = this.pokemonPageView
        set(value) {this.pokemonPageView = value}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonPokeApiPageUrl

        if (pokemonName != other.pokemonName) return false
        if (pokemonPageView != other.pokemonPageView) return false

        return true
    }
    /**
     * Constructeur par défaut
     */
    constructor():this("","")

    override fun hashCode(): Int {
        var result = pokemonName.hashCode()
        result = 31 * result + pokemonPageView.hashCode()
        return result
    }

    override fun toString(): String {
        return "PokemonPokeApiPageUrl(pokemonName='$pokemonName', pokemonPageView='$pokemonPageView')"
    }


}