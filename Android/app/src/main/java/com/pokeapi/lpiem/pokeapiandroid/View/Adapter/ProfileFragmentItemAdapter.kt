package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.RetrofitSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.no_element_subfragment.view.*
import kotlinx.android.synthetic.main.profile_list_fragment_item.view.*
import java.util.Observer

class ProfileFragmentItemAdapter(val context:Context):RecyclerView.Adapter<ProfileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
                LayoutInflater.from(context).inflate(
                        if(RetrofitSingleton.infoList.value!!.returnedData.size == 0) R.layout.no_element_subfragment else R.layout.profile_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return RetrofitSingleton.infoList.value!!.returnedData.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        if(itemCount == 0){
            initErrorMessage(holder)
        }else{
            initFriendList(holder,position)
        }
    }

    private fun initErrorMessage(holder: ProfileViewHolder){
        RetrofitSingleton.infoList.observe(this, androidx.lifecycle.Observer {
            holder.errorMessage.text = it.message
            holder.errorMessageTitle.text = it.title
        })
    }

    private fun initFriendList(holder: ProfileViewHolder, position: Int){
        RetrofitSingleton.infoList.observe(this, androidx.lifecycle.Observer {
            holder.username.text = it.returnedData[it.returnedData.keys.elementAt(position)]!!.username
            Glide.with(context).load(it.returnedData[it.returnedData.keys.elementAt(position)]!!.avatarImage)
        })
    }

}

class ProfileViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val errorMessage = itemView.noContentDescription
    val errorMessageTitle = itemView.noContentTitle

    val avatarImageView = itemView.friendAvatar
    val username = itemView.friendUsername
}