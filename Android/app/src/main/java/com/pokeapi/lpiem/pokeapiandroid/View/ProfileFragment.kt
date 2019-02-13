package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton

import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
import java.util.*

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
        myFragmentView = inflater.inflate(R.layout.activity_pokedex_list_view,container,false)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProfileFragment()
    }

    private fun initProfileFragment() {
        initProfileBasicInformations()
        initResumeSection()
    }

    private fun initProfileBasicInformations() {
        initTextView(usernameTextViewProfile,
                singleton.User.displayName!!,
                R.drawable.ic_contacts_black_24dp)

        initTextView(emailProfileTextView,
                singleton.User.email!!,
                R.drawable.ic_contact_mail_black_24dp)

        initTextView(registrationDateProfileTextView,
                SimpleDateFormat("dd/MM/yyyy").format(Date(singleton.User.metadata!!.creationTimestamp)),
                R.drawable.ic_perm_contact_calendar_black_24dp)

        Glide
                .with(applicationContext)
                .load(singleton!!.User.photoUrl)
                .apply(RequestOptions().override(300, 300).circleCrop())
                .into(avatarImageView)
    }

    private fun initTextView(textView: TextView, text:String, ressourceId:Int) {
        textView.text = text
        textView.setCompoundDrawablesWithIntrinsicBounds(ressourceId, 0, 0, 0)
    }

    private fun initResumeSection(){

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
