package com.pokeapi.lpiem.pokeapiandroid.provider

import android.content.Context
import androidx.lifecycle.Observer
import com.pokeapi.lpiem.pokeapiandroid.Provider.PokemonRetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.SinglePokemonRetrofitPokedex
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.AdapterHeader
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.PokedexItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

object PokedexSingletonDisplayManagment {
    /**
     * Create list for the adapter
     * @param rawData RawData
     * @param groupAdapter group adapter for the list
     */
    private fun createList(rawData: MutableList<String>, groupAdapter: GroupAdapter<ViewHolder>, context: Context){
        ExpandableGroup(AdapterHeader(""), true).apply {
            add(Section())
        }
    }

    private fun initBasicInfosArray(context: Context):MutableList<String>{
        val mutableList = mutableListOf<String>()
        PokemonRetrofitSingleton.singlePokemonData.observe(this, Observer {
            mutableList.add(it.height.toString())
            mutableList.add(it.weight.toString())
            it.types.forEach {
                mutableList.add(it.type)
            }
        })
    }


    /**
     * Add the list to the adapter
     * @param letter alphabetical letter about the first letter in the pokemon name
     * @param rawData pokemon list starting with the param [letter].
     * @param groupAdapter adapter about loading the lists
     */
    private fun addListToGroup(letter:String, rawData: MutableList<String>, groupAdapter: GroupAdapter<ViewHolder>, context:Context){
        ExpandableGroup(AdapterHeader(letter),true).apply {
            add(Section(generateListItems(rawData, context)))
            groupAdapter.add(this)
        }
    }


    /**
     * Generate list of pokemon base on their first letter
     * @param singlePokemonListPokedex rawData unfiltered
     * @return data filtered by first name alphabetically
     */
    private fun generateListItems(singlePokemonListPokedex:MutableList<String>, context:Context):MutableList<PokedexItem>{
        val returnedList:MutableList<PokedexItem> = mutableListOf()
        for(i in 0 until singlePokemonListPokedex.size){
            returnedList.add(i, PokedexItem(singlePokemonListPokedex[i].sprite, singlePokemonListPokedex[i].name,context.applicationContext))
        }
        return returnedList
    }

    fun initPokedexData(singlePokemonListPokedex: MutableList<SinglePokemonRetrofitPokedex>, context: Context, groupAdapter: GroupAdapter<ViewHolder>){
        return createList(,groupAdapter,context)
    }
}