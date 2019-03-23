package com.pokeapi.lpiem.pokeapiandroid.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import com.pokeapi.lpiem.pokeapiandroid.R

class PokedexPokemonView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex_pokemon_view)
        title = getString(R.string.pokedex_activity_prefix_title)
        //displayList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    /**
     * Display data for the selected pokemon
     */
    @Deprecated("NotUsed")
    private fun displayList(){
    }
}
