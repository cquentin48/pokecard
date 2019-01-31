package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import com.pokeapi.lpiem.pokeapiandroid.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.adapter_header.*

class AdapterHeader(private val letterString:String): Item(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.letter.text = letterString
        viewHolder.headerExpandableIcon.setImageResource(getRotatedIconResId())

        viewHolder.headerExpandableIcon.setOnClickListener{
            expandableGroup.onToggleExpanded()
            viewHolder.headerExpandableIcon.setImageResource(getRotatedIconResId())
        }
    }

    override fun getLayout(): Int = R.layout.adapter_header

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    private fun getRotatedIconResId() = if(expandableGroup.isExpanded){
        R.drawable.ic_keyboard_arrow_down_black_24dp
    }else{
        R.drawable.ic_keyboard_arrow_up_black_24dp
    }

}