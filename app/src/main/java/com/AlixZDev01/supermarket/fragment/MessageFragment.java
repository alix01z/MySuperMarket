package com.AlixZDev01.supermarket.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.AlixZDev01.supermarket.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MessageFragment extends Fragment {
    private BottomNavigationView bNavigationView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewMessage = inflater.inflate(R.layout.fragment_message, container, false);
        initViews(viewMessage);

        Log.d("MessageLifeCycle", "onCreateView: ");
        return viewMessage;
    }
    private void initViews(View v){
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ProfileLifeCycle" , "onViewCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MessageLifeCycle" , "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MessageLifeCycle" , "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MessageLifeCycle", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MessageLifeCycle", "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MessageLifeCycle", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MessageLifeCycle", "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MessageLifeCycle", "onDetach: ");
    }
}
