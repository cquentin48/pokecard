package com.pokeapi.lpiem.pokeapiandroid.Provider;

public class AppProviderSingleton {
    public final static int FACEBOOK = 1;
    public final static int TWITTER = 2;
    public final static int GOOGLE = 3;
    public final static int POKEAPI = 4;

    private static AppProviderSingleton instance;
    private FacebookApiProvider facebookApiProvider;

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
}
