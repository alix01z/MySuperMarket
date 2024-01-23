package com.AlixZDev01.supermarket.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.AlixZDev01.supermarket.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {
    MessageFragment messageFragment = new MessageFragment();
    AddressFragment addressFragment = new AddressFragment();
    OrdersFragment ordersFragment = new OrdersFragment();
    private RelativeLayout relativeLAddress;
    private RelativeLayout relativeLMessage;
    private RelativeLayout relativeLOrders;
    private BottomNavigationView bNavigationView;
    FragmentTransaction ft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewProfile = inflater.inflate(R.layout.fragment_profile , container , false);
        initViews(viewProfile);
        return  viewProfile;
    }
    private void initViews(View v){
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.VISIBLE);
        relativeLAddress = v.findViewById(R.id.relativeL_addresses);
        relativeLMessage = v.findViewById(R.id.relativeL_messages);
        relativeLOrders = v.findViewById(R.id.relativeL_orders);
        //init Listeners
        relativeLAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, addressFragment , "AddressF");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        relativeLMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, messageFragment , "MessageF");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        relativeLOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, ordersFragment , "OrdersF");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}
