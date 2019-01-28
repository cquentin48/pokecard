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
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.PokedexItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import java.util.*

class PokedexListView : AppCompatActivity(),PokedexFunctionInterface {
    private lateinit var data:MutableList<PokemonRetrofit>
    private lateinit var backupData:MutableList<PokemonRetrofit>
    private lateinit var adapter:GridLayoutManager
    private val pokemonAPI = AppProviderSingleton.getInstance()


    @SuppressLint("WrongConstant")
    override fun initPokedex(pokemonImportData : List<PokemonRetrofit>) {
        data = (pokemonImportData).toMutableList()
        backupData = (pokemonImportData).toMutableList()
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        recyclerViewPokedexList.apply {
            layoutManager = GridLayoutManager(this@PokedexListView, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        createList((filterPokemonListByFirstLetter(data)),groupAdapter)
        pokedexSearchChangedListener()
    }

    private fun generateListItems(pokemonList:MutableList<PokemonRetrofit>):MutableList<PokedexItem>{
        val returnedList:MutableList<PokedexItem> = mutableListOf()
        for(i in 0 until pokemonList.size){
            returnedList.add(i,PokedexItem(pokemonList[i].sprite!!,pokemonList[i].name!!,this))
        }
        return returnedList
    }

    private fun returnItem(pokemon:PokemonRetrofit):PokedexItem{
        return PokedexItem(pokemon.sprite!!,pokemon.name!!,this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pokeapi.lpiem.pokeapiandroid.R.layout.activity_pokedex_list_view)
        initAdapter()
    }

    /**
     * Filter pokemon retrofit data by its first letter
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

    private fun filterHashmapByAlphabeticalLetter(data:HashMap<String, MutableList<PokemonRetrofit>>): HashMap<String, MutableList<PokemonRetrofit>> {
        val map = object : HashMap<String, MutableList<PokemonRetrofit>>() {
            init {
                var ch = 'A'
                while (ch <= 'Z') {
                    if(getPokemonListByFirstLetter(data,ch.toString()) != null){
                        put(ch.toString(),data[ch.toString()]!!)
                    }
                    ch++
                }
            }
        }
        return map
    }

    private fun getPokemonListByFirstLetter(rawData: HashMap<String,MutableList<PokemonRetrofit>>, letter: String):MutableList<PokemonRetrofit>?{
        for((key,value) in rawData){
            if(key == letter){
                return rawData[key]
            }
        }
        return null
    }

    /**
     * Listener function about smart research
     */
    private fun pokedexSearchChangedListener(){
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
