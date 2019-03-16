package com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel

data class ProfileSubFragmentRetrofit(
        var returnedData:HashMap<String, String>,
        var dataType:String,
        var success:Boolean,
        var errorImageURL:String,
        var errorMessageTitle:String,
        var errorMessageDescription:String)