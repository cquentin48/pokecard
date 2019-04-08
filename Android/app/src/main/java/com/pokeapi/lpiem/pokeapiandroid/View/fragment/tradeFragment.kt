package com.pokeapi.lpiem.pokeapiandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.fragment_trade.*
import com.pokeapi.lpiem.pokeapiandroid.view.activity.MainActivity
import android.widget.ArrayAdapter






class tradeFragment : Fragment(){
    var mListView: ListView? = null

    private val pokemonToTrade = arrayOf("pikachu", "salameche", "elektor")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        mListView = listView

        val adapter = ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, pokemonToTrade)
        mListView?.setAdapter(adapter)

        val rootView = inflater.inflate(R.layout.fragment_trade, container, false)

        return rootView
    }
}