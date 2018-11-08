package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {
    @SerializedName("name")
    @Expose
    private String typeName;

    @SerializedName("url")
    @Expose
    private String url;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
