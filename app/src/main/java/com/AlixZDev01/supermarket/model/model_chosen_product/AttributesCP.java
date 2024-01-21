package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttributesCP {
    @SerializedName("title")
    private String titleCP;
    @SerializedName("values")
    private List<String> valuesCP;

    public AttributesCP(String titleCP, List<String> valuesCP) {
        this.titleCP = titleCP;
        this.valuesCP = valuesCP;
    }

    public String getTitleCP() {
        return titleCP;
    }

    public void setTitleCP(String titleCP) {
        this.titleCP = titleCP;
    }

    public List<String> getValuesCP() {
        return valuesCP;
    }

    public void setValuesCP(List<String> valuesCP) {
        this.valuesCP = valuesCP;
    }
}

