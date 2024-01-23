package com.AlixZDev01.supermarket.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.AlixZDev01.supermarket.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {
    ImageButton btnRetry;
    TextView txtCheckNet;
    private static final int SPLASH_DELAY = 3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        navigateToMainActivity();

    }
    private void navigateToMainActivity(){
        if (isInternetConnected()){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gotoHome = new Intent(SplashActivity.this , MainActivity.class);
                    startActivity(gotoHome);
                    finish();
                }
            } , SPLASH_DELAY);
        }
        else{
            btnRetry.setVisibility(View.VISIBLE);
            txtCheckNet.setVisibility(View.VISIBLE);
        }
    }
    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
    private void initViews(){
        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(findViewById(R.id.text_splash));
        btnRetry = findViewById(R.id.but_retry);
        txtCheckNet = findViewById(R.id.text_checknet);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }






//    private boolean isNetworkConnected(){
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        boolean isConndected = false;
//        if(cm.getNetworkCapabilities(network) != null){
//            cm.getNetworkCapabilities(network).hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
//            isConndected = true;
//        }
//        return cm.getActiveNetwork() != null && isConndected;
//    }




    //ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
     //           if (cm.getNetworkCapabilities(network) != null &&
       //     cm.getNetworkCapabilities(network).hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
        //        Intent gotoHome = new Intent(SplashActivity.this , MainActivity.class);
     //   startActivity(gotoHome);
//        //    }
        //    else {
        //       btnRetry.setVisibility(View.VISIBLE);
        //      textCheckNet.setVisibility(View.VISIBLE);
        //    }
}