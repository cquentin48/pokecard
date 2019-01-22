package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import kotlinx.android.synthetic.main.pokedex_letter_recycler_view.view.*


class PokedexLineAdapter(newListPokemon : HashMap<String,MutableList<PokemonData>>?, context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<PokedexLineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(com.pokeapi.lpiem.pokeapiandroid.R.layout.pokedex_letter_recycler_view, viewGroup, false))
    }

    private var listPokemon:HashMap<String,MutableList<PokemonData>> = newListPokemon!!
    private var context:Context = context

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val pokemonIndexLetter = view.pokedexLetter
        val pokemonRecyclerView = view.pokedexListRecyclerView
        }

    override fun getItemCount() = listPokemon.size

    override fun onBindViewHolder(holder: ViewHolder, pokemonPosition: Int) {
        holder.pokemonIndexLetter.text = getCharacterFromIndex(pokemonPosition)
        holder.pokemonRecyclerView.adapter = PokedexLetterRecyclerView(listPokemon[getCharacterFromIndex(pokemonPosition)],
                                                                       context,
                                                                       getCharacterFromIndex(pokemonPosition))
    }

    /**
     * Selecting a pokemon from the pokedex
     */
    private fun selectPokemon(pokemonId: Int){
        startActivity(context!!, Intent(context, PokedexPokemonView::class.java), null)
    }

    private fun getCharacterFromIndex(index : Int):String{
        return index.toChar().toString()
    }
}