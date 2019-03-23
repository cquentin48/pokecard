package com.pokeapi.lpiem.pokeapiandroid.ViewModel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.pokeapi.lpiem.pokeapiandroid.Provider.FirebaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.View.Fragment.PokedexListView
import com.pokeapi.lpiem.pokeapiandroid.View.LocalizationActivity

class MainMenuViewModel {
    private lateinit var backgroundDrawerLayout: DrawerLayout
    private var pokedexListView: PokedexListView = PokedexListView()
    private lateinit var pokeMap: LocalizationActivity

    //Getters/Setters
    var BackgroundDrawerLayout:DrawerLayout
    get() = backgroundDrawerLayout
    set(newValue){
        backgroundDrawerLayout = newValue
    }


    /**
     * Managing toolbar attached to navigationView
     */
    fun managingToolbar(actionBar: ActionBar) {
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_settings_black_24dp)
        }
    }

    fun loggingOut(context: Context){
        FirebaseSingleton.loggingOut(context)
    }

    fun managingNavigationElements(context: Context, navigationView:NavigationView, backgroundFragmentManager:FragmentManager){
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            backgroundDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            // set item as selected to persist highlight
            when (menuItem.itemId) {

                R.id.pokedexMenu -> {
                    backgroundFragmentManager.beginTransaction().replace(R.id.fragment_container, pokedexListView).commit()
                }
                R.id.pokeMap -> {
                    context.startActivity(Intent(context, LocalizationActivity::class.java))
                }
                R.id.profile -> {
                    Toast.makeText(context, context.getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()
                }
                R.id.collections -> {
                    Toast.makeText(context, context.getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()
                }
                R.id.options -> {
                    Toast.makeText(context, context.getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()

                }
                R.id.about -> {
                    Toast.makeText(context, context.getString(R.string.not_yet_implemented), Toast.LENGTH_LONG).show()

                }
                R.id.logOut -> {
                    loggingOut(context)
                }
            }
            true
        }
    }
}