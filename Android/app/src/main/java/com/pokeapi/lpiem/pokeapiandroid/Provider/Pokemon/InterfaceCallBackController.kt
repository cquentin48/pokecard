package com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species

interface InterfaceCallBackController<T> {
    fun addPokemonSpecies(i: Int, s:Species)
    fun addPokemonToList(p: PokemonRetrofit)
    fun addPokedexEntry(i: Int, s:String)
}
