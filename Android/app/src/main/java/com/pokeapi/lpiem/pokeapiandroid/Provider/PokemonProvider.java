package com.pokeapi.lpiem.pokeapiandroid.Provider;


import android.util.Log;

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Forms;
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Pokemon;
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.PokemonAPI;
import com.pokeapi.lpiem.pokeapiandroid.Provider.Pokemon.InterfaceCallBackController;
import com.pokeapi.lpiem.pokeapiandroid.View.MainAppActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonProvider implements Callback<Pokemon> {
    private Pokemon pokemonInput;
    private String pokemonName;
    private int nextId = 1;
    private String message;
    private InterfaceCallBackController interfaceCallBackController;

    public void start(final MainAppActivity googleConnexionResult){

        final PokemonAPI pokemonAPI = RetrofitSingleton.getInstance();

        Call<Pokemon> callPokemon = pokemonAPI.getPokemonById(6);

        callPokemon.enqueue(new Callback<Pokemon>() {

            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
//                Log.d("Reponse","Réponse");
                if(response.isSuccessful()){
                    Pokemon pokemonResulted = response.body();
                    pokemonName = "Nom du pokémon : " + pokemonResulted.getName();
                    setPokemon(pokemonResulted);
                    googleConnexionResult.showPokemon(pokemonResulted);
                }else{
                    Log.d("Erreur", "Erreur de connexion");
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("Erreur",t.getLocalizedMessage());
                t.printStackTrace();
            }
        });
//        Log.d("Nom du pokémon",this.pokemonName+"");
    }

    private void getNextPokemon(){
        /*Call<Pokemon>nextPokemon = this.interfaceAPI.getPokemonById(nextId);
        nextPokemon.enqueue(this);*/
    }

    public void setPokemon(Pokemon poke){
        this.pokemonInput = poke;
    }

    public Pokemon getPokemonInput() {
        return pokemonInput;
    }

    private synchronized void fetchData(Response<Pokemon> response) {
        Pokemon rawPokemon = response.body();

        List<Forms> formList = rawPokemon.getFormsList();
        message += "list people : \n\n";
        // changesList.forEach(people -> System.out.println(people.name));  // lambda expression (enable java 1.8 in project structure  - available only since AP 24...
        Log.d("Nom du pokémon",rawPokemon.getName());
        interfaceCallBackController.onWorkDone(true);
    }


    @Override
    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
        if(response.isSuccessful()){
            fetchData(response);
        }else{
            System.out.println("Erreur : "+response.errorBody());
            Log.d("Erreur",response.errorBody()+"");
        }
    }

    @Override
    public void onFailure(Call<Pokemon> call, Throwable t) {
        t.printStackTrace();
    }

    public PokemonProvider(Pokemon result) {
        this.pokemonInput = result;
    }

    public PokemonProvider(){
        this.pokemonInput = new Pokemon();
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }
}

