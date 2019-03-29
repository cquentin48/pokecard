package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.TypeList
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.CraftingSingleton
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.TypeListManagment

class CraftingFragmentViewModel {
    fun loadData(){
        TypeListManagment.loadAllTypes()
    }

    fun getTypesData():MutableLiveData<TypeList>{
        return TypeListManagment.typeList
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