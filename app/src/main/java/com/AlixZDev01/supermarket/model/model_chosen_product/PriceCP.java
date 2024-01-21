package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

public class PriceCP {
    @SerializedName("selling_price")
    private int selling_price_CP;

    public PriceCP(int selling_price_CP) {
        this.selling_price_CP = selling_price_CP;
    }

    public int getSelling_price_CP() {
        return selling_price_CP;
    }

    public void setSelling_price_CP(int selling_price_CP) {
        this.selling_price_CP = selling_price_CP;
    }
}
