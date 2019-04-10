package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonCollectionFirebase
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.FirebaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.PokemonRetrofitSingleton

class CollectionsViewModel {
    fun getCollectionMutableLiveData():MutableLiveData<PokemonCollectionFirebase>{
        return FirebaseSingleton.pokemonCollections
    }

    fun loadData(userId:String){
        FirebaseSingleton.getPokemonCollections(userId)
    }

    fun setSelectedPokemonId(id:Int){
        PokemonRetrofitSingleton.setSelectedPokemonCollectionId(id)
    }
}