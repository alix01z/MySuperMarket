package com.AlixZDev01.supermarket.database.address_db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_address")
public class AddressEntity {
    @ColumnInfo(name = "Address_ID")
    @PrimaryKey(autoGenerate = true)
    private int addressId;
    @ColumnInfo(name = "Latitude")
    private double latitude;
    @ColumnInfo(name = "Longitude")
    private double longitude;
    @ColumnInfo(name = "Formatted_Address")
    private String formatted_address;

    ////Constructors & Getters & Setters
    @Ignore
    public AddressEntity() {
    }

    public AddressEntity(double latitude, double longitude, String formatted_address) {
        this.addressId = 0;
        this.latitude = latitude;
        this.longitude = longitude;
        this.formatted_address = formatted_address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}
