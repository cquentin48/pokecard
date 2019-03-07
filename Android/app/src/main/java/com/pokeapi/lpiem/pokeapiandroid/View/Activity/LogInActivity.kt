package com.pokeapi.lpiem.pokeapiandroid.View.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.firebase.ui.auth.AuthUI

import com.google.firebase.auth.FirebaseAuth
import com.pokeapi.lpiem.pokeapiandroid.Provider.AppProviderSingleton
import kotlinx.android.synthetic.main.activity_log_in.*
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import android.widget.Toast
import com.pokeapi.lpiem.pokeapiandroid.Provider.FirebaseSingleton
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.ViewModel.LoginModelView

const val RC_SIGN_IN = 1

class LogInActivity : AppCompatActivity() {
    private lateinit var singleton: AppProviderSingleton
    private val viewModel: LoginModelView = LoginModelView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        this.title = getString(R.string.poke_card_log_in_title)
        this.singleton = AppProviderSingleton
        viewModel.initFirebaseAuthentification(this)
        checkUser()
        signIn()
    }

    /**
     * Build a signIn Session
     */
    private fun signIn(){
        signInButton.setOnClickListener {
            val providers = arrayListOf(
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.FacebookBuilder().build(),
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
     * Manage after logIn session
     * @param requestCode which action have been used
     * @param resultCode result code of the signIn
     * @param data result data from the signIn
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        checkUser()
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (isLogInSuccessfull(resultCode)) {
                manageSuccessfullLogIn()
            } else {
                if (response == null) {
                    return manageError(R.string.no_internet_connection)
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    return manageError(R.string.no_internet_connection)
                }

                Log.e(getString(R.string.error_tag), getString(R.string.sign_in_error_prefix), response.error)
            }
        }else{
            Log.e(getString(R.string.error_tag), "$requestCode $resultCode")
        }
    }

    /**
     * Display error message if the signIn has failed
     * @param errorMessage id key for the string message error in the string.xml file
     */
    private fun manageError(errorMessage: Int) {
        Toast.makeText(this@LogInActivity, errorMessage, Toast.LENGTH_LONG).show()
        return
    }

    /**
     * If the logIn has been successfull
     * @param resultCode result code of the sign In
     * @param boolean check if the user has successfully signed In
     */
    private fun isLogInSuccessfull(resultCode: Int) = resultCode == Activity.RESULT_OK

    /**
     * Function manager if the logIn has been successfull
     */
    private fun manageSuccessfullLogIn() {
        checkUser()
        finish()
    }

    /**
     * Launch mainActivity after successfull login
     */
    private fun checkUser() {
        if(FirebaseAuth.getInstance().currentUser!=null){
            FirebaseSingleton.firebaseUser = FirebaseAuth.getInstance().currentUser!!
            startActivity(Intent(this@LogInActivity, MainActivity::class.java))
        }
    }
}
