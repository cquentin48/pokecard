package com.pokeapi.lpiem.pokeapiandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokeapi.lpiem.pokeapiandroid.model.enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.pokedex_pagination_loading_layout.view.*

class SinglePokemonErrorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: LoadingState?) {
        itemView.pokemonSpriteLoadingProgressBar.visibility = if (status == LoadingState.LOADING) VISIBLE else INVISIBLE
        itemView.pokemonLoadingText.visibility = if (status == LoadingState.ERROR) VISIBLE else INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): SinglePokemonErrorViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pokedex_pagination_loading_layout, parent, false)
            view.pokemonLoadingText.setOnClickListener { retry() }
            return SinglePokemonErrorViewHolder(view)
        }
    }
}