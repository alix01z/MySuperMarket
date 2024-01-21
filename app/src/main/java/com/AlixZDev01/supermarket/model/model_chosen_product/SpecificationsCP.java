package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecificationsCP {
    @SerializedName("attributes")
    private List<AttributesCP> attributesCPList;

    public SpecificationsCP(List<AttributesCP> attributesCPList) {
        this.attributesCPList = attributesCPList;
    }

    public List<AttributesCP> getAttributesCPList() {
        return attributesCPList;
    }

    public void setAttributesCPList(List<AttributesCP> attributesCPList) {
        this.attributesCPList = attributesCPList;
    }
}
