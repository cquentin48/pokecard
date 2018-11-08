package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon;

public class Coin{
    private Element coinElementType;
    private String coinFileUrl;

    /**
     * Constructeur par défaut
     */
    public Coin(){
        coinElementType = new Element();
        coinFileUrl = "";
    }

    public Coin(String coinFileUrl, Element coinElementType){
        this.coinElementType = coinElementType;
        this.coinFileUrl = coinFileUrl;
    }

    public Element getCoinElementType(){
        return coinElementType;
    }

    public String getCoinFileUrl(){
        return this.coinFileUrl;
    }
}