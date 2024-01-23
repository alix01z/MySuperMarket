package com.AlixZDev01.supermarket.database.cart_db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ProductEntity.class} , version = 4)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao getProductDao();
}
