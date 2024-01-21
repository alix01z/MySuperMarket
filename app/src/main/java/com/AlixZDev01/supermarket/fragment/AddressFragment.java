package com.AlixZDev01.supermarket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.activity.MapActivity;
import com.AlixZDev01.supermarket.adapter.AddressAdapter;
import com.AlixZDev01.supermarket.database.address_db.AddressDatabase;
import com.AlixZDev01.supermarket.database.address_db.AddressEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends Fragment {
    AddressDatabase addressDB;
    List<AddressEntity> addressData;
    private BottomNavigationView bNavigationView;
    private AddressAdapter addressAdapter;
    private RecyclerView recyclervAddress;
    private Button btnGoToMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewAddressF = inflater.inflate(R.layout.fragment_address , container , false);
        initDatabase();
        initViews(viewAddressF);
        return viewAddressF;
    }
    private void initDatabase(){
        addressDB = Room.databaseBuilder(getActivity().getApplicationContext() , AddressDatabase.class ,
                "MyAddressDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
    private void initViews(View v){
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.GONE);
        btnGoToMap = v.findViewById(R.id.btn_goto_map);
        //init Recyclerview
        addressData = new ArrayList<>();
        addressData = addressDB.getAddressDAO().getAllAddresses();
        addressAdapter = new AddressAdapter(addressData , getContext() , addressDB);
        recyclervAddress = (RecyclerView) v.findViewById(R.id.recyclerv_address);
        recyclervAddress.setAdapter(addressAdapter);
        recyclervAddress.setLayoutManager(new LinearLayoutManager(getContext()));;
        //init Listeners
        btnGoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMapIntent = new Intent(getActivity() , MapActivity.class);
                startActivity(gotoMapIntent);
            }
        });
    }
}
