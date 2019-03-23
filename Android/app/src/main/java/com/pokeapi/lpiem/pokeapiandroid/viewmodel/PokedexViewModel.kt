package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.Model.Paging.PokedexListDataSource
import com.pokeapi.lpiem.pokeapiandroid.Model.Paging.PokemonDataFactory
import com.pokeapi.lpiem.pokeapiandroid.Provider.PokemonRetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.RetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.provider.PokedexSingletonDisplayManagment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.reactivex.disposables.CompositeDisposable

class PokedexViewModel : ViewModel(){
    /**
     * Load the pokedex data into the fragment
     */
    fun loadPokedex(context: Context){
        PokemonRetrofitSingleton.getPokeList(context)
    }

    /**
     * Init the pokedex Adapter
     */
    fun initPokedexAdapter(rawList: MutableList<PokemonRetrofit>, context: Context, groupAdapter:GroupAdapter<ViewHolder>){
        return PokedexSingletonDisplayManagment.initPokedexData(rawList,context, groupAdapter)
    }


    private val retrofitInstance = RetrofitSingleton.getInstance()
    var newsList: LiveData<PagedList<PokemonRetrofit>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val pokemonDataSourceFactory: PokemonDataFactory

    init {
        pokemonDataSourceFactory = PokemonDataFactory(compositeDisposable,retrofitInstance!!)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        newsList = LivePagedListBuilder<Int, PokemonRetrofit>(pokemonDataSourceFactory, config).build()
    }


    fun getState(): LiveData<LoadingState> = Transformations.switchMap<PokedexListDataSource,
            LoadingState>(pokemonDataSourceFactory.newsDataSourceLiveData, PokedexListDataSource::networkState)

    fun retry() {
        //pokemonDataSourceFactory.newsDataSourceLiveData.value?
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}