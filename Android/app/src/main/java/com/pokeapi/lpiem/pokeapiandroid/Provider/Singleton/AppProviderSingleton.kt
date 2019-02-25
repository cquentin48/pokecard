package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.firebase.auth.FirebaseUser


object AppProviderSingleton {
    private lateinit var firebaseUser:FirebaseUser
    var User:FirebaseUser
        get() = firebaseUser
        set(newValue){
            firebaseUser = newValue
        }

    var pokemonList = MutableLiveData<PokemonList >()

    fun getPokeList():MutableLiveData<PokemonList>{

        val callPokemon = RetrofitSingleton.getInstance()!!.getPokemonListData()

        callPokemon.enqueue(object : Callback<PokemonList> {

            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (response.isSuccessful) {
                    pokemonList.postValue(response.body()!!)
                } else {
                    Log.d("Error", "Error while fetching data")
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                t.printStackTrace()
            }
        })
        return pokemonList
    }

    fun cloneList(originalHashMap: List<PokemonRetrofit>):List<PokemonRetrofit>{
        return originalHashMap.toMutableList()
    }

    fun fetchData(){
        pokemonList = getPokeList()
    }

    /**
     * Fetch pokedex entry of a pokemon
     */
   /* fun getPokedexEntry(mainAppActivity: MainAppActivity, pokeIndex:Int){
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
    }*/

    /**
     * Find the pokemon Id by its name
     * @return id pokemonId || 0 if it's not found
     */
    /*fun findPokemonByName(pokeName: String):Int{
        var pokemonIdFound = 0
        this.pokemonList!!.forEach {
            if(it.PokemonName.equals(pokeName)){
                pokemonIdFound = it.PokemonId;
            }
        }
        return pokemonIdFound
    }*/

    /*companion object {
        val FACEBOOK = 1
        val TWITTER = 2
        val GOOGLE = 3
        val POKEAPI = 4

        private var initialized = false
        private var instance: AppProviderSingleton? = null

        fun getInstance(): AppProviderSingleton {
            return if (initialized) {
                instance!!
            } else {
                initialized = true
                instance = AppProviderSingleton()
                instance!!
            }
        }
    }*/

    fun cloneElement(rawElement:MutableList<PokemonPokeApiPageUrl>):MutableList<PokemonPokeApiPageUrl>{
        val newList:MutableList<PokemonPokeApiPageUrl> = mutableListOf()
        rawElement.forEach {
            newList.add(PokemonPokeApiPageUrl(it.PokemonName, it.PokemonPageView))
        }
        return newList
    }
}
