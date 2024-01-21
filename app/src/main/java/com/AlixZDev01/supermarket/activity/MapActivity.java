package com.AlixZDev01.supermarket.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.database.address_db.AddressDatabase;
import com.AlixZDev01.supermarket.database.address_db.AddressEntity;
import com.AlixZDev01.supermarket.model.NeshanAddressModel;
import com.AlixZDev01.supermarket.network.NeshanRetrofitConfig;
import com.AlixZDev01.supermarket.network.NeshanReverseService;

import org.neshan.common.model.LatLng;
import org.neshan.mapsdk.MapView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity {
    public static final NeshanReverseService getReverseData = NeshanRetrofitConfig.getRetrofitInstance()
            .create(NeshanReverseService.class);
    private TextView txtAddressTitle;
    private Button btnSaveAddress;
    private ProgressBar pbAddress;
    private MapView neshanMapv;
    String address;
    AddressDatabase addressDB;
    AddressEntity addressE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initViews();
        //init RoomDatabase
        addressDB = Room.databaseBuilder(getApplicationContext() , AddressDatabase.class ,
                "MyAddressDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initMap();
        btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng currentLoc = neshanMapv.getCameraTargetPosition();
                if(address != null){
                    addressE = new AddressEntity(currentLoc.getLatitude() , currentLoc.getLongitude() , address);
                    addressDB.getAddressDAO().addAddress(addressE);
                    Intent gotoAddressF = new Intent(MapActivity.this , MainActivity.class);
                    gotoAddressF.putExtra("destFragment" , "AddressF");
                    startActivity(gotoAddressF);
                    finish();
                }
                else {
                    Toast.makeText(MapActivity.this, "دوباره تلاش کنید!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews(){
        txtAddressTitle = findViewById(R.id.txt_address_title);
        pbAddress = findViewById(R.id.pb_address);
        neshanMapv = findViewById(R.id.mapv_neshan);
        btnSaveAddress = findViewById(R.id.btn_save_address);
    }
    private void initMap(){
        neshanMapv.moveCamera(new LatLng(35.700962,51.391166 ) , 0);
        neshanMapv.setZoom(14 , 0);

        neshanMapv.setOnCameraMoveFinishedListener(new MapView.OnCameraMoveFinishedListener() {
            @Override
            public void onCameraMoveFinished(int i) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pbAddress.setVisibility(View.VISIBLE);
                    }
                });
                getReverseAPI(neshanMapv.getCameraTargetPosition());
            }
        });
    }
    private void getReverseAPI(LatLng currentLocation){
        getReverseData.getNeshanAddress(currentLocation.getLatitude() , currentLocation.getLongitude())
                .enqueue(new Callback<NeshanAddressModel>() {
                    @Override
                    public void onResponse(Call<NeshanAddressModel> call, Response<NeshanAddressModel> response) {
                        address = response.body().getAddress();
                        if (address != null){
                            txtAddressTitle.setText(address);
                        }
                        else {
                            txtAddressTitle.setText("معبر بی نام");
                        }
                        pbAddress.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<NeshanAddressModel> call, Throwable t) {
                        txtAddressTitle.setText("معبر بی نام");
                        pbAddress.setVisibility(View.INVISIBLE);
                    }
                });

    }
}