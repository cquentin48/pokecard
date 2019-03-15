package com.pokeapi.lpiem.pokeapiandroid.ViewModel

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.R
import com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel.ProfileFragmentAdapterModel
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.AppProviderSingleton
import com.pokeapi.lpiem.pokeapiandroid.Provider.Singleton.FirebaseDatabaseSingleton

class ProfileFragmentViewModel {
    var infoList = MutableLiveData<HashMap<String,String>>()
    private val principalElementsList = listOf(
            ProfileFragmentAdapterModel("Distance",
                    R.drawable.notify_panel_notification_icon_bg,
                    "distance",
                    "long"
            ),
            ProfileFragmentAdapterModel("Date d'inscription",
                    R.drawable.notify_panel_notification_icon_bg,
                    "registrationDate",
                    "string"
            ),
            ProfileFragmentAdapterModel("Pokédex rempli",
                    R.drawable.notify_panel_notification_icon_bg,
                    "countingpokemonCollection",
                    "long"
            ),
            ProfileFragmentAdapterModel("Nombre de succès",
                    R.drawable.notify_panel_notification_icon_bg,
                    "countingachievmentList",
                    "long"
            ))
    fun initMainInfos(context: Context, usernameTextView: TextView, lastUserConnection:TextView, registrationDateProfileTextView:TextView, avatarImageView:ImageView){
        FirebaseDatabaseSingleton.setUpTextView(usernameTextView,"username")
        FirebaseDatabaseSingleton.setUpTextView(lastUserConnection,"lastUserConnection")
        FirebaseDatabaseSingleton.setUpTextView(registrationDateProfileTextView,"registrationDate")
        FirebaseDatabaseSingleton.settingUpImageView(avatarImageView,
                context,
                FirebaseDatabaseSingleton.userRef.child(AppProviderSingleton.getInstance().User.uid).child("avatarImage"))
    }

    private fun initCompleteMainInfos(){
        val elementList = hashMapOf<String,String>()
        principalElementsList.forEach {
            if(it.databaseEntry.contains("counting")){
                FirebaseDatabaseSingleton.countElements(it,it.dataType,infoList)
            }else{
                FirebaseDatabaseSingleton
                FirebaseDatabaseSingleton.getElement(it,infoList)
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
        infoList.value = hashMapOf()
    }
}