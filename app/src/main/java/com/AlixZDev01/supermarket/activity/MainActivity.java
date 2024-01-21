package com.AlixZDev01.supermarket.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.fragment.AddressFragment;
import com.AlixZDev01.supermarket.fragment.CartFragment;
import com.AlixZDev01.supermarket.fragment.CategoryFragment;
import com.AlixZDev01.supermarket.fragment.HomeFragment;
import com.AlixZDev01.supermarket.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;
    CartFragment cartFragment;
    CategoryFragment categoryFragment;
    AddressFragment addressFragment;
    String tagFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String destFragment = getIntent().getStringExtra("destFragment");
        if(Objects.equals(destFragment, "AddressF")){
            addressFragment = new AddressFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container , addressFragment , "AddressF").commit();
        }
        else {
            tagFragment = "HomeF";
            homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container , homeFragment , tagFragment).commit();
        }
        bottomNav = findViewById(R.id.bottom_navigation_view);
        bottomNav.setOnItemSelectedListener(naviListener);
    }


    private NavigationBarView.OnItemSelectedListener naviListener =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemID = item.getItemId();
                    Fragment selectedFragment = null;
                    if (itemID == R.id.home_navi_bar){
                        selectedFragment = new HomeFragment();
                        tagFragment = "HomeF";
                    }
                    if (itemID == R.id.profile_navi_bar){
                        selectedFragment = new ProfileFragment();
                        tagFragment = "ProfileF";
                    }
                    if (itemID == R.id.cart_navi_bar ){
                        selectedFragment = new CartFragment();
                        tagFragment = "CartF";
                    }
                    if (itemID == R.id.category_navi_bar){
                        selectedFragment = new CategoryFragment();
                        tagFragment = "CategoryF";
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container , selectedFragment , tagFragment).commit();
                    return false;
                }
            };
}