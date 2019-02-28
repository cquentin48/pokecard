package com.pokeapi.lpiem.pokeapiandroid.Model.Paging

import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonAPI
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonList
import com.xwray.groupie.kotlinandroidextensions.Item
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class PokedexDataSource<T: Item<*>>(
        api: PokemonAPI,
        converter: (PokemonList) -> T
) : DataSource.Factory<Int, T>() {

    val source = PageKeyedRepoDataSource(api, converter)

    override fun create(): DataSource<Int, T> {
        return source
    }
}