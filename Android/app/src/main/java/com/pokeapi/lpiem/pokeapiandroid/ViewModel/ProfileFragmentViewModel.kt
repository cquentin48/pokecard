package com.pokeapi.lpiem.pokeapiandroid.ViewModel

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.FirebaseDatabaseSingleton

class ProfileFragmentViewModel {
    var infoList:MutableLiveData<ArrayList<String>> = MutableLiveData()
    fun initMainInfos(context: Context, usernameTextView: TextView, lastUserConnection:TextView, registrationDateProfileTextView:TextView, avatarImageView:ImageView){
        FirebaseDatabaseSingleton.setUpTextView(usernameTextView,"username")
        FirebaseDatabaseSingleton.setUpTextView(lastUserConnection,"lastUserConnection")
        FirebaseDatabaseSingleton.setUpTextView(registrationDateProfileTextView,"registrationDate")
        FirebaseDatabaseSingleton.settingUpImageView(avatarImageView,
                context,
                FirebaseDatabaseSingleton.userRef.child(AppProviderSingleton.getInstance().User.uid).child("avatarImage"))
    }

    private fun initCompleteMainInfos(){
        val loadingList = listOf("registrationDate","countingfriendList","countingachievmentList","distance")
        val elementList = arrayListOf<String>()
        loadingList.forEach {
            if(it.contains("counting")){
                elementList.add("Compteur : "+FirebaseDatabaseSingleton.countElements(FirebaseDatabaseSingleton.userRef.
                        child(AppProviderSingleton.getInstance().User.uid).child(it.replace("counting","")),
                        true).toString())
            }else{
                elementList.add(FirebaseDatabaseSingleton.getElement(FirebaseDatabaseSingleton.userRef.child(AppProviderSingleton.getInstance().User.uid).child(it),getElementTypeById(it)))
            }
        }
        infoList.postValue(elementList)
    }

    fun initOtherSections(selectedSection:Int){
        when(selectedSection){
            0->initCompleteMainInfos()
            else->initCompleteMainInfos()
        }
    }

    fun getElementTypeById(ref:String):Int{
        return if(ref == "registrationDate") 0 else 1
    }

    fun initRecyclerView(){
        infoList.value = arrayListOf()
    }
}