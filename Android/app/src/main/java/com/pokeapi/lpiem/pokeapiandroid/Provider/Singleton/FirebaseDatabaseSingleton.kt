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
                    override fun onCancelled(databaseError: DatabaseError) {
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
                    override fun onCancelled(databaseError: DatabaseError) {
                        showErrorMessage(databaseError)
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

    fun moveElementToAnotherPlace(originalPath : DatabaseReference, newPath: DatabaseReference){
        originalPath.addValueEventListener(
                object : ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        showErrorMessage(databaseError)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        newPath.setValue(dataSnapshot.value) { databaseError, _ ->
                            if(databaseError == null){
                                Log.d("Information", "Success")
                            }else{
                                showErrorMessage(databaseError)
                            }
                        }
                    }

                }
        )
    }

    private fun addElementToList(dataList: HashMap<String, String>, reference: DatabaseReference, key: String) {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                showErrorMessage(databaseError)
            }

            override fun onDataChange(data: DataSnapshot) {
                dataList[key] = data.getValue(String::class.java)!!
            }
        })
    }

    private fun showErrorMessage(databaseError: DatabaseError) {
        Log.e("Error", databaseError.message)
        Log.e("Error", databaseError.details)
    }

    fun countElements(reference: DatabaseReference, requiredValue: Any): Int {
        var numberOfElements = 0
        reference.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e("Error",databaseError.message)
                        Log.e("Error",databaseError.details)
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
        if(isUserAlreadyPresent(userId)){
            userRef.child(userId).setValue(user)
        }
    }

    fun isUserAlreadyPresent(userId: String): Boolean {
        return if (userRef.child(userId) == null) true else false
    }

    fun initArrayList(context: Context, elementList:List<String>){

    }

    fun getElement(ref:DatabaseReference, typeObject:Int):String{
        var element = ""
        ref.addValueEventListener(
                object:ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        showErrorMessage(databaseError)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(typeObject == 0){
                            Log.d("Element : "+dataSnapshot.key,dataSnapshot.getValue(String::class.java))
                            element = "${dataSnapshot.key} : ${dataSnapshot.getValue(String::class.java)}"
                        }else{
                            Log.d("Element : "+dataSnapshot.key,dataSnapshot.getValue(Long::class.java).toString())
                            element = "${dataSnapshot.key} : ${dataSnapshot.getValue(Long::class.java).toString()}"
                        }
                    }
                }
        )
        return element
    }
}