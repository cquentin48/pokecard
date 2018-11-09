package com.pokeapi.lpiem.pokeapiandroid.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.pokeapi.lpiem.pokeapiandroid.Application.PokeApplication;
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.FacebookProfile;
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton;
import com.pokeapi.lpiem.pokeapiandroid.R;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private AppProviderSingleton singleton;
    public final static int RC_SIGN_IN = 1;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        this.setTitle("PokeCard - Connexion à l'application");
        this.singleton = AppProviderSingleton.getInstance();


        this.initGoogleLogInButton();
        //this.initFacebookLogInButton();
        this.initTwitterLogInButton();
    }

    public void initFacebookLogInButton(){
        CallbackManager callbackManager = this.singleton.getFacebookApiProvider().getCallbackManager();
        LoginButton loginButton = findViewById(R.id.facebookLoginButton);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final FacebookProfile facebookProfile = new FacebookProfile();
                GraphRequest request = GraphRequest.newMyFriendsRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONArrayCallback() {
                            @Override
                            public void onCompleted(JSONArray array, GraphResponse response) {
                                //Ajouter ici le retour des données pour l'ajouter dans le contrôleur pour le réseau social facebook
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name,id");
                request.setParameters(parameters);
                request.executeAsync();
                Intent intent = new Intent(MainActivity.this,MainAppActivity.class);
            }

            @Override
            public void onCancel() {
                Log.d("Facebook", "Connexion annulée");
            }

            @Override
            public void onError(FacebookException exception) {
                exception.printStackTrace();
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(MainActivity.this, MainAppActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void initTwitterLogInButton(){

    }

    public void initGoogleLogInButton(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        SignInButton signInButton = findViewById(R.id.googleLoginButton);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(MainActivity.this, MainAppActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google", "signInResult:failed code=" + e.getStatusCode());
            e.printStackTrace();
        }
    }

    public void launchActivity(){
        Intent intent = new Intent(MainActivity.this,MainAppActivity.class);
        startActivity(intent);
    }

    public static GoogleSignInClient initGoogleSignInApi(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, gso);
        return googleSignInClient;
    }
}
