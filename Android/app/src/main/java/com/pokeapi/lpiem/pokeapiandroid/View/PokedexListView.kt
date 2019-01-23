package com.pokeapi.lpiem.pokeapiandroid.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import kotlin.collections.HashMap
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.xwray.groupie.GroupAdapter
import android.text.Editable
import android.text.TextWatcher




class PokedexListView : AppCompatActivity(),PokedexFunctionInterface {

    override fun initPokedex(pokemonImportData : List<PokemonRetrofit>) {
        //val adapter = GroupAdapter()
        recyclerViewPokedexList.layoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        val data = (pokemonImportData).toMutableList()
        recyclerViewPokedexList.adapter = PokedexLineAdapter(data,this)
    }
    val pokemonAPI = AppProviderSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pokeapi.lpiem.pokeapiandroid.R.layout.activity_pokedex_list_view)
        initAdapter()
    }

    fun pokedexSearchChanged(){
        pokemonSearchName.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (s != "") {
                    notifyData()
                }
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
                //Nothing
            }

            override fun afterTextChanged(s: Editable) {
                //If nothing : displayAllPokemons
                notifyData()
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
