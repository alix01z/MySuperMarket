package com.AlixZDev01.supermarket.model;

public class CategoryItemModel {
    private int imagCate;
    private String txtCate_fa;
    private String txtCate_en;

    public CategoryItemModel(int imagCate, String txtCate_fa, String txtCate_en) {
        this.imagCate = imagCate;
        this.txtCate_fa = txtCate_fa;
        this.txtCate_en = txtCate_en;
    }

    public String getTxtCate_en() {
        return txtCate_en;
    }

    public void setTxtCate_en(String txtCate_en) {
        this.txtCate_en = txtCate_en;
    }

    public int getImagCate() {
        return imagCate;
    }

    public void setImagCate(int imagCate) {
        this.imagCate = imagCate;
    }

    public String getTxtCate_fa() {
        return txtCate_fa;
    }

    public void setTxtCate_fa(String txtCate_fa) {
        this.txtCate_fa = txtCate_fa;
    }
}
