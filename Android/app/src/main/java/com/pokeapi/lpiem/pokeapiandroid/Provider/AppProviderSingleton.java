package com.pokeapi.lpiem.pokeapiandroid.Provider;

import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.FacebookApiProvider;
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.GoogleApiProvider;
import com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks.TwitterApiProvider;

public class AppProviderSingleton {
    public final static int FACEBOOK = 1;
    public final static int TWITTER = 2;
    public final static int GOOGLE = 3;
    public final static int POKEAPI = 4;

    private static AppProviderSingleton instance;
    private FacebookApiProvider facebookApiProvider;
    private GoogleApiProvider googleApiProvider;
    private TwitterApiProvider twitterApiProvider;

    public static AppProviderSingleton getInstance(){
        if(instance == null){
            instance = new AppProviderSingleton();
        }
        return instance;
    }

    private AppProviderSingleton() {
        facebookApiProvider = new FacebookApiProvider();
    }

    public FacebookApiProvider getFacebookApiProvider() {
        return facebookApiProvider;
    }

    public void setFacebookApiProvider(FacebookApiProvider facebookApiProvider) {
        this.facebookApiProvider = facebookApiProvider;
    }

    public GoogleApiProvider getGoogleApiProvider() {
        return googleApiProvider;
    }

    public void setGoogleApiProvider(GoogleApiProvider googleApiProvider) {
        this.googleApiProvider = googleApiProvider;
    }

    public TwitterApiProvider getTwitterApiProvider() {
        return twitterApiProvider;
    }

    public void setTwitterApiProvider(TwitterApiProvider twitterApiProvider) {
        this.twitterApiProvider = twitterApiProvider;
    }
}
