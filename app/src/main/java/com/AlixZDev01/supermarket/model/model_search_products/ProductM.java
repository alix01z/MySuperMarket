package com.AlixZDev01.supermarket.model.model_search_products;

import com.google.gson.annotations.SerializedName;

public class ProductM {
    @SerializedName("id")
    private int id;

    @SerializedName("title_fa")
    private String title_fa;

    @SerializedName("images")
    private ImagesM imagesM;

    ///default_variant is a dynamic json
    @SerializedName("default_variant")
    private DefaultVariantM defaultVariantM;

    public DefaultVariantM getDefaultVariantM() {
        return defaultVariantM;
    }

    public void setDefaultVariantM(DefaultVariantM defaultVariantM) {
        this.defaultVariantM = defaultVariantM;
    }

    public ProductM(int id, String title_fa, ImagesM imagesM, DefaultVariantM defaultVariantM) {
        this.id = id;
        this.title_fa = title_fa;
        this.imagesM = imagesM;
        this.defaultVariantM = defaultVariantM;
    }

    public void processDefaultVariant(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle_fa() {
        return title_fa;
    }

    public void setTitle_fa(String title_fa) {
        this.title_fa = title_fa;
    }

    public ImagesM getImagesM() {
        return imagesM;
    }

    public void setImagesM(ImagesM imagesM) {
        this.imagesM = imagesM;
    }

}
