package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import android.content.Context
import com.pokeapi.lpiem.pokeapiandroid.Provider.PokemonRetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.provider.PokedexSingletonDisplayManagment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class PokedexViewModel {
    fun loadPokedex(context: Context){
        PokemonRetrofitSingleton.getPokeList(context)
    }

    fun initPokedexAdapter(rawList: MutableList<PokemonRetrofit>, context: Context, groupAdapter:GroupAdapter<ViewHolder>){
        return PokedexSingletonDisplayManagment.initPokedexData(rawList,context, groupAdapter)
    }
}