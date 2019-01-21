package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Model

class Achievement{
    var achievementName:String?= null
    var achievementPicture:String?=null
    var achievementDescription:String?=null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Achievement

        if (achievementName != other.achievementName) return false
        if (achievementPicture != other.achievementPicture) return false
        if (achievementDescription != other.achievementDescription) return false

        return true
    }

    override fun hashCode(): Int {
        var result = achievementName?.hashCode() ?: 0
        result = 31 * result + (achievementPicture?.hashCode() ?: 0)
        result = 31 * result + (achievementDescription?.hashCode() ?: 0)
        return result
    }
}
