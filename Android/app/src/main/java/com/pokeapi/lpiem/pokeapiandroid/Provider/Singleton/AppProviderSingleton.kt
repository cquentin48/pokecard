package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import android.util.Log
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.*
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.Profile
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.FacebookApiProvider
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.GoogleApiProvider
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.TwitterApiProvider
import com.pokeapi.lpiem.pokeapiandroid.View.MainAppActivity
import com.pokeapi.lpiem.pokeapiandroid.View.PokedexListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppProviderSingleton private constructor() {
    var facebookApiProvider: FacebookApiProvider? = null
    var googleApiProvider: GoogleApiProvider? = null
    var twitterApiProvider: TwitterApiProvider? = null
    var userProfile: Profile = Profile()
    private var pokemonCount: Int = 0

    var pokemonList:MutableList<PokemonData> ?

    fun addPokemonToList(newPokemon : PokemonData){
        pokemonList!!.add(newPokemon)
    }

    init {
        facebookApiProvider = FacebookApiProvider()
        pokemonList = mutableListOf()
    }



    fun getPokeList(pokedexView: PokedexListView){
        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonListData()

        callPokemon.enqueue(object : Callback<PokemonList> {

            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (response.isSuccessful()) {
                    val returnedData = response.body()
                    val pokemonImportData = cloneList(returnedData!!.PokemonList)
                    pokedexView.initAdapter(pokemonImportData)
                } else {
                    Log.d("Error", "Error while fetching data")
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    fun cloneList(originalHashMap: List<PokemonRetrofit>):List<PokemonRetrofit>{
        return originalHashMap.toMutableList()
    }

    /**
     * Fetch pokedex entry of a pokemon
     */
    fun getPokedexEntry(mainAppActivity: MainAppActivity, pokeIndex:Int){
        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonSpecies(pokeIndex)

        callPokemon.enqueue(object : Callback<Species> {

            override fun onResponse(call: Call<Species>, response: Response<Species>) {
                if (response.isSuccessful()) {
                    var pokemonDataReturned = response.body()
                    var pokemon = PokemonRetrofit()
                    pokemon.name = pokemonDataReturned!!.name
                    pokemon.id = pokeIndex



                    mainAppActivity.addPokemonToList(pokemon)
                } else {
                    Log.d("Error", "Error de connexion")
                }
            }

            override fun onFailure(call: Call<Species>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    fun getPokemonSpecies(mainAppActivity: MainAppActivity, pokeIndex:Int){
        val pokemonAPI = RetrofitSingleton.getInstance()

        val callPokemon = pokemonAPI!!.getPokemonSpecies(pokeIndex)

        callPokemon.enqueue(object : Callback<Species> {

            override fun onResponse(call: Call<Species>, response: Response<Species>) {
                if (response.isSuccessful()) {
                    var returnedData = response.body()!!

                    mainAppActivity.addPokemonSpecies(pokeIndex-1,returnedData)
                } else {
                    Log.d("Error", "Error de connexion")
                }
            }

            override fun onFailure(call: Call<Species>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                t.printStackTrace()
            }
        })
    }

    /**
     * Find the pokemon Id by its name
     * @return id pokemonId || 0 if it's not found
     */
    fun findPokemonByName(pokeName: String):Int{
        var pokemonIdFound = 0
        this.pokemonList!!.forEach {
            if(it.PokemonName.equals(pokeName)){
                pokemonIdFound = it.PokemonId;
            }
        }
        return pokemonIdFound
    }

    companion object {
        val FACEBOOK = 1
        val TWITTER = 2
        val GOOGLE = 3
        val POKEAPI = 4

        private var instance: AppProviderSingleton? = null

        fun getInstance(): AppProviderSingleton {
            var instanceProvider: AppProviderSingleton = AppProviderSingleton()
            if (instance == null) {
                instance = instanceProvider
            }
            return instanceProvider
        }
    }

    fun cloneElement(rawElement:MutableList<PokemonPokeApiPageUrl>):MutableList<PokemonPokeApiPageUrl>{
        val newList:MutableList<PokemonPokeApiPageUrl> = mutableListOf()
        rawElement.forEach {
            newList.add(PokemonPokeApiPageUrl(it.PokemonName, it.PokemonPageView))
        }
        return newList
    }
}
