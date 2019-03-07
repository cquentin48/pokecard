package com.pokeapi.lpiem.pokeapiandroid.ViewModel

import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.View.Fragment.PokedexListView

class PokedexViewModel {
    fun loadPokedex(pokedexView: PokedexListView){
        AppProviderSingleton.getPokeList(pokedexView)
    }
}