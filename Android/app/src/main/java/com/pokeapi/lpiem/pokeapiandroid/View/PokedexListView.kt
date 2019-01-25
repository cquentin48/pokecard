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
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.PokedexItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder


class PokedexListView : AppCompatActivity(),PokedexFunctionInterface {
    private lateinit var data:MutableList<PokemonRetrofit>
    private lateinit var backupData:MutableList<PokemonRetrofit>
    private lateinit var adapter:GridLayoutManager
    private val pokemonAPI = AppProviderSingleton.getInstance()

    private val pokemonSectionList = Section()


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
        backupData = (pokemonImportData).toMutableList()
        recyclerViewPokedexList.adapter = PokedexLineAdapter(data,this)
        pokedexSearchChangedListener()
    }

    private fun createPokemonList(pokemonListByLetter : MutableList<PokemonRetrofit>, pokemonLetter:String,groupAdapter:GroupAdapter<ViewHolder>){
        ExpandableGroup(AdapterHeader(pokemonLetter), false).apply {
            pokemonSectionList.addAll(MutableList(pokemonListByLetter.size){pokemonListByLetter})
            groupAdapter.add(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pokeapi.lpiem.pokeapiandroid.R.layout.activity_pokedex_list_view)
        initAdapter()
    }

    private fun filterData(searchString:String):MutableList<PokemonRetrofit>{
        val pokemonRetrofitListFiltered = arrayListOf<PokemonRetrofit>()
        data.forEach {
            if(it.name!!.contains(searchString,true)){
                data.add(it)
            }
        }
        recyclerViewPokedexList.adapter!!.notifyDataSetChanged()
        return data
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
