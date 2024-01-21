package com.AlixZDev01.supermarket.model.model_search_products;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModelM {
    @SerializedName("products")
    private List<ProductM> productslist;
    @SerializedName("product")
    private ProductM productM;

    public DataModelM(List<ProductM> productslist) {
        this.productslist = productslist;
    }

    public List<ProductM> getProductslist() {
        return productslist;
    }

    public void setProductslist(List<ProductM> productslist) {
        this.productslist = productslist;
    }
}
