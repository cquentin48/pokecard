package com.pokeapi.lpiem.pokeapiandroid.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController
import com.pokeapi.lpiem.pokeapiandroid.R

class MainAppActivity : AppCompatActivity(), InterfaceCallBackController<Any> {
    override fun onWorkDone() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
    }

    override fun showPokemon(p: PokemonRetrofit) {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
