package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager


import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.pokeapi.lpiem.pokeapiandroid.R
import java.io.Serializable
import com.facebook.appevents.codeless.internal.UnityReflection.sendMessage
import android.R.attr.bitmap
import android.R
import android.os.Message


class MapFragment : Fragment() {
    private var ARG_PARAM1:String = "latitude"
    private var ARG_PARAM2 :String = "longitude"
    private var ARG_PARAM3 :String = "screenLocalization"
    // TODO: Rename and change types of parameters
    private var latitude: Double = 0.toDouble()
    private var longitude: Double = 0.toDouble()
    private lateinit var screenLocalization: MapFragmentManager
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var marker: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            latitude = it.getDouble(ARG_PARAM1)
            longitude = it.getDouble(ARG_PARAM2)
            screenLocalization = it.getSerializable(ARG_PARAM3) as MapFragmentManager
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.frg) as SupportMapFragment?  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() //clear old markers

            val googlePlex = CameraPosition.builder()
                    .target(LatLng(latitude, longitude))
                    .zoom(19f)
                    .bearing(0f)
                    .tilt(70f)
                    .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2500, null)

            marker = mMap.addMarker(MarkerOptions()
                    .position(LatLng(latitude, longitude))
                    .title("Moi"))
        }

       update()


        // Inflate the layout for this fragment
        return rootView
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters

        fun newInstance(latitude: Double, longitude: Double, screenLocalization: Serializable) =
                MapFragment().apply {
                    arguments = Bundle().apply {
                        putDouble(ARG_PARAM1, latitude)
                        putDouble(ARG_PARAM2, longitude)
                        putSerializable(ARG_PARAM3, screenLocalization)
                    }
                }
    }

    fun update(){
        var mThread = Thread(Runnable {
            Thread.sleep(5000)
            var position: MutableList<Double> = screenLocalization.setMarkerPosition()

            val msg: Message = handler.obtainMessage()
            msg.what = UPDATE_IMAGE
            msg.obj = bitmap
            msg.arg1 = index
            handler.sendMessage(msg)

            var runOnUiThread = Runnable {
                marker.position = (LatLng(position[0], position[1]))
            }
            update()
        }).start()

    }


}
