package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.pokeapi.lpiem.pokeapiandroid.model.enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonAPI
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.SinglePokemonRetrofitPokedex
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action


class PokedexListDataSource(
        private val compositeDisposable: CompositeDisposable,
        private val api: PokemonAPI)
    : PageKeyedDataSource<Int, SinglePokemonRetrofitPokedex>() {

    var networkState = MutableLiveData<LoadingState>()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, SinglePokemonRetrofitPokedex>) {
        networkState.postValue(LoadingState.LOADING)
        compositeDisposable.add(
                api.getPokemonListData(0, params.requestedLoadSize)
                        .subscribe({ response ->
                            updateLoadingState(LoadingState.DONE)
                            callback.onResult(response.singlePokemonListPokedex,
                                    null,
                                    1)
                        }
                                , {
                            updateLoadingState(LoadingState.ERROR)
                            Log.e("Error", it.localizedMessage)
                            it.printStackTrace()
                            //setRetry(Action { loadInitial(params, callback) })
                        })
        )
    }

    private fun updateLoadingState(newState: LoadingState) {
        networkState.postValue(newState)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SinglePokemonRetrofitPokedex>) {
        updateLoadingState(LoadingState.LOADING)
        compositeDisposable.add(
                api.getPokemonListData(params.key, params.requestedLoadSize)
                        .subscribe(
                                { response ->
                                    updateLoadingState(LoadingState.DONE)
                                    callback.onResult(response.singlePokemonListPokedex,
                                            params.key + 1
                                    )
                                },
                                {
                                    updateLoadingState(LoadingState.ERROR)
                                    Log.e("Error", it.localizedMessage)
                                    it.printStackTrace()
                                    setRetry(Action { loadAfter(params, callback) })
                                }
                        )
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SinglePokemonRetrofitPokedex>) {
        //Nothing will happen ;)
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}