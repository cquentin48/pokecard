package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R

class MainAppActivity : AppCompatActivity(), InterfaceCallBackController<Any> {
    val singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for(i in 1..19){
            singleton!!.getPokeInfos(this,i)
        }
        for(i in 1..19){
            singleton!!.getPokemonSpecies(this,i)
        }
        setContentView(R.layout.activity_main_app)
    }

    override fun updatePokemonData(i: Int, s: Species) {
        singleton!!.listPokemon.get(i).pokemonSpecies=s
    }

    override fun updatePokedexEntry(i: Int, p: String) {
        singleton!!.listPokemon.get(i).pokemonPokedexEntry = p
    }

    override fun showPokemon(p: PokemonRetrofit) {
        val pokemon = PokemonData()
        singleton!!.listPokemon.add(pokemon)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.mapOrPokedexMenu ->{
                val intent = Intent(this,PokedexPokemonView::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
