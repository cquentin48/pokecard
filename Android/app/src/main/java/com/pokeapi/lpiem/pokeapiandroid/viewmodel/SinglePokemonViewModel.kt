package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import com.pokeapi.lpiem.pokeapiandroid.model.adaptermodel.PokedexBasicInfosAdapter
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.PokemonRetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonDataRetrofit

class SinglePokemonViewModel {
    fun loadData(pokemonId: Int){
        PokemonRetrofitSingleton.loadSinglePokemonData(pokemonId)
    }

    fun loadPokemonName(rawData: PokemonDataRetrofit):String{
        return PokemonRetrofitSingleton.getPokemonName(rawData)
    }

    fun initBasicInfosData(rawData: PokemonDataRetrofit):PokedexBasicInfosAdapter{
        return PokemonRetrofitSingleton.initBasicInfosData(rawData)
    }

    fun loadPokemonSpriteURL(rawData: PokemonDataRetrofit):String{
        return PokemonRetrofitSingleton.getPokemonSpriteURL(rawData)
    }

    fun loadPokemonId(rawData: PokemonDataRetrofit):String{
        return PokemonRetrofitSingleton.getPokemonId(rawData)
    }
}