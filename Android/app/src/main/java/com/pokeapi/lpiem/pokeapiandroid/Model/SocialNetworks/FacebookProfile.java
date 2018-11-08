package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks;

import java.util.ArrayList;
import java.util.List;

public class FacebookProfile {
    private int userId;
    private String firstName;
    private String lastName;

    private List<FacebookProfile> userList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<FacebookProfile> getUserList() {
        return userList;
    }

    public FacebookProfile(int userId, String firstName, String lastName, List<FacebookProfile> userList) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userList = userList;
    }

    public FacebookProfile(){
        this.userId = 0;
        this.firstName = "";
        this.lastName = "";
        this.userList = new ArrayList<>();
    }
}
