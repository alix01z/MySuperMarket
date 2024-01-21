package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

public class DataCP {
    @SerializedName("product")
    private ProductCP productCP;
    public DataCP(ProductCP productCP) {
        this.productCP = productCP;
    }

    public ProductCP getProductCP() {
        return productCP;
    }

    public void setProductCP(ProductCP productCP) {
        this.productCP = productCP;
    }
}
