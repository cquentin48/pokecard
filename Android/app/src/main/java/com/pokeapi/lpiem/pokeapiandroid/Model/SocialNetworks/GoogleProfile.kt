package com.pokeapi.lpiem.pokeapiandroid.Model.SocialNetworks

import android.net.Uri

class GoogleProfile(//Pseudo
        _email: String?, //Email
        _firstName: String?, //Prénom
        _familyName: String?, //Nom de famille
        _givenName: String?, //Identifiant
        _id: String?, //URI du compte
        _accountURI: Uri?
) {
    private var email:String = _email!!
    private var firstName:String = _firstName!!
    private var familyName:String = _familyName!!
    private var givenName:String = _givenName!!
    private var id:String = _id!!
    private var accountURI:Uri = _accountURI!!

    //Getters/Setters
    var Email: String
        get() = this.email
        set(newValue){
            this.email = newValue
        }
    var FirstName: String
        get() = this.firstName
        set(newValue){
            this.firstName = newValue
        }
    var FamilyName: String
        get() = this.familyName
        set(newValue){
            this.familyName = newValue
        }
    var GivenName: String
        get() = this.givenName
        set(newValue){
            this.givenName = newValue
        }
    var Id: String
        get() = this.id
        set(newValue){
            this.id = newValue
        }
    var AccountURI: Uri
        get() = this.accountURI
        set(newValue){
            this.accountURI = newValue
        }

}
