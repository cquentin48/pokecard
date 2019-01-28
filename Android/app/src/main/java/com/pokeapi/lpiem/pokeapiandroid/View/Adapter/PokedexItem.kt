package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import android.content.Context
import android.graphics.Color
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.R
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

    override fun getLayout(): Int = R.layout.pokedex_item

    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 3

}