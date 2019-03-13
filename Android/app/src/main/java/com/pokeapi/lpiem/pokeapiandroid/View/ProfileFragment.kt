package com.pokeapi.lpiem.pokeapiandroid.View

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton

import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.View.Adapter.ProfileItemAdapter
import com.pokeapi.lpiem.pokeapiandroid.ViewModel.ProfileFragmentViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var myFragmentView: View
    private lateinit var applicationContext: Context
    private val singleton:AppProviderSingleton = AppProviderSingleton.getInstance()
    private var selectedSection : Int = 0
    private var profileFragmentViewModel = ProfileFragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun passContext(context: Context){
        applicationContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        myFragmentView = inflater.inflate(R.layout.fragment_profile,container,false)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.title = activity!!.getString(R.string.ProfileTitleFragment)+" "+ singleton.User.displayName
        initProfileFragment()
    }

    private fun initProfileFragment() {
        initProfileBasicInformations()
        initResumeSection()
        Toast.makeText(activity,if(singleton!!.isNewUser())"Nouvel utilisateur" else "Ancien utilisateur",Toast.LENGTH_LONG).show()
    }

    private fun initProfileBasicInformations() {
        profileFragmentViewModel.initMainInfos(applicationContext,usernameTextViewProfile, lastUserConnectionDate, registrationDateProfileTextView, avatarImageView)
    }


    private fun initTextView(textView: TextView, text:String, ressourceId:Int) {
        textView.text = text
        textView.setCompoundDrawablesWithIntrinsicBounds(ressourceId, 0, 0, 0)
    }

    private fun initResumeSection(){
        otherInformationsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        otherInformationsRecyclerView.adapter = ProfileItemAdapter(profileFragmentViewModel.initOtherSections(selectedSection),applicationContext)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
