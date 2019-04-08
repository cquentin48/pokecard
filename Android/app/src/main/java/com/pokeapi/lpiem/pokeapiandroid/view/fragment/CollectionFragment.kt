package com.pokeapi.lpiem.pokeapiandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.FirebaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.view.activity.MainActivity
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.CollectionsAdapter
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.CollectionsViewModel
import kotlinx.android.synthetic.main.fragment_collection.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CollectionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CollectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CollectionFragment : Fragment() {
    val viewModel = CollectionsViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initGraphicalElements()
    }

    private fun initGraphicalElements(){
        setTitle()
        initPokemonList()
    }

    private fun setTitle(){
        (this.activity as MainActivity).setActionBarTitle(getString(R.string.collectionFragmentTitle))
    }

    private fun loadData(){
        viewModel.loadData(FirebaseSingleton.firebaseUser.uid)
    }

    private fun initPokemonList(){
        viewModel.getCollectionMutableLiveData().observe(this, Observer {
            collectionsRecyclerView.layoutManager = GridLayoutManager(context,3)
            collectionsRecyclerView.adapter = CollectionsAdapter(context!!,it)
        })
    }
}
