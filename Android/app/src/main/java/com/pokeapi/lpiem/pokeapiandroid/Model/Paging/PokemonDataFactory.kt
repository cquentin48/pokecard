package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonAPI
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.SinglePokemonRetrofitPokedex
import io.reactivex.disposables.CompositeDisposable

class PokemonDataFactory(
        private val compositeDisposable: CompositeDisposable,
        private val retrofitInstance: PokemonAPI)
    : DataSource.Factory<Int, SinglePokemonRetrofitPokedex>() {

    val newsDataSourceLiveData = MutableLiveData<PokedexListDataSource>()

    override fun create(): DataSource<Int, SinglePokemonRetrofitPokedex> {
        val newsDataSource = PokedexListDataSource(compositeDisposable, retrofitInstance)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}