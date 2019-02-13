package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import android.location.LocationManager
import android.provider.Settings
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.activity_main_app.*




class MainAppActivity : AppCompatActivity(){
    private var singleton: AppProviderSingleton?= AppProviderSingleton.getInstance()
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var pokedexListView: PokedexListView
    private lateinit var pokeMap: LocalizationActivity

    /**
     * Set up fragments
     */
    private fun setUpFragment(){
        pokedexListView = PokedexListView()
        pokeMap = LocalizationActivity()

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
                    startActivity(Intent(this, LocalizationActivity::class.java))
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

        //Toast.makeText(this,singleton!!.Profile.Username,Toast.LENGTH_LONG).show()

    }

    private fun loggingOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    startActivity(Intent(this, MainActivity::class.java))
                }
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
