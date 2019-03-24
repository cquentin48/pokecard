package com.pokeapi.lpiem.pokeapiandroid.view.adapter
import android.content.Context
import android.graphics.Color
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.pokedex_item.*

class SinglePokemonViewItem(private val dataType:Int, context:Context): Item(){
    private val context = context
    override fun bind(viewHolder: ViewHolder, position: Int) {
        when(dataType){
            0->
        }
    }

    /**
     * Get the layout used by the groupie item
     */
    override fun getLayout(): Int{
        return when(dataType){
            0->
            1->
        }
    }

    /**
     * Setting how many items will be put on a single line
     * @param spanCount number of pokemon
     * @param position current position of the item -> not used here
     * @return number of items per line
     */
    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 3

}