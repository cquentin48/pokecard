package com.pokeapi.lpiem.pokeapiandroid.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.PokemonFirebase
import com.pokeapi.lpiem.pokeapiandroid.view.fragment.SinglePokemonFragment
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.CollectionsViewModel
import kotlinx.android.synthetic.main.pokemon_collection_res_layout.view.*
import java.util.*

class CollectionsAdapter(val context: Context, val data:ArrayList<PokemonFirebase>): RecyclerView.Adapter<CollectionsAdapter.GenericInfosPokemonViewHolder>() {
    private var viewModel = CollectionsViewModel()
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
        holder.nickname.text = data[position].nickname
        holder.creationDate.text = data[position].creationDate.substring(0,10)
    }

    inner class GenericInfosPokemonViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val sprite = itemView.singlePokemonCollectionSprite!!
        val nickname = itemView.pokemonCollectionNickname!!
        val creationDate = itemView.pokemonCollectionCreationDate!!
        val layout = itemView.singlePokemonCollectionLayout
    }
}