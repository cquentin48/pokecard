package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.TypeList
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.CraftingSingleton
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.FirebaseDatabaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.TypeListManagment

class CraftingFragmentViewModel {
    fun loadData(){
        TypeListManagment.loadAllTypes()
    }

    fun getTypesData():MutableLiveData<TypeList>{
        return TypeListManagment.typeList
    }

    fun isPokemonCrafted():MutableLiveData<Boolean>{
        return CraftingSingleton.isPokemonCrafted
    }

    fun setPokemonId(pokemonGenerated: PokemonRetrofit){
        CraftingSingleton.setPokemonId(pokemonGenerated)
    }

    fun addPokemonToCollection(nickname: String){
        FirebaseDatabaseSingleton.addPokemonToCollection(nickname)
    }

    fun getPokemonGeneratedId():Int{
        return CraftingSingleton.pokemonId
    }

    fun generateRandomPokemon(firstType:Int, secondType:Int){
        CraftingSingleton.generateRandomPokemon(firstType, secondType)
    }

    fun getGeneratedData():MutableLiveData<PokemonRetrofit>{
        return CraftingSingleton.pokemonGenerated
    }

    fun loadPokemonName(pokemonGenerated:PokemonRetrofit):String{
        return pokemonGenerated.name
    }

    fun loadPokemonSprite(pokemonGenerated:PokemonRetrofit):String{
        return pokemonGenerated.sprite
    }
}