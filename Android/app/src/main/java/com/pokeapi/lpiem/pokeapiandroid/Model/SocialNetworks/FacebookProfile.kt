package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks

import java.util.ArrayList

class FacebookProfile {
    var userId: Int = 0
    var firstName: String? = null
    var lastName: String? = null

    var userList: List<FacebookProfile>? = null
        private set

    constructor(userId: Int, firstName: String, lastName: String, userList: List<FacebookProfile>) {
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
        this.userList = userList
    }

    constructor() {
        this.userId = 0
        this.firstName = ""
        this.lastName = ""
        this.userList = ArrayList()
    }
}
