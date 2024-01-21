package com.AlixZDev01.supermarket.model.model_search_products;

import com.google.gson.annotations.SerializedName;

public class ProductSearchM {
    @SerializedName("data")
    private DataModelM dataModelM;

    public ProductSearchM(DataModelM dataModelM) {
        this.dataModelM = dataModelM;
    }

    public DataModelM getDataModelM() {
        return dataModelM;
    }

    public void setDataModelM(DataModelM dataModelM) {
        this.dataModelM = dataModelM;
    }
}
