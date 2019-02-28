package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonAPI
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonList
import com.xwray.groupie.kotlinandroidextensions.Item
import java.io.IOException

class PageKeyedRepoDataSource<T : Item<*>>(
        private val api: PokemonAPI,
        private val converter: (PokemonList) -> T
) : PageKeyedDataSource<Int, T>() {

    companion object {
        private const val TAG = "PageKeyedRepoDataSource"
    }

    val networkState = MutableLiveData<LoadingState>()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // not used
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        callAPI(params.key, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos.map(converter), next)
        }
    }

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, T>
    ) {
        callAPI(1, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos.map(converter), null, next)
        }
    }

    private fun callAPI(
            page: Int,
            perPage: Int,
            callback: (repos: List<PokemonList>, next: Int?) -> Unit
    ) {
        Log.d(TAG, "page: $page, perPage: $perPage")

        networkState.postValue(LoadingState.LOADING)

        var state = LoadingState.ERROR

        try {
            // getting google's repository list
            val response = api.getPokemonListData(0, page).execute()

            response.body()?.let {
                var next: Int? = null
                response.headers().get("Link")?.let {
                    val regex = Regex("rel=\"next\"")
                    if (regex.containsMatchIn(it)) {
                        next = page + 1
                    }
                }

                callback(it, next)
                state = LoadingState.DONE
            }
        }
         catch (e: IOException) {
            Log.w(TAG, e)
        }

        networkState.postValue(state)
    }

}