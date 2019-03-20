package com.pokeapi.lpiem.pokeapiandroid.View

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model.PokemonData
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.Species
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import com.firebase.ui.auth.AuthUI
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.activity_main_app.*
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions




class MainAppActivity : AppCompatActivity(), InterfaceCallBackController<Any>, MapFragment.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    /* override fun getContext(): Context {
         return this as Context
     }

     override fun getContextLocation(): LocationManager {
       //  return getSystemService(Context.LOCATION_SERVICE) as LocationManager
     }

     override fun checkForPermission(): Boolean {
       //  return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
     }

     override fun getCoord() {
         //todoo
     }*/

    private var singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var pokedexListView: PokedexListView
    private lateinit var pokeMap: MapScreenFragment

    var Singleton: AppProviderSingleton
        get() = this.singleton!!
        set(value){
            this.singleton = value
        }

    /**
     * Set up fragments
     */
    private fun setUpFragment(){
        pokedexListView = PokedexListView()
        pokeMap = MapScreenFragment()

        pokedexListView.passContext(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        setUpFragment()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            // set item as selected to persist highlight
            when(menuItem.itemId){

                R.id.pokedexMenu ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,pokedexListView).commit()
                }
                R.id.pokeMap ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,pokeMap).commit()
                }
                R.id.profile ->{
                    Toast.makeText(this,getString(R.string.NotYetImplemented),Toast.LENGTH_LONG).show()
                }
                R.id.collections ->{
                    Toast.makeText(this,getString(R.string.NotYetImplemented),Toast.LENGTH_LONG).show()
                }
                R.id.options ->{
                    Toast.makeText(this,getString(R.string.NotYetImplemented),Toast.LENGTH_LONG).show()

                }
                R.id.about ->{
                    Toast.makeText(this,getString(R.string.NotYetImplemented),Toast.LENGTH_LONG).show()

                }
                R.id.logOut ->{
                    loggingOut()
                }
            }
            // close drawer when item is tapped

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

        updateNavigationHeader()

    }

    private fun updateNavigationHeader(){
        val navigationView = navigationView
        val headerView = navigationView.getHeaderView(0)
        val navigationViewUsername = headerView.findViewById(R.id.userNameNavigationView) as TextView
        val navigationViewUserProfileImage = headerView.findViewById(R.id.userProfileNavigationImage) as ImageView
        navigationViewUsername.text = singleton!!.fetchDisplayName()

        singleton!!.displayAvatar(this@MainAppActivity,navigationViewUserProfileImage)
    }

    private fun loggingOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    startActivity(Intent(this, MainActivity::class.java))
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
        when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

  /*  fun goToLocalization(view:View) {

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
    }*/
}
