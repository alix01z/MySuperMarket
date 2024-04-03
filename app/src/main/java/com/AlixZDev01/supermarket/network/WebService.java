package com.AlixZDev01.supermarket.network;

import com.AlixZDev01.supermarket.model.model_chosen_product.ChosenPModel;
import com.AlixZDev01.supermarket.model.model_search_products.ProductSearchM;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {
    @GET("search/")
    Call<ProductSearchM> getSearchResults(@Query("q") String searchPhrase);
    @GET("search/")
    Call<ProductSearchM> getInputResults();
    @GET("v2/product/{productID}/")
    Call<ChosenPModel> getChosenProductData(@Path("productID") String productID);
}
