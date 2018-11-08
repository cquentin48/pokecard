package com.pokeapi.lpiem.pokeapiandroid.Provider;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.FacebookProfile;
import com.pokeapi.lpiem.pokeapiandroid.View.MainActivity;

import org.json.JSONArray;

public class FacebookApiProvider implements FacebookApiInterface {
    private CallbackManager callbackManager;

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    @Override
    public void logout() {
        LoginManager.getInstance().logOut();
    }

    @Override
    public Object getData(LoginResult loginResult, AccessToken accessToken) {
        final FacebookProfile facebookProfile = new FacebookProfile();
        GraphRequest request = GraphRequest.newMyFriendsRequest(
                accessToken,
                new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray array, GraphResponse response) {
                        MainActivity am = new MainActivity();
                        am.launchActivity();
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,id");
        request.setParameters(parameters);
        request.executeAsync();
        return null;
    }

    @Override
    public boolean isConnected() {
        if(AccessToken.getCurrentAccessToken() != null){
            return true;
        }else{
            return false;
        }
    }

    public FacebookApiProvider(){
        this.callbackManager = CallbackManager.Factory.create();
    }
}
