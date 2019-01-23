package com.pokeapi.lpiem.pokeapiandroid.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import kotlin.collections.HashMap
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton


class PokedexListView : AppCompatActivity(),PokedexFunctionInterface {

    override fun initPokedex(pokemonImportData : List<PokemonRetrofit>) {
        recyclerViewPokedexList.layoutManager = LinearLayoutManager(this)
        val data = (pokemonImportData).toMutableList()
        recyclerViewPokedexList.adapter = PokedexLineAdapter(filterPokemonListByFirstLetter(data),this)
    }
    val pokemonAPI = AppProviderSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pokeapi.lpiem.pokeapiandroid.R.layout.activity_pokedex_list_view)
        initAdapter()
    }

    fun initAdapter(){
        pokemonAPI.getPokeList(this)
    }

    /**
     * Filter pokemonList by regrouping them through the first letter
     */
    private fun filterPokemonListByFirstLetter(pokemonList : MutableList<PokemonRetrofit>):HashMap<String,MutableList<PokemonRetrofit>>{
        val pokemonReturnList:HashMap<String,MutableList<PokemonRetrofit>> = HashMap()
        for(i in 0 until pokemonList.size){
            if(!pokemonReturnList.containsKey(Character.toString(pokemonList[i].name!![0].toUpperCase()))){
                pokemonReturnList[Character.toString(pokemonList[i].name!![0].toUpperCase())] = arrayListOf()
            }
            pokemonReturnList[Character.toString(pokemonList[i].name!![0].toUpperCase())]?.
                    add(pokemonReturnList[Character.toString(pokemonList[i].name!![0].toUpperCase())]!!.size,pokemonList[i])
        }
        return pokemonReturnList
    }
}
