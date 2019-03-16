package com.pokeapi.lpiem.pokeapiandroid.View

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel.ProfileSubFragmentRetrofit

import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.no_element_subfragment.view.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileFragmentItem.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * create an instance of this fragment.
 *
 */
@SuppressLint("ValidFragment")
class ProfileFragmentItem : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var fragmentView:View
    lateinit var activityContext:Context
    lateinit var rawData:ProfileSubFragmentRetrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(if(rawData.success)R.layout.no_element_subfragment else R.layout.fragment_profile_fragment_item, container, false)
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentItems()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * Init profileSubFragmentData
     */
    private fun initFragmentItems(){
        if(!rawData.success){
            initErrorFragment()
        }else{
            //Lancer ici la pagination
        }
    }

    private fun initErrorFragment(){
        fragmentView.noContentDescription.text = rawData.errorMessageDescription
        fragmentView.noContentTitle.text = rawData.errorMessageTitle
        Glide.with(activityContext).load(rawData.errorImageURL).into(fragmentView.noContentLogo)
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
}
