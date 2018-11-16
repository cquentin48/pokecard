package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ListView
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R

class PokedexPokemonView : AppCompatActivity() {
    val singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex_pokemon_view)
        setTitle("PokeCard : Pokédex Mondial")
        afficheList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    private fun afficheList(){
        var listView: ListView = findViewById(R.id.pokedexListListView)
        val context: Context = this
        val adapter = PokedexAdapter(context, singleton!!.listPokemon)
        listView.adapter = adapter
    }
}
