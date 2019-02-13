package com.pokeapi.lpiem.pokeapiandroid.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import android.text.Editable
import android.text.TextWatcher
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.AdapterHeader
import com.xwray.groupie.ExpandableGroup
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.PokedexItem
import com.xwray.groupie.Section
import java.util.*


private const val ARG_PARAM1 = "param1"

class PokedexListView : Fragment(),PokedexFunctionInterface {

    private lateinit var applicationContext: Context
    private lateinit var param:String
    private lateinit var data:MutableList<PokemonRetrofit>
    private lateinit var backupData:MutableList<PokemonRetrofit>
    private val pokemonAPI = AppProviderSingleton.getInstance()
    private lateinit var myFragmentView: View


    @SuppressLint("WrongConstant")
    override fun initPokedex(pokemonImportData : List<PokemonRetrofit>) {
        data = (pokemonImportData).toMutableList()
        backupData = (pokemonImportData).toMutableList()
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        recyclerViewPokedexList.apply {
            layoutManager = GridLayoutManager(activity!!.baseContext, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        createList((filterPokemonListByFirstLetter(data)),groupAdapter)
        pokedexSearchChangedListener()
    }


    fun passContext(context: Context){
        applicationContext = context
    }


    /**
     * Generate list of pokemon base on their first letter
     * @param pokemonList rawData unfiltered
     * @return data filtered by first name alphabetically
     */
    private fun generateListItems(pokemonList:MutableList<PokemonRetrofit>):MutableList<PokedexItem>{
        val returnedList:MutableList<PokedexItem> = mutableListOf()
        for(i in 0 until pokemonList.size){
            returnedList.add(i,PokedexItem(pokemonList[i].sprite!!,pokemonList[i].name!!,applicationContext))
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
        pokemonAPI.getPokeList(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myFragmentView = inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
        initAdapter()
        return inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
    }

    /**
     * Filter pokemon retrofit data by its first letter
     * @param pokemonList rawData
     *
     */
    private fun filterPokemonListByFirstLetter(pokemonList : MutableList<PokemonRetrofit>):TreeMap<String,MutableList<PokemonRetrofit>>{
        val pokemonReturnList:HashMap<String,MutableList<PokemonRetrofit>> = HashMap()
        for(i in 0 until pokemonList.size){
            if(!pokemonReturnList.containsKey(Character.toString(pokemonList[i].name!![0].toUpperCase()))){
                pokemonReturnList[Character.toString(pokemonList[i].name!![0].toUpperCase())] = arrayListOf()
            }
            pokemonReturnList[Character.toString(pokemonList[i].name!![0].toUpperCase())]?.
                    add(pokemonReturnList[Character.toString(pokemonList[i].name!![0].toUpperCase())]!!.size,pokemonList[i])
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


    /**
     * Filter data by the search bar
     */
    private fun filterData(searchString:String):MutableList<PokemonRetrofit>{
        val pokemonRetrofitListFiltered = arrayListOf<PokemonRetrofit>()
        data.forEach {
            if(it.name!!.contains(searchString,true)){
                pokemonRetrofitListFiltered.add(it)
            }
        }
        recyclerViewPokedexList.adapter!!.notifyDataSetChanged()
        return pokemonRetrofitListFiltered
    }

    /**
     * Listener function about smart research
     */
    private fun pokedexSearchChangedListener(){
        pokemonSearchName.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                data = if (s != "") {
                    filterData(s.toString())
                }else{
                    backupData.toMutableList()
                }
                notifyData()
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {}

            override fun afterTextChanged(s: Editable) {}

            fun notifyData(){
                recyclerViewPokedexList.adapter?.notifyDataSetChanged()
            }
        })
    }
}
