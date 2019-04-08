package com.pokeapi.lpiem.pokeapiandroid.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonFirebase
import kotlinx.android.synthetic.main.pokemon_collection_res_layout.view.*
import java.util.*

class CollectionsAdapter(val context: Context, val data:ArrayList<PokemonFirebase>): RecyclerView.Adapter<CollectionsAdapter.GenericInfosPokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericInfosPokemonViewHolder = GenericInfosPokemonViewHolder(
            LayoutInflater.from(context).inflate(R.layout.pokemon_collection_res_layout,parent,false)
    )

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: GenericInfosPokemonViewHolder, position: Int) {
        Glide.with(context).
                load(data[position].sprite).
                apply(RequestOptions().override(300, 300).circleCrop()).
                into(holder.sprite)
        holder.nickname.text = data[position].surname
    }

    inner class GenericInfosPokemonViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val sprite = itemView.pokemonCollectionSprite!!
        val nickname = itemView.pokemonCollectionNickname!!
    }
}