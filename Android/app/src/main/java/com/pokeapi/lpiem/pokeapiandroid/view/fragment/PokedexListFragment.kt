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
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.PokedexViewModel
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.PokedexItem


private const val ARG_PARAM1 = "param1"

class PokedexListView : Fragment(){

    private lateinit var param:String
    private val viewModel = PokedexViewModel()


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
        initPokedex()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }
}
