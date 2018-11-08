package com.pokeapi.lpiem.pokeapiandroid.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toolbar;

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Pokemon;
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController;
import com.pokeapi.lpiem.pokeapiandroid.R;

public class MainAppActivity extends AppCompatActivity implements InterfaceCallBackController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        Toolbar toolbar = findViewById(R.id.toolbarMainMenu);
        //this.setSupportActionBar(toolbar);
    }

    @Override
    public void onWorkDone(Object result) {
        //nothing
    }

    @Override
    public void showPokemon(Pokemon p) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu_main_windows, menu);
        return true;
    }
}
