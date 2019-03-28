package com.pokeapi.lpiem.pokeapiandroid.view

import java.io.Serializable

interface MapFragmentManager : Serializable {
    public fun setMarkerPosition(): MutableList<Double>
}