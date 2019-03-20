package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.location.LocationManager
import java.io.Serializable

interface MapFragmentManager : Serializable {
    public fun setMarkerPosition(): MutableList<Double>
}