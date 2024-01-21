package com.AlixZDev01.supermarket.database.address_db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AddressEntity.class} , version = 3)
public abstract class AddressDatabase extends RoomDatabase {
    public abstract AddressDao getAddressDAO();
}
