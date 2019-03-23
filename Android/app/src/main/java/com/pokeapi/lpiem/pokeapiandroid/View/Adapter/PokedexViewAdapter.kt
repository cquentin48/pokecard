package com.pokeapi.lpiem.pokeapiandroid.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pokeapi.lpiem.pokeapiandroid.Model.Enum.LoadingState
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonRetrofit

class PokedexViewAdapter(private val retry: () -> Unit, context:Context)
    : PagedListAdapter<PokemonRetrofit, RecyclerView.ViewHolder>(pokemonDiffCallBack){
    private val DATA_VIEW_TYPE = 1
    private val LOADING_DATA = 2
    private var state = LoadingState.LOADING
    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) InitialPokedexViewHolder.create(parent, context) else SinglePokemonErrorViewHolder.create(retry, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as InitialPokedexViewHolder).bind(getItem(position)!!)
        else (holder as SinglePokemonErrorViewHolder).bind(state)
    }
    companion object {
        val pokemonDiffCallBack = object : DiffUtil.ItemCallback<PokemonRetrofit>() {
            override fun areItemsTheSame(oldItem: PokemonRetrofit, newItem: PokemonRetrofit): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PokemonRetrofit, newItem: PokemonRetrofit): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else LOADING_DATA
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == LoadingState.LOADING || state == LoadingState.ERROR)
    }

    fun setState(state: LoadingState) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}