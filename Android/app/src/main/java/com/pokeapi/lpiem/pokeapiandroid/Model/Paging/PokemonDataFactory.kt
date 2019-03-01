package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class PokemonDataFactory : DataSource.Factory<Int, PokedexListDataSource>() {
    lateinit var pokedexData: MutableLiveData<PokedexListDataSource>
    lateinit var pokedexDataSource: PokedexListDataSource

    override fun create(): DataSource<Int, PokedexListDataSource> {
        pokedexDataSource = PokedexListDataSource()
        pokedexData.postValue(pokedexDataSource)
        return pokedexData
    }
}