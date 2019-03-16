package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.FirebaseDatabaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.profile_item.view.*

class ProfileItemAdapter(val items: HashMap<String,String>, val context:Context): RecyclerView.Adapter<ProfileItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.profile_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProfileItemAdapter.ViewHolder, position: Int) {
        Log.d("Information","${items.keys.elementAt(position)}")
        holder.label.text = "${items.keys.elementAt(position)}"
        holder.value.text = "${items[items.keys.elementAt(position)]}"
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageViewIcon = itemView.profileSectionItemIcon
        val value = itemView.profileSectionValue
        val label = itemView.profileSectionLabel
    }
}
