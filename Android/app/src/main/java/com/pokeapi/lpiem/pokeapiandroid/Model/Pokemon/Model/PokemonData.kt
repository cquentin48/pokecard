package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Types

class PokemonData (pokemonName: String, pokemonSprite: String, pokemonPokedexEntry: String, PokemonTypeList: MutableList<Types>, pokemonSpecies: Species, pokemonId:Int){
    //Attributs
    private var pokemonName:String = pokemonName
    private var pokemonSprite:String= pokemonSprite
    private var pokemonPokedexEntry:String = pokemonPokedexEntry
    private var pokemonTypes:MutableList<Types>? = PokemonTypeList
    private var pokemonSpecies: Species = pokemonSpecies
    private var pokemonId:Int = pokemonId

    //Getters/Setters
    var PokemonName: String
        get() = this.pokemonName
        set(newValue){
            this.pokemonName = newValue
        }
    var PokemonSprite: String
        get() = this.pokemonSprite
        set(newValue){
            this.pokemonSprite = newValue
        }
    var PokemonTypes: MutableList<Types>?
        get() = this.pokemonTypes
        set(newValue){
            this.pokemonTypes = newValue
        }
    var PokemonPokedexEntry: String
        get() = this.pokemonPokedexEntry
        set(newValue){
            this.pokemonPokedexEntry = newValue
        }
    var PokemonSpecies: Species
        get() = this.pokemonSpecies
        set(newValue){
            this.pokemonSpecies = newValue
        }
    var PokemonId: Int
        get() = this.pokemonId
        set(newValue){
            this.pokemonId = newValue
        }


    /**
     * Constructeur par défaut
     */
    constructor():this("A","","A",mutableListOf<Types>(),Species(),0)

    /**
     * Constructeur avec données de retrofit
     */
    constructor(name:String, species:Species, typeList: MutableList<Types>, id:Int):this(name,"","",typeList,species,id)

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

    fun clone(elementType : MutableList<Types>): PokemonData{
        return PokemonData(this.pokemonName,
                this.pokemonSprite,
                this.pokemonPokedexEntry,
                copyList(elementType),
                this.pokemonSpecies,
                this.pokemonId)
    }

    /**
     * MutableList copy function
     * @param elementType rawType element
     * @return MutableList<Types> returned list
     */
    private fun copyList(elementType : MutableList<Types>):MutableList<Types>{
        var newTypeList : MutableList<Types> = mutableListOf()
        while(elementType.iterator().hasNext()){
            newTypeList.add(elementType.iterator().next())
        }

        return newTypeList
    }

}