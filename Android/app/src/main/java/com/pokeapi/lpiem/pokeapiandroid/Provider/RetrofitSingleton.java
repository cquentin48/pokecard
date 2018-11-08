package com.pokeapi.lpiem.pokeapiandroid.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.PokemonAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static PokemonAPI instance = null;
    public final static String POKEMONBASEURL = "https://pokeapi.co/";

    public static PokemonAPI getInstance(){
        if(instance == null){
            instance = buildInstance();
        }
        return instance;
    }

    private static PokemonAPI buildInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(POKEMONBASEURL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        PokemonAPI pokemonAPI = retrofit.create(PokemonAPI.class);
        return pokemonAPI;
    }

    private RetrofitSingleton(){

    }
}
