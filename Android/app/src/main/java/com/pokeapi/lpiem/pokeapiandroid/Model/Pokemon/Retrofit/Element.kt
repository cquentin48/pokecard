package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon.Retrofit

class Element {
    var elementName: String? = null

    var weaknessElementList: List<Element>? = null
    var effectiveElementList: List<Element>? = null
    var normalElementList: List<Element>? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Element

        if (elementName != other.elementName) return false
        if (weaknessElementList != other.weaknessElementList) return false
        if (effectiveElementList != other.effectiveElementList) return false
        if (normalElementList != other.normalElementList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = elementName?.hashCode() ?: 0
        result = 31 * result + (weaknessElementList?.hashCode() ?: 0)
        result = 31 * result + (effectiveElementList?.hashCode() ?: 0)
        result = 31 * result + (normalElementList?.hashCode() ?: 0)
        return result
    }
}
