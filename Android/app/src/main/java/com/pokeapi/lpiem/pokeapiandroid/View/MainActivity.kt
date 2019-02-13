package com.pokeapi.lpiem.pokeapiandroid.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.firebase.ui.auth.AuthUI

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import kotlinx.android.synthetic.main.activity_log_in.*
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import android.widget.Toast
import com.pokeapi.lpiem.pokeapiandroid.R
const val RC_SIGN_IN = 1

class LogInActivity : AppCompatActivity() {
    private var singleton: AppProviderSingleton? = AppProviderSingleton.getInstance()
    private var context: Context? = null
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        this.context = this
        this.title = getString(R.string.poke_card_log_in_title)
        this.singleton = AppProviderSingleton.getInstance()
        FirebaseApp.initializeApp(this@LogInActivity)
        mAuth = FirebaseAuth.getInstance(FirebaseApp.initializeApp(this@LogInActivity)!!)
        startActivity()
        signIn()
    }

    /**
     * Sign in session managment
     */
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

    /**
     * Result of the sign in intent
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
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
                    Toast.makeText(this@LogInActivity,R.string.sign_in_cancelled,Toast.LENGTH_LONG).show()
                    return
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this@LogInActivity,R.string.no_internet_connection,Toast.LENGTH_LONG).show()
                    return
                }

                Toast.makeText(this@LogInActivity,R.string.unknown_error,Toast.LENGTH_LONG).show()
                Log.e("Error", getString(R.string.sign_in_error_prefix), response.error)
            }
        }
    }

    /**
     * Launch mainActivity after successfull login
     */
    private fun startActivity() {
        if(FirebaseAuth.getInstance().currentUser!=null){
            singleton!!.User = FirebaseAuth.getInstance().currentUser!!
            startActivity(Intent(this@LogInActivity, MainAppActivity::class.java))
        }
    }
}
