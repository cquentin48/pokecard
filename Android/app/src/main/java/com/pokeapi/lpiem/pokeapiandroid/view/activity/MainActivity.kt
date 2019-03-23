package com.pokeapi.lpiem.pokeapiandroid.view.activity

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
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.auth.AuthUI
import com.pokeapi.lpiem.pokeapiandroid.provider.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.View.Fragment.PokedexListView
import com.pokeapi.lpiem.pokeapiandroid.View.LocalizationActivity
import com.pokeapi.lpiem.pokeapiandroid.ViewModel.MainMenuViewModel
import com.pokeapi.lpiem.pokeapiandroid.view.fragment.PokedexListView
import com.pokeapi.lpiem.pokeapiandroid.view.MapScreenFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var pokedexListView: PokedexListView
    private lateinit var pokeMap: MapScreenFragment
    private lateinit var pokeMap: LocalizationActivity
    private var viewModel: MainMenuViewModel = MainMenuViewModel()

    /**
     * Set up fragments
     */
    private fun setUpFragment(){
        pokedexListView = PokedexListView()
        pokeMap = MapScreenFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGraphicalElements()
        setUpFragment()
        navigationDrawerItemManagment()
    }

    /**
     * Managing toolbar attached to navigationView
     */
    private fun managingToolbar() {
        initActionBar()
        viewModel.managingToolbar(supportActionBar as ActionBar)
    }

    /**
     * Initialisation of the action bar
     */
    private fun initActionBar() {
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
        navigationViewUsername.text = if(AppProviderSingleton.User.displayName == "") AppProviderSingleton.User.email else AppProviderSingleton.User.displayName
        val defaultString = getString(R.string.default_photo_url)
        Glide.with(this@MainActivity).
                load(if(AppProviderSingleton.User.photoUrl.toString() == "")defaultString else AppProviderSingleton.User.photoUrl.toString())
                .apply(RequestOptions().override(300, 300).circleCrop())
                .into(navigationViewUserProfileImage)
    }

    /**
     * Manage actions for the items within the navigation view
     */
    private fun navigationDrawerItemManagment() {
        mDrawerLayout = findViewById(R.id.drawer_layout)
        viewModel.managingNavigationElements(this@MainActivity,navigationView,supportFragmentManager)
        /*navigationView.setNavigationItemSelectedListener { menuItem ->
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
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, pokeMap).commit()
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
        }*/
        managingToolbar()
    }

    private fun initGraphicalElements(){
        initDrawerLayout()
        initActionBar()
    }

    private fun initDrawerLayout() {
        viewModel.BackgroundDrawerLayout = drawer_layout
    }

    /**
     * Display a toast about a menu not been implemented
     */
    private fun displayToastNotYetImplemented() {
        Toast.makeText(this, getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()
    }

    /**
     * Logging-out session function
     */
    private fun loggingOut(){
        viewModel.loggingOut(this@MainActivity)
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

  /*  fun goToLocalization(view:View) {

        val service = getSystemService(LOCATION_SERVICE) as LocationManager
        val enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!enabled) run {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        else {
          //  val myIntent = Intent(this, LocalizationActivity::class.java)
           // startActivity(myIntent)
        }
    }*/
}
