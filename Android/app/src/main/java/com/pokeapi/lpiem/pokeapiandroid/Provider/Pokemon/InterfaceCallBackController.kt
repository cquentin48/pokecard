package com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit

interface InterfaceCallBackController<T> {
    fun onWorkDone()
    fun showPokemon(p: PokemonRetrofit)
}
