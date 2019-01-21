package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.pokedex_entry_ressource_layout.view.*

class PokedexLineAdapter(newListPokemon : MutableList<PokemonData>?, context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<PokedexLineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.pokedex_entry_ressource_layout, p0, false))
    }

    private var listPokemon:List<PokemonData> = newListPokemon!!
    private var context:Context? = null
    var ContextView:Context? = null
        get() = this.context!!

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val pokemonImageView = view.pokemonSpritePokedexImageViewRessource;
        val pokemonNameTextView = view.pokemonNamePokedexRessourceTextView;
        //val pokemonPokedexLayout = view.pokemonPokedexLayout
        }

    override fun getItemCount() = listPokemon.size

    override fun onBindViewHolder(holder: ViewHolder, pokemonPosition: Int) {
        holder.pokemonNameTextView.text = listPokemon[pokemonPosition].PokemonName
        Glide.with(context!!).load(listPokemon[pokemonPosition].PokemonSprite).into(holder.pokemonImageView)
    }

    /**
     * Selecting a pokemon from the pokedex
     */
    private fun selectPokemon(pokemonId: Int){
        startActivity(context!!, Intent(context, PokedexPokemonView::class.java), null)
    }
}