package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.pokedex_entry_ressource_layout.view.*


class PokedexLineAdapter(newListPokemon: MutableList<PokemonRetrofit>, context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<PokedexLineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.pokedex_entry_ressource_layout, viewGroup, false))
    }

    private var listPokemon:MutableList<PokemonRetrofit> = this!!.initList(newListPokemon)!!
    private var context:Context = context

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val pokemonLetterTextView = view.pokemonNamePokedexRessourceTextView!!
        val pokemonSpriteRessource = view.pokemonSpritePokedexImageViewRessource!!
    }

    override fun getItemCount() = listPokemon.size

    fun initList(newListPokemon : MutableList<PokemonRetrofit>?):MutableList<PokemonRetrofit>?{
        return newListPokemon!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pokemonPosition: Int) {
        viewHolder.pokemonLetterTextView.text = listPokemon[pokemonPosition].name
        Glide.with(context).load(listPokemon[pokemonPosition].sprite).into(viewHolder.pokemonSpriteRessource)
    }
}