package com.pokeapi.lpiem.pokeapiandroid.View.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R

class PokedexPokemonView : AppCompatActivity() {
    val singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex_pokemon_view)
        title = getString(R.string.pokedex_activity_prefix_title)
        displayList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    private fun displayList(){
        /*val recyclerView = recyclerViewPokedexList
        val tmpMutableList:MutableList<PokemonData> = arrayListOf()
        tmpMutableList.add(0,PokemonData())
        recyclerView.adapter = PokemonLetterRecyclerView(tmpMutableList,this,"A")
        recyclerView.layoutManager(androidx.recyclerview.widget.GridLayoutManager(this, 3))*/
    }
}

private operator fun androidx.recyclerview.widget.RecyclerView.LayoutManager?.invoke(gridLayoutManager: androidx.recyclerview.widget.GridLayoutManager) {

}
