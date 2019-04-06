package com.pokeapi.lpiem.pokeapiandroid.provider.singleton

import com.google.firebase.database.FirebaseDatabase
import com.pokeapi.lpiem.pokeapiandroid.model.firebase.PokemonCollection
import java.text.SimpleDateFormat
import java.util.*

object FirebaseDatabaseSingleton {
    private val database = FirebaseDatabase.getInstance().reference

    /**
     * Add pokemon to collection list in firebase
     * @param nickname nickname chosen by the user
     */
    fun addPokemonToCollection(nickname: String){
        database.child("collections")
                .child(FirebaseSingleton.firebaseUser.uid)
                .child(CraftingSingleton.pokemonId.toString())
                .push()
                .setValue(generatePokemon(nickname))
    }

    /**
     * Generate pokemon data in order to send it to firebase
     * @param nickname nickname chosen by the user
     */
    private fun generatePokemon(nickname: String):PokemonCollection{
        return PokemonCollection(SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date()),nickname)
    }
}