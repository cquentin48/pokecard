package com.pokeapi.lpiem.pokeapiandroid.Provider.MapProviders

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pokeapi.lpiem.pokeapiandroid.View.MapFragment
import com.pokeapi.lpiem.pokeapiandroid.View.MapFragmentManager
import java.io.Serializable

class MapProvider(var activity: MapFragmentManager): Serializable {
    private var mActivity : MapFragmentManager = activity
    private lateinit var locationManager: LocationManager
    private lateinit var provider: String
    private lateinit var providers: List<String>

    fun getLocation() {
        locationManager = activity.getContextLocation()

        val criteria = Criteria()
        providers = locationManager!!.allProviders
        //provider = locationManager.getBestProvider(criteria, false);
        provider = "network"

        if (ActivityCompat.checkSelfPermission(mActivity.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mActivity.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
    }




}