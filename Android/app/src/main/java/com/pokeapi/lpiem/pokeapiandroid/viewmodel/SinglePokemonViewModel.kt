package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import com.pokeapi.lpiem.pokeapiandroid.Provider.PokemonRetrofitSingleton

class SinglePokemonViewModel {
    fun loadData(pokemonId: Int){
        PokemonRetrofitSingleton.loadSinglePokemonData(pokemonId)
    }
}