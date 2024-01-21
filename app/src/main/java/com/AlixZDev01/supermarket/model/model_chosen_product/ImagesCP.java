package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesCP {
    @SerializedName("list")
    private List<ListCP> listCPList;

    public ImagesCP(List<ListCP> listCPList) {
        this.listCPList = listCPList;
    }

    public List<ListCP> getListCPList() {
        return listCPList;
    }

    public void setListCPList(List<ListCP> listCPList) {
        this.listCPList = listCPList;
    }
}
