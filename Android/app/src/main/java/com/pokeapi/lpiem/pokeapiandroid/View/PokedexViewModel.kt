package com.pokeapi.lpiem.pokeapiandroid.View

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.lifecycle.Transformations
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.Model.Paging.PokedexListDataSource
import com.pokeapi.lpiem.pokeapiandroid.Model.Paging.PokemonDataFactory
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.RetrofitSingleton
import io.reactivex.disposables.CompositeDisposable


class PokedexViewModel : ViewModel(){

    private val networkService = RetrofitSingleton.getInstance()
    var newsList: LiveData<PagedList<PokemonRetrofit>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 43
    private val pokemonDataSourceFactory: PokemonDataFactory

    init {
        pokemonDataSourceFactory = PokemonDataFactory(compositeDisposable)
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