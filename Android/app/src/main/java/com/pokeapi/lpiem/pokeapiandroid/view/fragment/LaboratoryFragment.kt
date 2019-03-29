package com.pokeapi.lpiem.pokeapiandroid.view.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer

import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.view.activity.MainActivity
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.CraftingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_laboratory.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LaboratoryFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LaboratoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LaboratoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var viewModel: CraftingFragmentViewModel = CraftingFragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun setTitle(){
        val activity = activity as MainActivity
        activity.setActionBarTitle(getString(R.string.fragmentLaboTitle))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_laboratory, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    private fun showOrHideElements(visibility:Int){
        pokemonSpriteImage.visibility = visibility
        surnamePokemon.visibility = visibility
        pokemonNameCreated.visibility = visibility
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTitle()
        viewModel.loadData()
        initBothSpinners()
        manageButtons()
        showOrHideElements(View.INVISIBLE)
    }

    private fun razSpinners(){
        firstTypeSpinner.setSelection(0)
        secondTypeSpinner.setSelection(0)
    }

    private fun onRAZButtonClickListener(){
        razSettingCraftFragment.setOnClickListener {
            razSpinners()
        }
    }

    private fun manageButtons(){
        onRAZButtonClickListener()
    }

    private fun initBothSpinners(){
        initSpinnerData(firstTypeSpinner)
        initSpinnerData(secondTypeSpinner)
    }


    private fun initSpinnerData(spinner: Spinner){
        viewModel.getTypesData().observe(this, Observer {
            val adapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, it.typeList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LaboratoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                LaboratoryFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
