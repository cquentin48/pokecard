package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pokeapi.lpiem.pokeapiandroid.R

class pokedexListView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex_list_view)
    }

    fun launchactivity(pokemonId: Int){
        val context: Context = this
        val intent:Intent = Intent(context,PokedexPokemonView::class.java)
        intent.putExtra("PokemonId", pokemonId)
        startActivity(intent)
    }
}
