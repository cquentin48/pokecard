package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonList
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.RetrofitSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokedexListDataSource : PageKeyedDataSource<Int, PokemonList>() {

    var networkState = MutableLiveData<LoadingState>()
    private val itemSize = 20
    private var totalCount = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PokemonList>) {
        networkState.postValue(LoadingState.LOADING)

        val callPokemon = RetrofitSingleton.getInstance()!!

        callPokemon.getPokemonListData(0, params.requestedLoadSize)
                .enqueue(object : Callback<PokemonList> {
                    override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                        if (response.isSuccessful) {
                            totalCount = response.body()!!.count
                            callback.onResult(response.body()!!.PokemonList as MutableList<PokemonList>, null, 1)
                            networkState.postValue(LoadingState.DONE)

                        } else {
                            networkState.postValue(LoadingState.ERROR)
                        }
                    }

                    override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                        val errorMessage = if (t == null) "unknown error" else t.message
                        Log.e("Error", errorMessage)
                        networkState.postValue(LoadingState.ERROR)
                    }
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonList>) {
        networkState.postValue(LoadingState.LOADING)
        val callPokemon = RetrofitSingleton.getInstance()!!

        callPokemon.getPokemonListData(params.key,params.requestedLoadSize)
                .enqueue(object : Callback<PokemonList>{
                    override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                        if(response.isSuccessful){
                            val key = if(isLastPage(params.key)) params.key+1 else null
                            callback.onResult(response.body()!!.PokemonList as MutableList<PokemonList>,params.key+1)
                        }else{
                            networkState.postValue(LoadingState.ERROR)
                        }
                    }

                    override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                        networkState.postValue(LoadingState.ERROR)
                        Log.e("Error",t.localizedMessage)
                    }
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonList>) {
        //Nothing will happen ;)
    }

    private fun isLastPage(pageId: Int) : Boolean = (pageId+1)*itemSize<totalCount
}