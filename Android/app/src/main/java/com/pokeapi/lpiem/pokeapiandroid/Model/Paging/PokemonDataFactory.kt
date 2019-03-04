package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import io.reactivex.disposables.CompositeDisposable

class PokemonDataFactory(
        private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, PokemonRetrofit>() {

    val newsDataSourceLiveData = MutableLiveData<PokedexListDataSource>()

    override fun create(): DataSource<Int, PokemonRetrofit> {
        val newsDataSource = PokedexListDataSource(compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}