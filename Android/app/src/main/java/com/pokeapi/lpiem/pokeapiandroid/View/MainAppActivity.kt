package com.pokeapi.lpiem.pokeapiandroid.View

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R


class MainAppActivity : AppCompatActivity(), InterfaceCallBackController<Any>{
    private var singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()
    private lateinit var mDrawerLayout: DrawerLayout

    var Singleton: AppProviderSingleton = singleton!!
        get() = this.singleton!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        mDrawerLayout = findViewById(R.id.drawer_layout)

        //singleton!!.getPokeApiInfos(this)

        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_settings_black_24dp)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
