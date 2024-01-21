package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

public class ChosenPModel {
    @SerializedName("data")
    private DataCP dataCP;

    public ChosenPModel(DataCP dataCP) {
        this.dataCP = dataCP;
    }

    public DataCP getDataCP() {
        return dataCP;
    }

    public void setDataCP(DataCP dataCP) {
        this.dataCP = dataCP;
    }
}
