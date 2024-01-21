package com.AlixZDev01.supermarket.model.model_search_products;

import com.google.gson.annotations.SerializedName;

public class ImagesM {
    @SerializedName("main")
    private MainM mainM;

    public ImagesM(MainM mainM) {
        this.mainM = mainM;
    }

    public MainM getMain() {
        return mainM;
    }

    public void setMain(MainM mainM) {
        this.mainM = mainM;
    }


}
