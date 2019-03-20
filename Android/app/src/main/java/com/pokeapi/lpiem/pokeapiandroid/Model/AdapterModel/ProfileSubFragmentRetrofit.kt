package com.pokeapi.lpiem.pokeapiandroid.Model.AdapterModel

import com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks.Profile

data class ProfileSubFragmentRetrofit(
        var returnedData:HashMap<Int, Profile>,
        var title:String,
        var message:String)