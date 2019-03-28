package com.pokeapi.lpiem.pokeapiandroid.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.TypeList
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.CraftingFragmentViewModel
import kotlinx.android.synthetic.main.craft_type_ressource_layout.view.*

class TypeInputCraftAdapter(val typesInput:TypeList, val context:Context): RecyclerView.Adapter<TypeInputCraftAdapter.TypeInputViewHolder>(){
    private var viewModel = CraftingFragmentViewModel()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeInputCraftAdapter.TypeInputViewHolder =
            TypeInputViewHolder(LayoutInflater.from(context).inflate(R.layout.craft_type_ressource_layout,parent,false))

    override fun getItemCount(): Int = typesInput.typeList.size

    override fun onBindViewHolder(holder: TypeInputCraftAdapter.TypeInputViewHolder, position: Int) {
        holder.label.text = "Element n°$position"
        holder.spinner.adapter = ArrayAdapter(context,R.layout.craft_type_ressource_layout, typesInput.typeList)
    }

    class TypeInputViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val label = itemView.typeLabel
        val spinner = itemView.typeSpinner
    }
}