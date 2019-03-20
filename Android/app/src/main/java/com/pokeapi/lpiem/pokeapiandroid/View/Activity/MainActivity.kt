package com.pokeapi.lpiem.pokeapiandroid.View.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import android.location.LocationManager
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.View.Fragment.PokedexListView
import com.pokeapi.lpiem.pokeapiandroid.View.LocalizationActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pokedex_pokemon_view.*

class MainActivity : AppCompatActivity(){
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var pokedexListView: PokedexListView
    private lateinit var pokeMap: LocalizationActivity

    /**
     * Set up fragments
     */
    private fun setUpFragment(){
        pokedexListView = PokedexListView()
        pokeMap = LocalizationActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFragment()
        navigationDrawerItemManagment()
    }

    /**
     * Managing toolbar attached to navigationView
     */
    private fun managingToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_settings_black_24dp)
        }
    }


    private fun updateNavigationHeader(){
        val navigationView = navigationView
        val headerView = navigationView.getHeaderView(0)
        val navigationViewUsername = headerView.findViewById(R.id.userNameNavigationView) as TextView
        val navigationViewUserProfileImage = headerView.findViewById(R.id.userProfileNavigationImage) as ImageView
        navigationViewUsername.text = if(AppProviderSingleton.User.displayName == "") AppProviderSingleton.User.email else AppProviderSingleton.User.displayName

        Glide.with(this@MainActivity).load(AppProviderSingleton.User.photoUrl).into(imageView)
    }

    /**
     * Manage actions for the items within the navigation view
     */
    private fun navigationDrawerItemManagment() {
        mDrawerLayout = findViewById(R.id.drawer_layout)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            // set item as selected to persist highlight
            when (menuItem.itemId) {

                R.id.pokedexMenu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, pokedexListView).commit()
                }
                R.id.pokeMap -> {
                    startActivity(Intent(this, LocalizationActivity::class.java))
                }
                R.id.profile -> {
                    displayToastNotYetImplemented()
                }
                R.id.collections -> {
                    displayToastNotYetImplemented()
                }
                R.id.options -> {
                    displayToastNotYetImplemented()

                }
                R.id.about -> {
                    displayToastNotYetImplemented()

                }
                R.id.logOut -> {
                    loggingOut()
                }
            }
            true
        }
        managingToolbar()
    }

    /**
     * Display a toast about a menu not been implemented
     */
    private fun displayToastNotYetImplemented() {
        Toast.makeText(this, getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()
    }

    private fun updateNavigationHeader(){
        val navigationView = navigationView
        val headerView = navigationView.getHeaderView(0)
        val navigationViewUsername = headerView.findViewById(R.id.userNameNavigationView) as TextView
        val navigationViewUserProfileImage = headerView.findViewById(R.id.userProfileNavigationImage) as ImageView
        navigationViewUsername.text = singleton!!.fetchDisplayName()

        singleton!!.displayAvatar(this@MainAppActivity,navigationViewUserProfileImage)
    }

    /**
     * Logging-out session function
     */
    private fun loggingOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    startActivity(Intent(this, LogInActivity::class.java))
                }
    }

    /**
     * Manage action of each item selected
     * @param item which item has been used
     * @return true
     */
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
