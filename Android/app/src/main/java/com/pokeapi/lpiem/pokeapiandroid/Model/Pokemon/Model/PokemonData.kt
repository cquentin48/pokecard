package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import java.lang.reflect.Type

class PokemonData (){
    var pokemonName:String=""
    var pokemonSprite:String=""
    var pokemonPokedexEntry:String=""
    var pokemonTypes:List<Type>?=null
    var pokemonSpecies: Species?=null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonData

        if (pokemonName != other.pokemonName) return false
        if (pokemonSprite != other.pokemonSprite) return false
        if (pokemonPokedexEntry != other.pokemonPokedexEntry) return false
        if (pokemonTypes != other.pokemonTypes) return false
        if (pokemonSpecies != other.pokemonSpecies) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pokemonName.hashCode()
        result = 31 * result + pokemonSprite.hashCode()
        result = 31 * result + pokemonPokedexEntry.hashCode()
        result = 31 * result + (pokemonTypes?.hashCode() ?: 0)
        result = 31 * result + (pokemonSpecies?.hashCode() ?: 0)
        return result
    }

    fun copy(p: PokemonData){

    }
}