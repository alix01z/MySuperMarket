package com.AlixZDev01.supermarket.model.model_search_products;

import com.google.gson.annotations.SerializedName;

public class PriceM {
    @SerializedName("selling_price")
    private int selling_price;

    public PriceM(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }
}
