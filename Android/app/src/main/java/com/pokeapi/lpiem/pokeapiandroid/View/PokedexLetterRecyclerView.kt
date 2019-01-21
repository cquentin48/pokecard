package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.pokedex_letter_recycler_view.view.*

class PokemonLetterRecyclerView(newListPokemon : MutableList<PokemonData>?, context: Context, letter:String) : androidx.recyclerview.widget.RecyclerView.Adapter<PokemonLetterRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.pokedex_entry_ressource_layout, p0, false))
    }

    private var listPokemon:MutableList<PokemonData> = newListPokemon!!
    private var context:Context? = null
    private var letter:String = letter

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val pokemonLetterTextView = view.pokedexLetter
        val pokemonListRecyclerView = view.pokedexListRecyclerView
    }

    override fun getItemCount() = listPokemon.size

    override fun onBindViewHolder(holder: ViewHolder, pokemonPosition: Int) {
        holder.pokemonLetterTextView.text = letter
        holder.pokemonListRecyclerView.adapter = PokedexLineAdapter(listPokemon,context!!)
    }
}