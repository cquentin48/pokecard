
package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import com.pokeapi.lpiem.pokeapiandroid.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.adapter_header.*

class ProfileAdapterHeader(private val sectionTitle:String): Item(), ExpandableItem{
    private lateinit var expandableGroup: ExpandableGroup
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.profileSectionLetter.text = sectionTitle
        viewHolder.headerExpandableIconProfile.setImageResource(getRotatedIconResId())

        viewHolder.headerExpandableIconProfile.setOnClickListener{
            expandableGroup.onToggleExpanded()
            viewHolder.headerExpandableIconProfile.setImageResource(getRotatedIconResId())
        }
    }

    override fun getLayout(): Int = R.layout.adapter_header_profile

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    private fun getRotatedIconResId() = if(expandableGroup.isExpanded){
        R.drawable.ic_keyboard_arrow_down_black_24dp
    }else{
        R.drawable.ic_keyboard_arrow_up_black_24dp
    }
}