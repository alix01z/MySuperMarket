package com.AlixZDev01.supermarket.model.model_search_products;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainM {
    @SerializedName("webp_url")
    private List<String> webp_urlList;

    public MainM(List<String> webp_urlList) {
        this.webp_urlList = webp_urlList;
    }

    public List<String> getWebp_urlList() {
        return webp_urlList;
    }

    public void setWebp_urlList(List<String> webp_urlList) {
        this.webp_urlList = webp_urlList;
    }
}
