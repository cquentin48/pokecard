package com.pokeapi.lpiem.pokeapiandroid.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import com.firebase.ui.auth.AuthUI

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import kotlinx.android.synthetic.main.activity_main.*
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import android.widget.Toast
import com.pokeapi.lpiem.pokeapiandroid.R


class MainActivity : AppCompatActivity() {
    private var singleton: AppProviderSingleton? = AppProviderSingleton.getInstance()
    private var context: Context? = null
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.context = this
        this.title = "PokeCard - Connexion à l'application"
        this.singleton = AppProviderSingleton.getInstance()
        FirebaseApp.initializeApp(this@MainActivity)
        mAuth = FirebaseAuth.getInstance(FirebaseApp.initializeApp(this@MainActivity)!!)
        startActivity()
        signIn()
    }

    private fun signIn(){
        signInButton.setOnClickListener {
            val providers = arrayListOf(
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.FacebookBuilder().build(),
                    AuthUI.IdpConfig.TwitterBuilder().build(),
                    AuthUI.IdpConfig.EmailBuilder().build())

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setTheme(R.style.firebaseAuthUI)
                            .setLogo(R.drawable.walkemon_logo)
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        /*val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.login_menu, menu)*/
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {
                startActivity()
                finish()
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this@MainActivity,R.string.sign_in_cancelled,Toast.LENGTH_LONG).show()
                    return
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this@MainActivity,R.string.no_internet_connection,Toast.LENGTH_LONG).show()
                    return
                }

                Toast.makeText(this@MainActivity,R.string.unknown_error,Toast.LENGTH_LONG).show()
                Log.e("Error", "Sign-in error: ", response.error)
            }
        }
    }

    private fun startActivity() {
        if(FirebaseAuth.getInstance().currentUser!=null){
            singleton!!.User = FirebaseAuth.getInstance().currentUser!!
            startActivity(Intent(this@MainActivity, MainAppActivity::class.java))
        }
    }

    companion object {
        val RC_SIGN_IN = 1
        private var context: Context? = null
    }
}
