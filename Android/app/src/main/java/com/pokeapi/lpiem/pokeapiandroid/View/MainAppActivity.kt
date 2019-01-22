package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R


class MainAppActivity : AppCompatActivity(), InterfaceCallBackController<Any>,NavigationView.OnNavigationItemSelectedListener {
    private var singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()
    private lateinit var mDrawerLayout: DrawerLayout

    var Singleton: AppProviderSingleton = singleton!!
        get() = this.singleton!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        mDrawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }
        singleton!!.getPokeApiInfos(this)
        //initNavigationDrawer()
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

    private fun initNavigationDrawer(){

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when(id){
           R.id.navPokeMap->Toast.makeText(this,"Pas encore implémenté",Toast.LENGTH_LONG).show()
           R.id.navPokedex->startActivity(Intent(this,PokedexPokemonView::class.java))
           R.id.navProfile->Toast.makeText(this,"Pas encore implémenté",Toast.LENGTH_LONG).show()
           R.id.navExchange-> Toast.makeText(this,"Pas encore implémenté",Toast.LENGTH_LONG).show()
           R.id.navSignOut->startActivity(Intent(this,MainActivity::class.java))
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
