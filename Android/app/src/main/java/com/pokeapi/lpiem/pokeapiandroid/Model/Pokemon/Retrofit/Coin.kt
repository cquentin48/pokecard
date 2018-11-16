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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coin

        if (coinElementType != other.coinElementType) return false
        if (coinFileUrl != other.coinFileUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coinElementType?.hashCode() ?: 0
        result = 31 * result + (coinFileUrl?.hashCode() ?: 0)
        return result
    }
}