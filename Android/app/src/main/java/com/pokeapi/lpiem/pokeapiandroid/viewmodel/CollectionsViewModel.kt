package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonFirebase
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.FirebaseSingleton

class CollectionsViewModel {
    fun getCollectionMutableLiveData():MutableLiveData<ArrayList<PokemonFirebase>>{
        return FirebaseSingleton.pokemonCollections
    }

    fun loadData(userId:String){
        FirebaseSingleton.getPokemonCollections(userId)
    }
}