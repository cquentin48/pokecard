package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import android.R
import android.location.LocationManager
import android.provider.Settings
import android.view.View
import kotlinx.android.synthetic.main.activity_main_app.*


class MainAppActivity : AppCompatActivity(), InterfaceCallBackController<Any> {
    private var singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()
    var Singleton: AppProviderSingleton = singleton!!
        get() = this.singleton!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pokeapi.lpiem.pokeapiandroid.R.layout.activity_main_app)
        buttonListener()
    }

    private fun buttonListener(){
        pokedexButton.setOnClickListener {
            val intent = Intent(this, PokedexListView::class.java)
            startActivity(intent)
        }
    }

    override fun addPokemonSpecies(i: Int, s: Species) {
        singleton!!.pokemonList!!.get(i).PokemonSpecies = s
    }

    override fun addPokedexEntry(i: Int, p: String) {
        singleton!!.pokemonList!!.get(i).PokemonPokedexEntry = p
    }

    override fun addPokemonToList(p: PokemonRetrofit) {
        Singleton!!.addPokemonToList(PokemonData(p.name!!,
                p.species!!,
                p.typeList!!.toMutableList(),
                p.id))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            com.pokeapi.lpiem.pokeapiandroid.R.id.mapOrPokedexMenu ->{
                val intent = Intent(this,PokedexListView::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(com.pokeapi.lpiem.pokeapiandroid.R.menu.main_menu, menu)
        return true
    }

    fun goToLocalization(view:View) {

        val service = getSystemService(LOCATION_SERVICE) as LocationManager
        val enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!enabled) run {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        else {
            val myIntent = Intent(this, LocalizationActivity::class.java)
            startActivity(myIntent)
        }
    }
}
