package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks

import java.util.ArrayList

class FacebookProfile {
    var userId: Int = 0
    var firstName: String? = null
    var lastName: String? = null

    var userList: List<FacebookProfile>? = null

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FacebookProfile

        if (userId != other.userId) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (userList != other.userList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (userList?.hashCode() ?: 0)
        return result
    }
}
