package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon;

import java.util.List;

public class Element {
    private String elementName;

    private List<Element>weaknessElementList;
    private List<Element>effectiveElementList;
    private List<Element>normalElementList;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public List<Element> getWeaknessElementList() {
        return weaknessElementList;
    }

    public void setWeaknessElementList(List<Element> weaknessElementList) {
        this.weaknessElementList = weaknessElementList;
    }

    public List<Element> getEffectiveElementList() {
        return effectiveElementList;
    }

    public void setEffectiveElementList(List<Element> effectiveElementList) {
        this.effectiveElementList = effectiveElementList;
    }

    public List<Element> getNormalElementList() {
        return normalElementList;
    }

    public void setNormalElementList(List<Element> normalElementList) {
        this.normalElementList = normalElementList;
    }
}
