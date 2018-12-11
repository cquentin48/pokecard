package com.pokeapi.lpiem.pokeapiandroid.View

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.R

class PokedexAdapter(newListPokemon : MutableList<PokemonData>?) : RecyclerView.Adapter<PokedexAdapter.ViewHolder>() {

    private var listPokemon:List<PokemonData> = newListPokemon!!

    class ViewHolder(val pokemonNameTextView: TextView) : RecyclerView.ViewHolder(pokemonNameTextView)
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
            val textView = LayoutInflater.from(parent.context).inflate(R.layout.pokedex_entry_ressource_layout,parent,false) as TextView
            return ViewHolder(textView)
        }

    override fun getItemCount() = listPokemon.size

    override fun onBindViewHolder(holder: ViewHolder, pokemonPosition: Int) {
        holder.pokemonNameTextView.text = listPokemon[pokemonPosition].PokemonName
    }
}