package com.pokeapi.lpiem.pokeapiandroid.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pokeapi.lpiem.pokeapiandroid.Provider.PokemonRetrofitSingleton
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.AdapterHeader
import com.xwray.groupie.ExpandableGroup
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.PokedexViewModel
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.PokedexItem
import com.pokeapi.lpiem.pokeapiandroid.view.`interface`.PokedexFunctionInterface
import com.xwray.groupie.Section
import java.util.*


private const val ARG_PARAM1 = "param1"

class PokedexListView : Fragment(){

    private lateinit var param:String
    private val viewModel = PokedexViewModel()
    private lateinit var data:MutableList<PokemonRetrofit>
    private lateinit var myFragmentView: View


    @SuppressLint("WrongConstant")
    /**
     * Pokedex initialisation
     * @param pokemonImportData raw imported from the api
     */
    fun initPokedex() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        recyclerViewPokedexList.apply {
            layoutManager = GridLayoutManager(activity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        PokemonRetrofitSingleton.pokemonList.observe(this, androidx.lifecycle.Observer {
            viewModel.initPokedexAdapter(it.pokemonList.toMutableList(),context!!,groupAdapter)
        })
    }


    /**
     * Generate list of pokemon base on their first letter
     * @param pokemonList rawData unfiltered
     * @return data filtered by first name alphabetically
     */
    private fun generateListItems(pokemonList:MutableList<PokemonRetrofit>):MutableList<PokedexItem>{
        val returnedList:MutableList<PokedexItem> = mutableListOf()
        for(i in 0 until pokemonList.size){
            returnedList.add(i,PokedexItem(pokemonList[i].sprite, pokemonList[i].name,activity!!.applicationContext))
        }
        return returnedList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM1) as String
            param
        }
    }

    /**
     * Initialisation of the adapter
     */
    fun initAdapter(){
        viewModel.loadPokedex(context!!)
        //initPokedex()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myFragmentView = inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
        initAdapter()
        return inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPokedex()
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

    /**
     * Create list for the adapter
     * @param rawData RawData
     * @param groupAdapter group adapter for the list
     */
    private fun createList(rawData: TreeMap<String, MutableList<PokemonRetrofit>>, groupAdapter: GroupAdapter<ViewHolder>){
        for((key,value) in rawData){
            addListToGroup(key,value,groupAdapter)
        }
    }


    /**
     * Add the list to the adapter
     * @param letter alphabetical letter about the first letter in the pokemon name
     * @param rawData pokemon list starting with the param [letter].
     * @param groupAdapter adapter about loading the lists
     */
    private fun addListToGroup(letter:String, rawData: MutableList<PokemonRetrofit>, groupAdapter: GroupAdapter<ViewHolder>){
        ExpandableGroup(AdapterHeader(letter),true).apply {
            add(Section(generateListItems(rawData)))
            groupAdapter.add(this)
        }
    }
}
