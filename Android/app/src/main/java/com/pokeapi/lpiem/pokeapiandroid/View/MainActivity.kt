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
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.GoogleProfile
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    private var singleton: AppProviderSingleton? = AppProviderSingleton.getInstance()
    private var context:Context?= null
    private var connectionType = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.context = this
        this.title = "PokeCard - Connexion à l'application"
        this.singleton = AppProviderSingleton.getInstance()

        this.initGoogleLogInButton()
        this.initFacebookLogInButton()
        //this.initTwitterLogInButton()
        this.loginPokeApiButton()
    }

    private fun loginPokeApiButton(){
        connectWithPokeAccount.setOnClickListener {
            startActivity(Intent(this, MainAppActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.login_menu, menu)
        return true
    }

    fun initFacebookLogInButton() {
        val callbackManager = this.singleton!!.facebookApiProvider!!.callbackManager
        val loginButton = facebookLoginButton


        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                startActivity(Intent(this@MainActivity, MainAppActivity::class.java))
            }

            override fun onCancel() {
                Log.d("Facebook", "Connexion annulée")
            }

            override fun onError(exception: FacebookException) {
                Log.e("Erreur",exception.localizedMessage)
            }
        })
    }

    fun initGoogleLogInButton() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        getGoogleProfileInfos()
        if(account != null){
            //startActivity(Intent(this,MainAppActivity::class.java))
        }
        val signInButton = findViewById<SignInButton>(R.id.googleLoginButton)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener {
            connectionType = 0
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        singleton!!.facebookApiProvider!!.callbackManager.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun getGoogleProfileInfos(){
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            singleton!!.Profile = GoogleProfile(
                    acct.email,
                    acct.displayName,
                    acct.familyName,
                    acct.givenName,
                    acct.id,
                    acct.photoUrl)
        }
        //startActivity(Intent(this, MainAppActivity::class.java))
    }

    private fun isFacebookAccountSignedIn():Boolean{
        val accessToken = AccessToken.getCurrentAccessToken()
        return accessToken != null && !accessToken.isExpired
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            getGoogleProfileInfos()
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
