package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

public class DefaultVariantCP {
    @SerializedName("price")
    private PriceCP priceCP;

    public DefaultVariantCP(PriceCP priceCP) {
        this.priceCP = priceCP;
    }

    public PriceCP getPriceCP() {
        return priceCP;
    }

    public void setPriceCP(PriceCP priceCP) {
        this.priceCP = priceCP;
    }
}
