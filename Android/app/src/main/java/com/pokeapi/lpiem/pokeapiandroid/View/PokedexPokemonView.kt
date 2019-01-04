package com.pokeapi.lpiem.pokeapiandroid.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.activity_pokedex_list_view.*

class PokedexPokemonView : AppCompatActivity() {
    val singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex_pokemon_view)
        setTitle("Pokédex Mondial")
        displayList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    private fun displayList(){
        val recyclerView = recyclerViewPokedexList
        recyclerView.layoutManager(GridLayoutManager(this,3))
        val tmpMutableList:MutableList<PokemonData> = MutableList(1)
        recyclerView.adapter = PokemonLetterRecyclerView(tmpMutableList,this,"A")
    }
}

private operator fun RecyclerView.LayoutManager?.invoke(gridLayoutManager: GridLayoutManager) {

}
