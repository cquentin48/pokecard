package com.pokeapi.lpiem.pokeapiandroid.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.MainMenuViewModel
import com.pokeapi.lpiem.pokeapiandroid.view.fragment.PokedexListView
import com.pokeapi.lpiem.pokeapiandroid.view.MapScreenFragment
import com.pokeapi.lpiem.pokeapiandroid.view.fragment.CollectionFragment
import com.pokeapi.lpiem.pokeapiandroid.view.fragment.LaboratoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var pokedexListView: PokedexListView
    private lateinit var pokeMap: MapScreenFragment
    private lateinit var craftingFragment: LaboratoryFragment
    private lateinit var collectionsFragment: CollectionFragment
    private var viewModel: MainMenuViewModel = MainMenuViewModel()

    /**
     * Set up fragments
     */
    private fun setUpFragment(){
        pokedexListView = PokedexListView()
        pokeMap = MapScreenFragment()
        craftingFragment = LaboratoryFragment()
        collectionsFragment = CollectionFragment()
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
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
        managingToolbarSetting()
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

    /**
     * Managing toolbar attached to navigationView
     */
    private fun managingToolbarSetting() {
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_settings_black_24dp)
        }
    }

    private fun updateNavigationHeader(){
        (this.navigationView.getHeaderView(0).findViewById(R.id.userNameNavigationView) as TextView).text = viewModel.getUsername()
        Glide.with(this@MainActivity).
                load(viewModel.getImageURL(this@MainActivity))
                .apply(RequestOptions().override(300, 300).circleCrop())
                .into(this.navigationView.getHeaderView(0).findViewById(R.id.userProfileNavigationImage) as ImageView)
    }

    fun setActionBarTitle(title:String){
        supportActionBar!!.title = title
    }

    /**
     * Manage actions for the items within the navigation view
     */
    private fun navigationDrawerItemManagment() {
        mDrawerLayout = drawer_layout
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
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, pokeMap).commit()
                }
                R.id.profile -> {
                    displayToastNotYetImplemented()
                }
                R.id.crafting -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,craftingFragment).commit()
                }
                R.id.collections ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, collectionsFragment).commit()
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

    private fun initGraphicalElements(){
        initActionBar()
    }

    /**
     * Display a toast about a menu not been implemented
     */
    private fun displayToastNotYetImplemented() {
        viewModel.displayToastNotYetImplemented(this@MainActivity)
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
