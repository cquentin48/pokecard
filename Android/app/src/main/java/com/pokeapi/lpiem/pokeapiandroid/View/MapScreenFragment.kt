package com.pokeapi.lpiem.pokeapiandroid.View

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.pokeapi.lpiem.pokeapiandroid.Model.UserApp
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.activity_localization.*


class MapScreenFragment : Fragment() , MapFragment.OnFragmentInteractionListener, LocationListener {

    private lateinit var detailsfield: TextView
    private var lat: Double = 0.toDouble()
    private var lon: Double = 0.toDouble()
    private lateinit var provider: String
    private lateinit var providers: List<String>
    private lateinit var locationManager: LocationManager
    private var isCreate: Boolean = false
    override fun onFragmentInteraction(uri: Uri) {
//todo
    }



    lateinit var mMapFragment:MapFragment



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_localization, container, false)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationManager = context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        providers = locationManager!!.allProviders
        //provider = locationManager.getBestProvider(criteria, false);
        provider = "network"

        if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMapFragment  = MapFragment.newInstance(10.0,10.0)
            addFragment()
            return
        }
        val location = locationManager!!.getLastKnownLocation(provider)



        // Initialize the location fields
        if (location != null) {
            println("Provider $provider has been selected.")
            onLocationChanged(location)
        } else {
            latitude.text = "Location not available"
            longitude.text = "Location not available"
        }


        lat = location.latitude
        lon = location.longitude



        mMapFragment  = MapFragment.newInstance(lat,lon)
        addFragment()
        isCreate = true
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(activity as Context , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager!!.requestLocationUpdates(provider, 400, 1f, this)
    }

    override fun onPause() {
        super.onPause()
        locationManager!!.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        if(isCreate) {
            UserApp.lat = location.latitude
            lat = location.latitude
            lon = location.longitude
            latitude.text = "latitude: " + lat.toString()
            longitude.text = "longitude: " + lon.toString()
        }
        //val detailgetter = DetailsGetter()
        // detailsfield!!.text = detailgetter.getData()
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // TODO Auto-generated method stub

    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText(context, "Enabled new provider $provider",
                Toast.LENGTH_SHORT).show()

    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText(context, "Disabled provider $provider",
                Toast.LENGTH_SHORT).show()
    }



    fun addFragment() {
        val manager = activity!!.supportFragmentManager
        val ft = manager.beginTransaction()
        ft.add(R.id.frgContainer, mMapFragment)
        ft.commit()
    }

}