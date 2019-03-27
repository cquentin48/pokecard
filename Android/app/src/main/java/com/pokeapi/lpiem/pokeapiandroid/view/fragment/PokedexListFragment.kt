package com.pokeapi.lpiem.pokeapiandroid.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pokeapi.lpiem.pokeapiandroid.model.enum.LoadingState
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.PokedexViewAdapter
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.PokedexViewModel


private const val ARG_PARAM1 = "param1"

class PokedexListView : Fragment(){

    private val viewModel = PokedexViewModel()
    private lateinit var applicationContext: Context
    private lateinit var param:String
    private lateinit var newsListAdapter: PokedexViewAdapter
    private lateinit var singlePokemonFragment: PokedexViewAdapter

    private fun initPokedex() {
        newsListAdapter = PokedexViewAdapter(viewModel::retry,context!!)
        recyclerViewPokedexList.layoutManager = GridLayoutManager(context,3)
        recyclerViewPokedexList.adapter = newsListAdapter
        viewModel.newsList.observe(this, Observer {
            newsListAdapter.submitList(it)
        })
    }

    private fun initState() {
        viewModel.getState().observe(this, Observer { state ->
            /*progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE*/
            if (!viewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: LoadingState.DONE)
            }

        })
    }

    @SuppressLint("WrongConstant")
    /**
     * Pokedex initialisation
     * @param pokemonImportData raw imported from the api
     */
    /*fun initPokedex() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        recyclerViewPokedexList.apply {
            layoutManager = GridLayoutManager(activity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        PokemonRetrofitSingleton.pokemonLists.observe(this, androidx.lifecycle.Observer {
            viewModel.initPokedexAdapter(it.pokemonLists.toMutableList(),context!!,groupAdapter)
        })
    }*/

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
        initPokedex()
        initState()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }
}
