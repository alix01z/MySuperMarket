package com.AlixZDev01.supermarket.network;

import com.AlixZDev01.supermarket.model.NeshanAddressModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NeshanReverseService {
    @Headers("Api-Key: service.9995ec2f893d44b2a614ed1f6d6e5649")
    @GET("/v5/reverse")
    Call<NeshanAddressModel> getNeshanAddress(@Query("lat") Double latitude , @Query("lng") Double longitude);
}
