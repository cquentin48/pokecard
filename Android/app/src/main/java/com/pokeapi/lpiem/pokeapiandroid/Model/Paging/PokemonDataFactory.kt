package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonAPI
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit
import io.reactivex.disposables.CompositeDisposable

class PokemonDataFactory(
        private val compositeDisposable: CompositeDisposable,
        private val retrofitInstance: PokemonAPI)
    : DataSource.Factory<Int, PokemonRetrofit>() {

    val newsDataSourceLiveData = MutableLiveData<PokedexListDataSource>()

    override fun create(): DataSource<Int, PokemonRetrofit> {
        val newsDataSource = PokedexListDataSource(compositeDisposable, retrofitInstance)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}