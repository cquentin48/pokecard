package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks;

import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Achievement;
import com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Pokemon;

import java.net.URI;
import java.util.List;

public class Profile {
    private String username;
    private String email;
    private List<Pokemon> pokemonOwned;
    private List<Achievement> achievementsList;
    private URI avatarImage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pokemon> getPokemonOwned() {
        return pokemonOwned;
    }

    public void setPokemonOwned(List<Pokemon> pokemonOwned) {
        this.pokemonOwned = pokemonOwned;
    }

    public List<Achievement> getAchievementsList() {
        return achievementsList;
    }

    public void setAchievementsList(List<Achievement> achievementsList) {
        this.achievementsList = achievementsList;
    }

    public URI getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(URI avatarImage) {
        this.avatarImage = avatarImage;
    }
}
