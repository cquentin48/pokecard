package com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel.ProfileFragmentAdapterModel
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

    fun countElements(profileItem: ProfileFragmentAdapterModel, requiredValue: Any, liveData: MutableLiveData<HashMap<String, String>>){
        FirebaseDatabaseSingleton.userRef.child(AppProviderSingleton.getInstance().User.uid).child(profileItem.databaseEntry).addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e("Error",databaseError.message)
                        Log.e("Error",databaseError.details)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var numberOfElements = 0
                        dataSnapshot.children.forEach {
                            if (it.equals(requiredValue)) {
                                numberOfElements++
                            }
                        }
                        liveData.value!![dataSnapshot.key!!] = "${dataSnapshot.key} : $numberOfElements"
                        liveData.postValue(liveData.value)
                    }

                }
        )
    }

    fun initUser(userId: String, user: Profile) {
        if(isUserAlreadyPresent(userId)){
            userRef.child(userId).setValue(user)
        }
    }

    fun isUserAlreadyPresent(userId: String): Boolean {
        return userRef.child(userId) != null
    }

    fun getElement(ref:DatabaseReference, typeObject:Int, liveData: MutableLiveData<HashMap<String,String>>){
        ref.addValueEventListener(
                object:ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        showErrorMessage(databaseError)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(typeObject == 0){
                            liveData.value!![dataSnapshot.key.toString()] = "${dataSnapshot.key} : ${dataSnapshot.getValue(String::class.java)}"
                            liveData.postValue(liveData.value)
                        }else{
                            liveData.value!![dataSnapshot.key.toString()] = "${dataSnapshot.key} : ${dataSnapshot.getValue(Long::class.java).toString()}"
                            liveData.postValue(liveData.value)
                        }
                    }
                }
        )
    }
}