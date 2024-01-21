package com.AlixZDev01.supermarket.model.model_chosen_product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductCP {
    @SerializedName("id")
    private int id_CP;
    @SerializedName("title_fa")
    private String title_fa_CP;
    @SerializedName("default_variant")
    private DefaultVariantCP defaultVariantCP;
    @SerializedName("specifications")
    private List<SpecificationsCP> specificationsCP;
    @SerializedName("images")
    private ImagesCP imagesCP;

    public ProductCP(int id_CP, String title_fa_CP, DefaultVariantCP defaultVariantCP, List<SpecificationsCP> specificationsCP, ImagesCP imagesCP) {
        this.id_CP = id_CP;
        this.title_fa_CP = title_fa_CP;
        this.defaultVariantCP = defaultVariantCP;
        this.specificationsCP = specificationsCP;
        this.imagesCP = imagesCP;
    }

    public ImagesCP getImagesCP() {
        return imagesCP;
    }

    public void setImagesCP(ImagesCP imagesCP) {
        this.imagesCP = imagesCP;
    }

    public int getId_CP() {
        return id_CP;
    }

    public void setId_CP(int id_CP) {
        this.id_CP = id_CP;
    }

    public String getTitle_fa_CP() {
        return title_fa_CP;
    }

    public void setTitle_fa_CP(String title_fa_CP) {
        this.title_fa_CP = title_fa_CP;
    }

    public DefaultVariantCP getDefaultVariantCP() {
        return defaultVariantCP;
    }

    public void setDefaultVariantCP(DefaultVariantCP defaultVariantCP) {
        this.defaultVariantCP = defaultVariantCP;
    }

    public List<SpecificationsCP> getSpecificationsCP() {
        return specificationsCP;
    }

    public void setSpecificationsCP(List<SpecificationsCP> specificationsCP) {
        this.specificationsCP = specificationsCP;
    }
}
