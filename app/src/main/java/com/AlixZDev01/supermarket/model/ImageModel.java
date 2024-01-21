package com.AlixZDev01.supermarket.model;

public class ImageModel {
    private int imageRes;
    private String id;

    public ImageModel(int imageRes, String id) {
        this.imageRes = imageRes;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
