package com.pokeapi.lpiem.pokeapiandroid.Provider

import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonList

object PokemonRetrofitSingleton {
    var pokemonList = MutableLiveData<PokemonList>()
}