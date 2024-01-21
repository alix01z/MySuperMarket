package com.AlixZDev01.supermarket.model.model_search_products;

import com.google.gson.annotations.SerializedName;

public class DefaultVariantM {
    @SerializedName("price")
    private PriceM priceM;

    public DefaultVariantM(PriceM priceM) {
        this.priceM = priceM;
    }

    public PriceM getPriceM() {
        return priceM;
    }

    public void setPriceM(PriceM priceM) {
        this.priceM = priceM;
    }
}
