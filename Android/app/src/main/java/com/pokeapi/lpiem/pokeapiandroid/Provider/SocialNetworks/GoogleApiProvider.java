package com.pokeapi.lpiem.pokeapiandroid.Provider.SocialNetworks;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.pokeapi.lpiem.pokeapiandroid.View.MainActivity;

public class GoogleApiProvider implements GoogleApiInterface {
    private GoogleSignInClient googleSignInClient;

    @Override
    public void logOut() {

    }

    @Override
    public Object getData() {
        return null;
    }

    public void initGoogleApi(){
        googleSignInClient = MainActivity.initGoogleSignInApi();
    }

    public GoogleSignInClient getGoogleSignInClient(){
        return this.googleSignInClient;
    }
}
