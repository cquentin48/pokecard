package com.pokeapi.lpiem.pokeapiandroid.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import android.text.Editable
import android.text.TextWatcher
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.AdapterHeader
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder


class PokedexListView : AppCompatActivity(),PokedexFunctionInterface {
    private lateinit var data:MutableList<PokemonRetrofit>
    private lateinit var backupData:MutableList<PokemonRetrofit>
    private lateinit var adapter:GridLayoutManager
    private lateinit var section:Section

    @SuppressLint("WrongConstant")
    override fun initPokedex(pokemonImportData : List<PokemonRetrofit>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        recyclerViewPokedexList.apply {
            layoutManager = GridLayoutManager(this@PokedexListView, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
        }
        adapter = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        recyclerViewPokedexList.layoutManager = adapter
        data = (pokemonImportData).toMutableList()
        createList(filterPokemonListByFirstLetter(data),groupAdapter)
        backupData = (pokemonImportData).toMutableList()
        recyclerViewPokedexList.adapter = PokedexLineAdapter(data,this)
        pokedexSearchChangedListener()
    }
    val pokemonAPI = AppProviderSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pokeapi.lpiem.pokeapiandroid.R.layout.activity_pokedex_list_view)
        initAdapter()
    }

    /**
     * Filter pokemon retrofit data by its first letter
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

    private fun createList(rawData:HashMap<String, MutableList<PokemonRetrofit>>, groupAdapter: GroupAdapter<ViewHolder>){
        for((key,value) in rawData){
            addListToGroup(key,value,groupAdapter)
        }
    }

    private fun addListToGroup(letter:String, rawData: MutableList<PokemonRetrofit>, groupAdapter: GroupAdapter<ViewHolder>){
        section = Section()
        ExpandableGroup(AdapterHeader(letter),true).apply {
            section.addAll(rawData)
            groupAdapter.add(this)
        }
    }

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
    fun pokedexSearchChangedListener(){
        pokemonSearchName.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (s != "") {
                    data = filterData(s.toString())
                }else{
                    data = backupData.toMutableList()
                }
                notifyData()
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
                //Nothing
            }

            override fun afterTextChanged(s: Editable) {
                //Nothing
            }

            fun notifyData(){
                recyclerViewPokedexList.adapter?.notifyDataSetChanged()
            }
        })
    }

    fun initAdapter(){
        pokemonAPI.getPokeList(this)
    }
}
