package com.AlixZDev01.supermarket.fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.AlixZDev01.supermarket.adapter.CartAdapter;
import com.AlixZDev01.supermarket.database.cart_db.ProductDatabase;
import com.AlixZDev01.supermarket.database.cart_db.ProductEntity;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private ProductDatabase productDB;
    private Context context;
    private List<ProductEntity> productsData;
    private RecyclerView recyclerCart;
    private LottieAnimationView lottieEmptycart;
    private TextView txtEmptyCart;
    private BottomNavigationView bNavigationView;
    CartAdapter cartAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewCartF = inflater.inflate(R.layout.fragment_cart , container , false);
        initDatabase();
        initViews(viewCartF);
        return viewCartF;
    }
    private void initViews(View v){
        lottieEmptycart = v.findViewById(R.id.lottie_cart_empty);
        txtEmptyCart = v.findViewById(R.id.txt_cart_empty);
        recyclerCart = v.findViewById(R.id.recyclerv_cart);
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.VISIBLE);

        productsData = new ArrayList<>();
        productsData = productDB.getProductDao().getAllProducts();
        if (productsData.size() == 0){
            lottieEmptycart.setVisibility(View.VISIBLE);
            txtEmptyCart.setVisibility(View.VISIBLE);
        }
        //init Recyclerview
        cartAdapter = new CartAdapter(getContext() , productsData , productDB);
        recyclerCart.setAdapter(cartAdapter);
        recyclerCart.setLayoutManager(new LinearLayoutManager(context));
    }
    private void initDatabase(){
        productDB = Room.databaseBuilder(getActivity().getApplicationContext(), ProductDatabase.class , "MyProductDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}
