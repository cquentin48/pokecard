package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.Profile
import kotlinx.android.synthetic.main.fragment_profile.*

object FirebaseDatabaseSingleton {

    private val principalElementList = listOf("username", "lastUserConnection", "registrationDate")
    private val arrayElementList = listOf("pokemonCollection", "friendList", "achievmentList")

    val database = FirebaseDatabase.getInstance()
    val userRef = database.getReference("users")
    val achievementList = database.getReference("achievments")
    val messageList = database.getReference("messages")


    fun setUpTextView(textView: TextView, elementType: String) {
        userRef.child(AppProviderSingleton.getInstance().User.uid).addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        textView.text = dataSnapshot.child(elementType).value.toString()
                    }
                }
        )
    }

    fun settingUpImageView(imageView: ImageView, fragmentContext: Context, reference: DatabaseReference) {
        reference.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        Glide
                                .with(fragmentContext)
                                .load(p0.getValue(String::class.java))
                                .apply(RequestOptions().override(300, 300).circleCrop())
                                .into(imageView)
                    }

                }
        )
    }

    private fun addElementToList(dataList: HashMap<String, String>, reference: DatabaseReference, key: String) {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("Error", p0.message)
            }

            override fun onDataChange(data: DataSnapshot) {
                dataList[key] = data.getValue(String::class.java)!!
            }
        })
    }

    private fun countElements(reference: DatabaseReference, requiredValue: Any): Int {
        var numberOfElements = 0
        reference.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        p0.children.forEach {
                            if (it.equals(requiredValue)) {
                                numberOfElements++
                            }
                        }
                    }

                }
        )
        return numberOfElements
    }

    fun initUser(userId: String, user: Profile) {
        userRef.child(userId).setValue(user)
    }

    fun isUserAlreadyPresent(userId: String): Boolean {
        return if (userRef.child(userId) == null) true else false
    }
}