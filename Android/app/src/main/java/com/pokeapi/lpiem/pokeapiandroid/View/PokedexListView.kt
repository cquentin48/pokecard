package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import kotlin.collections.HashMap
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R

private const val ARG_PARAM1 = "param1"

class PokedexListView : Fragment(),PokedexFunctionInterface {

    val pokemonAPI = AppProviderSingleton.getInstance()
    private lateinit var applicationContext: Context
    private lateinit var param:String

    override fun initPokedex(pokemonImportData : List<PokemonRetrofit>) {
        val data = (pokemonImportData).toMutableList()
        recyclerViewPokedexList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPokedexList.adapter = PokedexLineAdapter(filterPokemonListByFirstLetter(data),activity!!.baseContext)
    }


    fun passContext(context: Context){
        applicationContext = context
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM1) as String
            param
        }
        initAdapter()
    }

    fun initAdapter(){
        pokemonAPI.getPokeList(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
                PokedexListView().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)

                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
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
