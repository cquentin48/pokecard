package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.R
import com.squareup.picasso.Picasso

class PokedexAdapter(newContext:Context, newListPokemon : List<PokemonData>) : BaseAdapter() {
    private var listPokemon:List<PokemonData> = newListPokemon
    private var layoutInflater:LayoutInflater?= LayoutInflater.from(newContext)
    private var context: Context?=newContext

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("Index",position.toString())
        var holder:ViewHolder
        if(convertView == null){
            holder = ViewHolder()
            val newConvertedView:View = layoutInflater!!.inflate(R.layout.pokedex_entry_ressource_layout,parent)

            holder.pokemonNameTextView = convertView!!.findViewById(R.id.pokemonSpriteImageView);
            holder.pokemonTypeSpritesImageView = convertView!!.findViewById(R.id.pokemonNameTextView);
            holder.pokemonTypeSpritesImageView = convertView!!.findViewById(R.id.pokemonTypesTextView);

            holder.pokemonNameTextView!!.setText(listPokemon!!.get(position)!!.pokemonName)
            holder.pokemonTypeSpritesImageView!!

            Picasso.with(context).load(listPokemon.get(position)!!.pokemonSprite).into(holder.pokemonSpriteImageView)

            return newConvertedView
        }else{
            holder = convertView.tag as ViewHolder
            return convertView
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