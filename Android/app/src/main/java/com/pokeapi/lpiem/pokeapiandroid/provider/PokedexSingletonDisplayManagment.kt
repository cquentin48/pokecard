package com.pokeapi.lpiem.pokeapiandroid.provider

import android.content.Context
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.AdapterHeader
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.PokedexItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import java.util.*

object PokedexSingletonDisplayManagment {
    /**
     * Create list for the adapter
     * @param rawData RawData
     * @param groupAdapter group adapter for the list
     */
    fun createList(rawData: TreeMap<String, MutableList<PokemonRetrofit>>, groupAdapter: GroupAdapter<ViewHolder>, context: Context){
        for((key,value) in rawData){
            addListToGroup(key,value,groupAdapter, context)
        }
    }


    /**
     * Add the list to the adapter
     * @param letter alphabetical letter about the first letter in the pokemon name
     * @param rawData pokemon list starting with the param [letter].
     * @param groupAdapter adapter about loading the lists
     */
    private fun addListToGroup(letter:String, rawData: MutableList<PokemonRetrofit>, groupAdapter: GroupAdapter<ViewHolder>, context:Context){
        ExpandableGroup(AdapterHeader(letter),true).apply {
            add(Section(generateListItems(rawData, context)))
            groupAdapter.add(this)
        }
    }


    /**
     * Generate list of pokemon base on their first letter
     * @param pokemonList rawData unfiltered
     * @return data filtered by first name alphabetically
     */
    private fun generateListItems(pokemonList:MutableList<PokemonRetrofit>, context:Context):MutableList<PokedexItem>{
        val returnedList:MutableList<PokedexItem> = mutableListOf()
        for(i in 0 until pokemonList.size){
            returnedList.add(i, PokedexItem(pokemonList[i].sprite, pokemonList[i].name,context.applicationContext))
        }
        return returnedList
    }

    /**
     * Filter pokemon retrofit data by its first letter
     * @param pokemonList rawData
     * @return rawData ordered by first letter alphabetically
     */
    private fun filterPokemonListByFirstLetter(pokemonList : MutableList<PokemonRetrofit>):TreeMap<String,MutableList<PokemonRetrofit>>{
        val pokemonReturnList:HashMap<String,MutableList<PokemonRetrofit>> = HashMap()
        for(i in 0 until pokemonList.size){
            if(!pokemonReturnList.containsKey(Character.toString(pokemonList[i].name[0].toUpperCase()))){
                pokemonReturnList[Character.toString(pokemonList[i].name[0].toUpperCase())] = arrayListOf()
            }
            pokemonReturnList[Character.toString(pokemonList[i].name[0].toUpperCase())]?.
                    add(pokemonReturnList[Character.toString(pokemonList[i].name[0].toUpperCase())]!!.size,pokemonList[i])
        }
        return TreeMap(pokemonReturnList)
    }

    fun initPokedexData(pokemonList: MutableList<PokemonRetrofit>,context: Context, groupAdapter: GroupAdapter<ViewHolder>){
        return createList((filterPokemonListByFirstLetter(pokemonList)),groupAdapter,context)
    }
}