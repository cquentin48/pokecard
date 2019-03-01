package com.pokeapi.lpiem.pokeapiandroid.View

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.lifecycle.Transformations
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.Model.Paging.PokemonDataFactory
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonList
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class PokedexViewModel : ViewModel(){
    private var executor: Executor? = null
    private var networkState: LiveData<LoadingState>? = null
    var pokedexPokemonListLiveData: LiveData<PagedList<PokemonList>>? = null


    /*
     * Step 1: We are initializing an Executor class
     * Step 2: We are getting an instance of the DataSourceFactory class
     * Step 3: We are initializing the network state liveData as well.
     *         This will update the UI on the network changes that take place
     *         For instance, when the data is getting fetched, we would need
     *         to display a loader and when data fetching is completed, we
     *         should hide the loader.
     * Step 4: We need to configure the PagedList.Config.
     * Step 5: We are initializing the pageList using the config we created
     *         in Step 4 and the DatasourceFactory we created from Step 2
     *         and the executor we initialized from Step 1.
     */
    init{
        executor = Executors.newFixedThreadPool(5)

        val feedDataFactory = PokemonDataFactory()
        networkState = Transformations.switchMap(feedDataFactory.pokedexData)
        ) { dataSource -> dataSource.getNetworkState() }

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(20).build()

        pokedexPokemonListLiveData = LivePagedListBuilder(feedDataFactory, pagedListConfig)
                .setFetchExecutor(executor!!)
                .build()
    }

    /*
     * Getter method for the network state
     */
    fun getNetworkState(): LiveData<LoadingState>? {
        return networkState
    }
}