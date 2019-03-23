package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.View.Activity.PokedexPokemonView
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.pokedex_item.*

class PokedexItem(private val pokemonSpriteUrl:String, private val pokemonName:String, context: Context): Item(){
    private val context = context
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.item_fancy_cardView.setCardBackgroundColor(Color.WHITE)
        viewHolder.pokemonNamePokedex.text = pokemonName
        Glide.with(context).load(pokemonSpriteUrl).into(viewHolder.pokemonSprite)
    }

    /**
     * Get the layout used by the groupie item
     */
    override fun getLayout(): Int = R.layout.pokedex_item

    /**
     * Setting how many items will be put on a single line
     * @param spanCount number of pokemon
     * @param position current position of the item -> not used here
     * @return number of items per line
     */
    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 3

}