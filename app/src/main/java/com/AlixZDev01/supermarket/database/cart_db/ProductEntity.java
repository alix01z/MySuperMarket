package com.AlixZDev01.supermarket.database.cart_db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_cart" , indices = {@Index(value = {"Product_ID"}, unique = true)})
public class ProductEntity {
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Product_ID")
    private int product_ID;

    @ColumnInfo(name = "Title_fa")
    private String title_fa;

    @ColumnInfo(name = "Webp_url")
    private String webp_url;

    @ColumnInfo(name = "Selling_price")
    private int selling_price;
    @ColumnInfo(name = "Amount")
    private int amount;

    public ProductEntity(int product_ID, String title_fa, String webp_url, int selling_price) {
        this.id = 0;
        this.product_ID = product_ID;
        this.title_fa = title_fa;
        this.webp_url = webp_url;
        this.selling_price = selling_price;
        this.amount = 1;
    }

    @Ignore
    public ProductEntity() {
    }

    public String getTitle_fa() {
        return title_fa;
    }

    public void setTitle_fa(String title_fa) {
        this.title_fa = title_fa;
    }

    public String getWebp_url() {
        return webp_url;
    }

    public void setWebp_url(String webp_url) {
        this.webp_url = webp_url;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
