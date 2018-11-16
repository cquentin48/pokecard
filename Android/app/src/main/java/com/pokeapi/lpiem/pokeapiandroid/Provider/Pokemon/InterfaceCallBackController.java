package com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon;

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit.PokemonRetrofit;

public interface InterfaceCallBackController<T>  {
    public void onWorkDone();
    public void showPokemon(PokemonRetrofit p);
}
