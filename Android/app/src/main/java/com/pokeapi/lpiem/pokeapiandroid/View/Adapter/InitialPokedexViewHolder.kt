package com.pokeapi.lpiem.pokeapiandroid.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.SinglePokemonRetrofitPokedex
import com.pokeapi.lpiem.pokeapiandroid.view.fragment.SinglePokemonFragment
import kotlinx.android.synthetic.main.pokedex_entry_ressource_layout.view.*

class InitialPokedexViewHolder(view: View, context:Context): RecyclerView.ViewHolder(view) {
    private val context = context
    fun bind(singleSinglePokemonPokedex: SinglePokemonRetrofitPokedex) {
        if (singleSinglePokemonPokedex != null) {
            itemView.pokemonNamePokedexRessourceTextView.text = singleSinglePokemonPokedex.name
            Glide.with(context).load(singleSinglePokemonPokedex.sprite).into(itemView.pokemonSpritePokedexImageViewRessource)
            itemView.pokemonLayout.setOnClickListener {
                val view = itemView.context as FragmentActivity
                val bundle = Bundle()
                bundle.putInt("PokemonId",singleSinglePokemonPokedex.id)
                val fragment = SinglePokemonFragment()
                fragment.arguments = bundle
                view.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, context: Context): InitialPokedexViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pokedex_entry_ressource_layout, parent, false)
            return InitialPokedexViewHolder(view, context)
        }
    }
}