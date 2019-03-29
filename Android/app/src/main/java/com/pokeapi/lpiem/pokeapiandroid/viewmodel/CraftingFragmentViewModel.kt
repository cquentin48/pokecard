package com.pokeapi.lpiem.pokeapiandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.pokeapi.lpiem.pokeapiandroid.model.retrofit.pokemons.TypeList
import com.pokeapi.lpiem.pokeapiandroid.provider.singleton.TypeListManagment

class CraftingFragmentViewModel {
    fun loadData(){
        TypeListManagment.loadAllTypes()
    }

    fun getTypesData():MutableLiveData<TypeList>{
        return TypeListManagment.typeList
    }
}