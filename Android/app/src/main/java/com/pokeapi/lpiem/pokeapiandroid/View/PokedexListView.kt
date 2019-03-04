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
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.AdapterHeader
import com.xwray.groupie.ExpandableGroup
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.PokedexItem
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.PokedexViewAdapter
import com.xwray.groupie.Section
import java.util.*


private const val ARG_PARAM1 = "param1"

class PokedexListView : Fragment(){

    private lateinit var applicationContext: Context
    private lateinit var param:String
    private lateinit var myFragmentView: View
    private lateinit var viewModel: PokedexViewModel
    private lateinit var newsListAdapter: PokedexViewAdapter

    private fun initAdapter() {
        newsListAdapter = PokedexViewAdapter(viewModel::retry,applicationContext)
        recyclerViewPokedexList.layoutManager = GridLayoutManager(applicationContext,3)
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

    /*@SuppressLint("WrongConstant")
    fun initPokedex() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        recyclerViewPokedexList.apply {
            layoutManager = GridLayoutManager(activity!!.baseContext, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        AppProviderSingleton.pokemonList.observe(this, Observer {
            createList(filterPokemonListByFirstLetter(it.PokemonList as MutableList<PokemonRetrofit>),groupAdapter)
        })
        AppProviderSingleton.fetchData()
        pokedexSearchChangedListener()
    }*/


    fun passContext(context: Context){
        applicationContext = context
    }

    private fun generateListItems(pokemonList:MutableList<PokemonRetrofit>):MutableList<PokedexItem>{
        val returnedList:MutableList<PokedexItem> = mutableListOf()
        for(i in 0 until pokemonList.size){
            returnedList.add(i,PokedexItem(pokemonList[i].sprite!!,pokemonList[i].name!!,applicationContext))
        }
        return returnedList
    }

    /*private fun returnItem(pokemon:PokemonRetrofit):PokedexItem{
        return PokedexItem(pokemon.sprite!!,pokemon.name!!,this)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM1) as String
            param
        }
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
        myFragmentView = inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
        return inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)
                .get(PokedexViewModel::class.java)
        initAdapter()
        initState()
        //initPokedex()
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
    /*private fun filterData(searchString:String):MutableList<PokemonRetrofit>{
        val pokemonRetrofitListFiltered = arrayListOf<PokemonRetrofit>()
        data.forEach {
            if(it.name!!.contains(searchString,true)){
                pokemonRetrofitListFiltered.add(it)
            }
        }
        recyclerViewPokedexList.adapter!!.notifyDataSetChanged()
        return pokemonRetrofitListFiltered
    }*/

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
        for((key,_) in rawData){
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
                    //data = filterData(s.toString())
                }else{
                    //data = backupData.toMutableList()
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
}
