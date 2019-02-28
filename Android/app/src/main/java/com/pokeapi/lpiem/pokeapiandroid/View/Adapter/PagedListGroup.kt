package com.pokeapi.lpiem.pokeapiandroid.View.Adapter

import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.kotlinandroidextensions.Item
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.ListUpdateCallback
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.paging.AsyncPagedListDiffer
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.annotation.NonNull
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class PagedListGroup<T : Item> : Group, GroupDataObserver{
    private lateinit var parentObserver:GroupDataObserver
    private lateinit var placeHolder:Item

    private val listUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {
            parentObserver.onItemRangeInserted(this@PagedListGroup, position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            parentObserver.onItemRangeRemoved(this@PagedListGroup, position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            parentObserver.onItemMoved(this@PagedListGroup, fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            parentObserver.onItemRangeChanged(this@PagedListGroup, position, count)
        }
    }

    private val differ = AsyncPagedListDiffer(
            listUpdateCallback,
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                    return newItem.isSameAs(oldItem)
                }

                override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                    return newItem.equals(oldItem)
                }
            }).build()
    )

    override fun getItemCount(): Int {
        return differ.itemCount
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): com.xwray.groupie.Item<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPosition(item: com.xwray.groupie.Item<*>): Int {
        val currentList = differ.currentList ?: return -1
        //noinspection SuspiciousMethodCalls
        return currentList.indexOf(item)
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChanged(group: Group) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemRangeRemoved(group: Group, positionStart: Int, itemCount: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemInserted(group: Group, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemRemoved(group: Group, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemChanged(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver.onItemChanged(this, index)
        }
    }

    override fun onItemChanged(group: Group, position: Int, payload: Any?) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver.onItemChanged(this, index, payload)
        }
    }

    override fun onItemRangeInserted(group: Group, positionStart: Int, itemCount: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemMoved(group: Group, fromPosition: Int, toPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemRangeChanged(group: Group, positionStart: Int, itemCount: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemRangeChanged(group: Group, positionStart: Int, itemCount: Int, payload: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getItemPosition(@NonNull group: Group): Int {
        val currentList = differ.currentList ?: return -1

        return currentList.indexOf(group)
    }
}