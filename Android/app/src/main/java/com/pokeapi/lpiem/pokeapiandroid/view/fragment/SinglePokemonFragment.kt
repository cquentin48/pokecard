package com.pokeapi.lpiem.pokeapiandroid.view.fragment

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.PokemonRetrofitSingleton

import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.view.adapter.GenericPokemonViewRecyclerViewItem
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.SinglePokemonViewModel
import kotlinx.android.synthetic.main.fragment_single_pokemon.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SinglePokemonFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SinglePokemonFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SinglePokemonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var viewModel = SinglePokemonViewModel()
    private var pokemonIdValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        pokemonIdValue = arguments!!.getInt("PokemonId")
        return inflater.inflate(R.layout.fragment_single_pokemon, container, false)
    }

    fun initPokemonSpriteAndName(){
        PokemonRetrofitSingleton.singlePokemonData.observe(this, Observer {
            pokemonId.text = viewModel.loadPokemonId(it)
            pokemonName.text = viewModel.loadPokemonName(it)
            Glide.with(context!!)
                    .load(viewModel.loadPokemonSpriteURL(it))
                    .apply(RequestOptions().override(300, 300).circleCrop())
                    .into(pokemonSprite)
        })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    fun initBasicInfos(){
        initPokemonSpriteAndName()
    }

    @SuppressLint("WrongConstant")
    fun initRecyclerView(){
        pokemonInformations.layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        PokemonRetrofitSingleton.singlePokemonData.observe(this, Observer {
            pokemonInformations.adapter = GenericPokemonViewRecyclerViewItem(viewModel.initBasicInfosData(it))
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData(pokemonIdValue)
        initBasicInfos()
        initRecyclerView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SinglePokemonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SinglePokemonFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
