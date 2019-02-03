package com.pokeapi.lpiem.pokeapiandroid.View

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
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
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.pokeapi.lpiem.pokeapiandroid.R

class MapScreenFragment : Fragment() , MapFragment.OnFragmentInteractionListener, LocationListener {
    private lateinit var latituteField: TextView
    private lateinit var longitudeField: TextView
    private lateinit var detailsfield: TextView


    private var lat: Double = 0.toDouble()
    private var lon: Double = 0.toDouble()
    override fun onFragmentInteraction(uri: Uri) {

    }

    lateinit var mMapFragment:MapFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.activity_localization, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        latituteField = findViewById(R.id.latitude) as TextView
        longitudeField = findViewById(R.id.longitude) as TextView



        // Initialize the location fields
        if (location != null) {
            println("Provider $provider has been selected.")
            onLocationChanged(location)
        } else {
            latituteField!!.text = "Location not available"
            longitudeField!!.text = "Location not available"
        }





        mMapFragment  = MapFragment.newInstance(lat,lon)
        addFragment()
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        lat = location.latitude
        lon = location.longitude
        latituteField!!.text = "latitude: " + lat.toString()
        longitudeField!!.text = "longitude: " +lon.toString()
        //val detailgetter = DetailsGetter()
        // detailsfield!!.text = detailgetter.getData()
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // TODO Auto-generated method stub

    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText(this, "Enabled new provider $provider",
                Toast.LENGTH_SHORT).show()

    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText(this, "Disabled provider $provider",
                Toast.LENGTH_SHORT).show()
    }



    fun addFragment() {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        ft.add(R.id.frgContainer, mMapFragment)
        ft.commit()
    }

}