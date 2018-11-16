package com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species

interface InterfaceCallBackController<T> {
    fun updatePokemonData(i: Int, s:Species)
    fun showPokemon(p: PokemonRetrofit)
    fun updatePokedexEntry(i: Int, s:String)
}
