package com.pokeapi.lpiem.pokeapiandroid.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import kotlin.collections.HashMap
import com.google.android.flexbox.JustifyContent
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.RetrofitSingleton
import retrofit2.Call


class PokedexListView : AppCompatActivity(),PokedexFunctionInterface {

    override fun initPokedex() {
        initAdapter()
    }
    val pokemonAPI = RetrofitSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pokeapi.lpiem.pokeapiandroid.R.layout.activity_pokedex_list_view)
        initAdapter()
    }

    fun initAdapter(){
        val pokemonReturnList = filterPokemonListByFirstLetter(arrayListOf())
        var mutableList: MutableList<PokemonData> = arrayListOf()
        mutableList.add(PokemonData())
        pokemonReturnList["A"] = mutableList
        mutableList = arrayListOf()
        mutableList.add(PokemonData())
        pokemonReturnList["B"] = mutableList
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.COLUMN
        layoutManager.justifyContent = JustifyContent.FLEX_END
        recyclerViewPokedexList.layoutManager = layoutManager
        recyclerViewPokedexList.adapter = PokedexLineAdapter(pokemonReturnList,this)
    }

    /**
     * Filter pokemonList by regrouping them through the first letter
     */
    private fun filterPokemonListByFirstLetter(pokemonList : MutableList<PokemonData>):HashMap<String,MutableList<PokemonData>>{
        val pokemonReturnList:HashMap<String,MutableList<PokemonData>> = HashMap()
        for(i in 0 until pokemonList.size){
            if(!pokemonReturnList.containsKey(Character.toString(pokemonList[i].PokemonName[0].toUpperCase()))){
                pokemonReturnList[Character.toString(pokemonList[i].PokemonName[0].toUpperCase())] = arrayListOf()
            }
            pokemonReturnList[Character.toString(pokemonList[i].PokemonName[0].toUpperCase())]?.
                    add(pokemonReturnList[Character.toString(pokemonList[i].PokemonName[0].toUpperCase())]!!.size,pokemonList[i])
        }
        return pokemonReturnList
    }
}
