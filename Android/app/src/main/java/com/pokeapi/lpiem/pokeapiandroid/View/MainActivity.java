package com.pokeapi.lpiem.pokeapiandroid.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton;
import com.pokeapi.lpiem.pokeapiandroid.R;

public class MainActivity extends AppCompatActivity {
    private AppProviderSingleton singleton;
    public final static int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.singleton = AppProviderSingleton.getInstance();


        this.initGoogleLogInButton();
        this.initFacebookLogInButton();
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
                singleton.getFacebookApiProvider().getData(loginResult,AccessToken.getCurrentAccessToken());

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
    }

    public void initTwitterLogInButton(){

    }

    public void initGoogleLogInButton(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account!= null){
            Intent intent = new Intent(MainActivity.this,MainAppActivity.class);
            startActivity(intent);
        }else{
            SignInButton signInButton = findViewById(R.id.googleLoginButton);
            signInButton.setSize(SignInButton.SIZE_STANDARD);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build();
                    GoogleSignInClient mGoogleSignInClient;
                    mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent,RC_SIGN_IN);
                }
            });
        }
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
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void launchActivity(){
        Intent intent = new Intent(MainActivity.this,MainAppActivity.class);
        startActivity(intent);
    }
}
