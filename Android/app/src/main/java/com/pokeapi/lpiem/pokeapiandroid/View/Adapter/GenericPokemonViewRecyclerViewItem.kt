package com.pokeapi.lpiem.pokeapiandroid.view.adapter
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.model.adaptermodel.PokedexBasicInfosAdapter
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.GenericPokemonViewRecyclerViewItem.GenericInfosPokemonViewHolder
import kotlinx.android.synthetic.main.general_info_pokemon_recyclerview_layout.view.*

class GenericPokemonViewRecyclerViewItem(private val data:PokedexBasicInfosAdapter, private val context: Context):RecyclerView.Adapter<GenericInfosPokemonViewHolder>(){
    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericInfosPokemonViewHolder =
            GenericInfosPokemonViewHolder(LayoutInflater.from(context).inflate(R.layout.general_info_pokemon_recyclerview_layout,parent,false))

    override fun getItemCount(): Int {
        return data.infos.size
    }

    override fun onBindViewHolder(holder: GenericInfosPokemonViewHolder, position: Int) {
        holder.label.text = data.infos[position].label
        holder.value.text = data.infos[position].value
    }

    inner class GenericInfosPokemonViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val label = itemView.categoryIcon
        val value = itemView.categoryValue
    }
}