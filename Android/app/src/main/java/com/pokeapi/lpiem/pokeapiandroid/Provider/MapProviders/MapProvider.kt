package com.pokeapi.lpiem.pokeapiandroid.Provider.MapProviders

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Parcel
import android.os.Parcelable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pokeapi.lpiem.pokeapiandroid.View.MapFragment
import com.pokeapi.lpiem.pokeapiandroid.View.MapFragmentManager
import java.io.Serializable

class MapProvider : AsyncTask<MapFragmentManager, Void, Int>() {
    override fun doInBackground(vararg params: MapFragmentManager): Int {
       params[0].setMarkerPosition()
       return 0
    }

}