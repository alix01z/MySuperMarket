package com.AlixZDev01.supermarket.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;

import com.AlixZDev01.supermarket.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {
    Network network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageButton butRetry = findViewById(R.id.but_retry);
        TextView textCheckNet = findViewById(R.id.text_checknet);
        //initialize YoYo
        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(findViewById(R.id.text_splash));


        //Go to MainActivity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gotoHome = new Intent(SplashActivity.this , MainActivity.class);
                startActivity(gotoHome);
                finish();
            }
        } , 3500);
    }
    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConndected = false;
        if(cm.getNetworkCapabilities(network) != null){
            cm.getNetworkCapabilities(network).hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            isConndected = true;
        }
        return cm.getActiveNetwork() != null && isConndected;
    }
    //ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
     //           if (cm.getNetworkCapabilities(network) != null &&
       //     cm.getNetworkCapabilities(network).hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
        //        Intent gotoHome = new Intent(SplashActivity.this , MainActivity.class);
     //   startActivity(gotoHome);
//        //    }
        //    else {
        //       butRetry.setVisibility(View.VISIBLE);
        //      textCheckNet.setVisibility(View.VISIBLE);
        //    }

}