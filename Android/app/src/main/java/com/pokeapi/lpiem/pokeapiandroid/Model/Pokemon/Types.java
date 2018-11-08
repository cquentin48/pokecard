package com.pokeapi.lpiem.pokeapiandroid.Model.Pokemon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Types {
    @SerializedName("slot")
    @Expose
    private int slot;

    @SerializedName("type")
    @Expose
    private Type type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
