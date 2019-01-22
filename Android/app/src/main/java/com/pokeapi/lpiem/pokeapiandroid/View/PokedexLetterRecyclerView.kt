package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.pokedex_entry_ressource_layout.view.*
import kotlinx.android.synthetic.main.pokedex_letter_recycler_view.view.*

class PokedexLetterRecyclerView(newListPokemon : MutableList<PokemonData>?, context: Context, letter:String) : androidx.recyclerview.widget.RecyclerView.Adapter<PokedexLetterRecyclerView.ViewHolder>() {
    //private val singleton

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.pokedex_entry_ressource_layout, viewGroup, false))
    }

    private var listPokemon:MutableList<PokemonData> = this!!.initList(newListPokemon)!!
    private var context:Context = context
    private var letter:String = letter

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val pokemonLetterTextView = view.pokemonNamePokedexRessourceTextView!!
        val pokemonSpriteRessource = view.pokemonSpritePokedexImageViewRessource!!
    }

    override fun getItemCount() = listPokemon.size

    fun initList(newListPokemon : MutableList<PokemonData>?):MutableList<PokemonData>?{
        return newListPokemon!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pokemonPosition: Int) {
        viewHolder.pokemonLetterTextView.text = listPokemon[pokemonPosition].PokemonName
        Glide.with(context).load(listPokemon[pokemonPosition].PokemonSprite).into(viewHolder.pokemonSpriteRessource)
    }
}