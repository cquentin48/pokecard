package com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon;

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Pokemon;

public interface InterfaceCallBackController<T>  {
    public void onWorkDone(T result);
    public void showPokemon(Pokemon p);
}
