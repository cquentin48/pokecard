package com.pokeapi.lpiem.pokeapiandroid.Application;

import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton;
import com.twitter.sdk.android.core.Twitter;

public class PokeApplication extends Application {
    private AppProviderSingleton singleton;

    @Override
    public void onCreate(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Twitter.initialize(this);
        singleton = AppProviderSingleton.getInstance();
        super.onCreate();
    }
}
