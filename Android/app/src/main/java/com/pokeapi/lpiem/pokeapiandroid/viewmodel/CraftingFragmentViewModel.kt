package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.TypeList
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.CraftingSingleton
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.FirebaseDatabaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.TypeListManagment

class CraftingFragmentViewModel {

    /**
     * Import all types from the walkemon API
     */
    fun loadData(){
        TypeListManagment.loadAllTypes()
    }

    /**
     * Get all of the pokemon types in the mutable live data
     */
    fun getTypesData():MutableLiveData<TypeList>{
        return TypeListManagment.typeList
    }


    /**
     * Check if the pokemon had been crafted
     */
    fun isPokemonCrafted():MutableLiveData<Boolean>{
        return CraftingSingleton.isPokemonCrafted
    }


    /**
     * Add the pokemon to the firebase collection of the user
     */
    fun addPokemonToCollection(nickname: String, pokemonId: Int){
        FirebaseDatabaseSingleton.addPokemonToCollection(nickname, pokemonId)
    }

    /**
     * Generate a random pokemon based on its type(s)
     */
    fun generateRandomPokemon(firstType:Int, secondType:Int){
        CraftingSingleton.generateRandomPokemon(firstType, secondType)
    }


    /**
     * Return the mutable live data of the generated pokemon
     */
    fun getGeneratedData():MutableLiveData<PokemonRetrofit>{
        return CraftingSingleton.pokemonGenerated
    }


    /**
     * Load the name of the randomly generated pokemon
     */
    fun loadPokemonName(pokemonGenerated:PokemonRetrofit):String{
        return pokemonGenerated.name
    }


    /**
     * Load the sprite of the randomly generated pokemon
     */
    fun loadPokemonSprite(pokemonGenerated:PokemonRetrofit):String{
        return pokemonGenerated.sprite
    }
}