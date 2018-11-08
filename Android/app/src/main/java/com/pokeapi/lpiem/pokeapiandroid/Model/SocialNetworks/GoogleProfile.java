package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks;

import java.net.URI;

public class GoogleProfile {
    //Pseudo
    private String username;
    //E-mail
    private String email;
    //Prénom
    private String firstName;
    //Nom de famille
    private String familyName;
    //Surnom
    private String givenName;
    //Identifiant
    private String id;
    //URI du compte
    private URI accountURI;

    public GoogleProfile(String username, String email, String firstName, String familyName, String givenName, String id, URI accountURI) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.familyName = familyName;
        this.givenName = givenName;
        this.id = id;
        this.accountURI = accountURI;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public URI getAccountURI() {
        return accountURI;
    }

    public void setAccountURI(URI accountURI) {
        this.accountURI = accountURI;
    }
}
