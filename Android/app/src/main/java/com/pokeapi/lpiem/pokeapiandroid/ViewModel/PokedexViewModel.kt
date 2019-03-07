package com.pokeapi.lpiem.pokeapiandroid.ViewModel

import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.Provider.PokemonRetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.View.Fragment.PokedexListView

class PokedexViewModel {
    fun loadPokedex(pokedexView: PokedexListView){
        PokemonRetrofitSingleton.getPokeList(pokedexView)
    }
}