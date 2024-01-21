package com.AlixZDev01.supermarket.database.cart_db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void addProduct(ProductEntity product);

    @Delete
    void deleteProduct(ProductEntity product);

    @Update
    void updateProduct(ProductEntity product);

    @Query("SELECT * FROM table_cart")
    List<ProductEntity> getAllProducts();
}
