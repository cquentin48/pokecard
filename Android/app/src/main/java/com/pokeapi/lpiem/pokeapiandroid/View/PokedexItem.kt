package com.pokeapi.lpiem.pokeapiandroid.View

import androidx.annotation.ColorInt
import com.pokeapi.lpiem.pokeapiandroid.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class PokedexItem(private val pokemonSpriteUrl:String, private val pokemonName:String): Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
    }

    override fun getLayout(): Int = R.layout.item_fancy

    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 3

}