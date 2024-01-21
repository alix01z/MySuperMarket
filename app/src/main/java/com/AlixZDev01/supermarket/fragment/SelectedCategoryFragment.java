package com.AlixZDev01.supermarket.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.adapter.SearchAdapter;
import com.AlixZDev01.supermarket.database.cart_db.ProductDatabase;
import com.AlixZDev01.supermarket.model.model_search_products.ProductM;
import com.AlixZDev01.supermarket.model.model_search_products.ProductSearchM;
import com.AlixZDev01.supermarket.network.WebService;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectedCategoryFragment extends Fragment {
    private List<ProductM> productSearchList;
    private ProductDatabase productDB;
    private SearchAdapter searchAdapter;
    private RecyclerView recyclerSelectedCate;
    private LottieAnimationView lottieLoading;
    private BottomNavigationView bNavigationView;
    private String searchData;
    private TextView txtErrorLoading;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewSelectedCate = inflater.inflate(R.layout.fragment_selected_category ,container ,false);
        //init Bundle
        Bundle bundleGetData = getArguments();
        if (bundleGetData != null) {
            searchData = bundleGetData.getString("ClickedItem");
        }
        initDatabase();
        initViews(viewSelectedCate);
        getData(searchData);
        return viewSelectedCate;
    }
    private void initViews(View v){
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.GONE);
        lottieLoading = v.findViewById(R.id.lottiev_loading_selectf);
        txtErrorLoading = v.findViewById(R.id.txt_errorloading_selectf);
        recyclerSelectedCate = v.findViewById(R.id.recyclerv_selected_category);
        //init Recyclerview
        productSearchList = new ArrayList<>();
        searchAdapter = new SearchAdapter(getContext() , productSearchList , productDB);
        recyclerSelectedCate.setAdapter(searchAdapter);
        recyclerSelectedCate.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void getData(String input){
        lottieLoading.setVisibility(View.VISIBLE);
        String url = "https://api.digikala.com/v1/categories/" + input + "/";
        Retrofit retrofitS = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebService webService = retrofitS.create(WebService.class);
        webService.getInputResults().enqueue(new Callback<ProductSearchM>() {
            @Override
            public void onResponse(Call<ProductSearchM> call, Response<ProductSearchM> response) {
                lottieLoading.setVisibility(View.INVISIBLE);
                productSearchList.clear();
                ProductSearchM product = response.body();
                productSearchList.addAll(product.getDataModelM().getProductslist());
                searchAdapter.notifyDataSetChanged();
                Log.d("tagR", "onResponse: ");

            }

            @Override
            public void onFailure(Call<ProductSearchM> call, Throwable t) {
                Log.d("tagR", "onFailure: " + t.getMessage());
                lottieLoading.setVisibility(View.INVISIBLE);
                txtErrorLoading.setVisibility(View.VISIBLE);
            }
        });
    }
    private void initDatabase(){
        productDB = Room.databaseBuilder(getActivity().getApplicationContext() , ProductDatabase.class , "MyProductDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}
