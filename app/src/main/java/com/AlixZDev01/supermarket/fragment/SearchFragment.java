package com.AlixZDev01.supermarket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SearchFragment extends Fragment {
    private String searchPhrase;
    private SearchAdapter searchAdapter;
    private LottieAnimationView lottieLoading;
    private TextView txtErrorLoading;
    private ImageView imgvSearch;
    private EditText edtxtSearch;
    private RecyclerView recyclerSearch;
    private BottomNavigationView bNavigationView;
    private ProductDatabase productDB;
    private List<ProductM> productSearchList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewSearch = inflater.inflate(R.layout.fragment_search , container , false);
        initDatabase();
        initViews(viewSearch);
        edtxtSearch.requestFocus();
        return viewSearch;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        showKeyboard(edtxtSearch);
        super.onViewCreated(view, savedInstanceState);
    }

    private void initViews(View v){
        imgvSearch = v.findViewById(R.id.imgv_search_icon);
        edtxtSearch = v.findViewById(R.id.edtxt_search);
        lottieLoading = v.findViewById(R.id.lottiev_loading);
        txtErrorLoading = v.findViewById(R.id.txt_errorloading);
        recyclerSearch = v.findViewById(R.id.recyclerv_search);
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.GONE);

        //init Recyclerview
        productSearchList = new ArrayList<>();
        searchAdapter = new SearchAdapter(getContext() , productSearchList , productDB);
        recyclerSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSearch.setAdapter(searchAdapter);
        //init Listeners
        imgvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = edtxtSearch.getText().toString();
                searchInputString(searchQuery);
                lottieLoading.setVisibility(View.VISIBLE);
                txtErrorLoading.setVisibility(View.INVISIBLE);
            }
        });
        edtxtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchQuery = edtxtSearch.getText().toString();
                    searchInputString(searchQuery);
                    lottieLoading.setVisibility(View.VISIBLE);
                    txtErrorLoading.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });
    }
    private void initDatabase(){
        productDB = Room.databaseBuilder(getActivity().getApplicationContext(), ProductDatabase.class
                , "MyProductDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
    private void searchInputString(String s){
        //init Retrofit
        lottieLoading.setVisibility(View.VISIBLE);
        String url = "https://api.digikala.com/v1/categories/food-beverage/";
        Retrofit retrofitS = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebService webService = retrofitS.create(WebService.class);
        webService.getSearchResults(s).enqueue(new Callback<ProductSearchM>() {
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
    private void showKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
