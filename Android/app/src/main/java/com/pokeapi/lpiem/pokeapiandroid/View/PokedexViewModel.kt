package com.pokeapi.lpiem.pokeapiandroid.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.Model.Paging.PokedexDataSource
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonAPI
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonList
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.PokedexItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokedexViewModel: ViewModel() {
    companion object {
        private const val PAGE_SIZE = 50
    }


    val repos: LiveData<PagedList<PokedexItem>>

    val networkState: LiveData<LoadingState>

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(PokemonAPI::class.java)

        val factory = PokedexDataSource(api, ::convertToItem)
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()

        repos = LivePagedListBuilder(factory, config).build()
        networkState = factory.source.networkState
    }

    private fun convertToItem(repo: PokemonList): PokedexItem = PokedexItem(repo.)
}