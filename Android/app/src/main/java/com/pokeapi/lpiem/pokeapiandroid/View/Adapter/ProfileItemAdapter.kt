package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.profile_item.view.*

class ProfileItemAdapter(val items: ArrayList<String>, val context:Context): RecyclerView.Adapter<ProfileItemAdapter.ViewHolder>() {
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
        /*Glide.with(context).load(items[position].imageViewURL).into(holder.imageViewIcon)*/
        holder.description.text = "${items[position]}"/* : ${items[position].elementDescription}"*/
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var imageViewIcon = itemView.profileSectionItemIcon
        var description = itemView.profileSectionDetail
    }
}
