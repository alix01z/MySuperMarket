package com.AlixZDev01.supermarket.database.address_db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddressDao {
    @Insert
    void addAddress(AddressEntity address);
    @Delete
    void deleteAddress(AddressEntity address);
    @Update
    void updateAddress(AddressEntity address);

    @Query("SELECT * FROM table_address")
    List<AddressEntity> getAllAddresses();

    @Query("SELECT * FROM Table_Address WHERE Address_ID = :address_id")
    AddressEntity getAddress(int address_id);
}
