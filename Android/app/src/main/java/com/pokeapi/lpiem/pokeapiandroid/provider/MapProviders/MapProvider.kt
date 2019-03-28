package com.pokeapi.lpiem.pokeapiandroid.provider.MapProviders

import android.os.AsyncTask
import com.pokeapi.lpiem.pokeapiandroid.view.MapFragmentManager

class MapProvider : AsyncTask<MapFragmentManager, Void, Int>() {
    override fun doInBackground(vararg params: MapFragmentManager): Int {
       params[0].setMarkerPosition()
       return 0
    }

}