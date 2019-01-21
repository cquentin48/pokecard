package com.pokeapi.lpiem.pokeapiandroid.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuInflater

import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.FacebookProfile
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R

class MainActivity : AppCompatActivity() {
    private var singleton: AppProviderSingleton? = AppProviderSingleton.getInstance()
    private var context:Context?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.context = this
        this.title = "PokeCard - Connexion à l'application"
        this.singleton = AppProviderSingleton.getInstance()

        this.initGoogleLogInButton()
        this.initFacebookLogInButton()
        this.initTwitterLogInButton()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.login_menu, menu)
        return true
    }

    fun initFacebookLogInButton() {
        val callbackManager = this.singleton!!.facebookApiProvider!!.callbackManager
        val loginButton = findViewById<LoginButton>(R.id.facebookLoginButton)
        loginButton.setReadPermissions("email")

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val facebookProfile = FacebookProfile()
                val request = GraphRequest.newMyFriendsRequest(
                        AccessToken.getCurrentAccessToken()
                ) { _, _ ->
                    //Ajouter ici le retour des données pour l'ajouter dans le contrôleur pour le réseau social facebook
                }

                val parameters = Bundle()
                parameters.putString("fields", "first_name,last_name,id")
                request.parameters = parameters
                request.executeAsync()
                val intent = Intent(this@MainActivity, MainAppActivity::class.java)
            }

            override fun onCancel() {
                Log.d("Facebook", "Connexion annulée")
            }

            override fun onError(exception: FacebookException) {
                exception.printStackTrace()
            }
        })

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                launchActivity()
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }
        })
    }

    fun initTwitterLogInButton() {

    }

    fun initGoogleLogInButton() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        val signInButton = findViewById<SignInButton>(R.id.googleLoginButton)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val intent = Intent(this@MainActivity, MainAppActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google", "signInResult:failed code=" + e.statusCode)
            e.printStackTrace()
        }

    }

    fun launchActivity() {
        val intent = Intent(this@MainActivity, MainAppActivity::class.java)
        startActivity(intent)
    }

    companion object {
        val RC_SIGN_IN = 1
        private var context: Context? = null

        fun initGoogleSignInApi(): GoogleSignInClient {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
            return GoogleSignIn.getClient(context!!, gso)
        }
    }
}
