package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListCP {
    @SerializedName("webp_url")
    private List<String> webp_urlCPList;
    public ListCP(List<String> webp_urlCPList) {
        this.webp_urlCPList = webp_urlCPList;
    }

    public List<String> getWebp_urlCPList() {
        return webp_urlCPList;
    }

    public void setWebp_urlCPList(List<String> webp_urlCPList) {
        this.webp_urlCPList = webp_urlCPList;
    }
}
