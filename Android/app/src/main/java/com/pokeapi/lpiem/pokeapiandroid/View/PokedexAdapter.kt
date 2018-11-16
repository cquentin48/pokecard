package com.pokeapi.lpiem.pokeapiandroid.View

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.R
import android.support.v7.widget.RecyclerView.ViewHolder



class PokedexAdapter : BaseAdapter() {
    val listPokemon:HashMap<Int, PokemonData>?= null
    val layoutInflater:LayoutInflater?= null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("Index",position.toString())
        var holder:ViewHolder
        if(convertView == null){
            holder = ViewHolder()
            val newConvertedView:View = layoutInflater!!.inflate(R.layout.pokedex_entry_ressource_layout,parent)
            holder.pokemonSpriteImageView!!
            holder.pokemonNameTextView!!.setText(null)
            holder.pokemonTypeSpritesImageView!!
        }else{
            holder = convertView.tag as ViewHolder
        }
    }

    override fun getItem(position: Int): PokemonData? {
        return listPokemon!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listPokemon!!.size
    }

    private class ViewHolder{
        var pokemonSpriteImageView: ImageView?= null
        var pokemonNameTextView: TextView?= null
        var pokemonTypeSpritesImageView: ImageView?= null
    }
}