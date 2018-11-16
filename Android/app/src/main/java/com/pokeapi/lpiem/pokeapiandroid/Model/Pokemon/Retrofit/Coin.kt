package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

class Coin {
    var coinElementType: Element? = null
        private set
    var coinFileUrl: String? = null
        private set

    /**
     * Constructeur par défaut
     */
    constructor() {
        coinElementType = Element()
        coinFileUrl = ""
    }

    constructor(coinFileUrl: String, coinElementType: Element) {
        this.coinElementType = coinElementType
        this.coinFileUrl = coinFileUrl
    }
}