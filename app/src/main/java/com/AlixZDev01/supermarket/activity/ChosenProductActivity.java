package com.AlixZDev01.supermarket.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.adapter.SpecsAdapter;
import com.AlixZDev01.supermarket.adapter.VPagerCProductAdapter;
import com.AlixZDev01.supermarket.database.address_db.AddressEntity;
import com.AlixZDev01.supermarket.database.cart_db.ProductDatabase;
import com.AlixZDev01.supermarket.database.cart_db.ProductEntity;
import com.AlixZDev01.supermarket.model.model_chosen_product.AttributesCP;
import com.AlixZDev01.supermarket.model.model_chosen_product.ChosenPModel;
import com.AlixZDev01.supermarket.model.model_chosen_product.ListCP;
import com.AlixZDev01.supermarket.model.model_chosen_product.ProductCP;
import com.AlixZDev01.supermarket.network.WebService;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import org.w3c.dom.Attr;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChosenProductActivity extends AppCompatActivity {
    private ViewPager2 viewPagerProduct;
    private ImageView imgvProduct;
    private TextView txtProductName;
    private TextView txtCategory;
    private TextView txtPrice;
    private Button btnAddToCart;
    private RecyclerView recyclerSpecs;
    private ProductCP productCP;
    private String receivedID;
    private SpecsAdapter specsAdapter;
    private VPagerCProductAdapter vPagerAdapter;
    private List<AttributesCP> attributesCPList;
    private ProductDatabase productDB;
    private ChosenPModel chosenPModel;
    private List<ListCP> imageCPList;
    private WormDotsIndicator wormDotsIndicatorCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_product);
        Intent transferId = getIntent();
        if (transferId.hasExtra("idTransfer")){
            receivedID = String.valueOf(transferId.getIntExtra("idTransfer" , 0));
        }
        initDatabase();
        initViews();
        getData(receivedID);
    }
    private void initViews(){
        txtProductName = findViewById(R.id.txt_product_name);
        txtCategory = findViewById(R.id.txt_category);
        txtPrice = findViewById(R.id.txt_price);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        recyclerSpecs = findViewById(R.id.recyclerv_specs);
        viewPagerProduct = findViewById(R.id.viewpager_product);
        wormDotsIndicatorCP = findViewById(R.id.worm_dots_indicator_CP);
        //init Recyclerview
        attributesCPList = new ArrayList<>();
        specsAdapter = new SpecsAdapter(this , attributesCPList , productDB);
        recyclerSpecs.setLayoutManager(new LinearLayoutManager(this));
        recyclerSpecs.setAdapter(specsAdapter);
        //init ViewPager
        imageCPList = new ArrayList<>();
        vPagerAdapter = new VPagerCProductAdapter(this , imageCPList);
        viewPagerProduct.setAdapter(vPagerAdapter);
        //init WormDotIndicator
        wormDotsIndicatorCP.attachTo(viewPagerProduct);
        //init Listeners
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long rowID = productDB.getProductDao().addProduct(new ProductEntity(productCP.getId_CP() ,
                                productCP.getTitle_fa_CP() , productCP.getImagesCP().getListCPList().get(0).getWebp_urlCPList().get(0) ,
                                productCP.getDefaultVariantCP().getPriceCP().getSelling_price_CP()));
                //Handle conflict ( adding duplicate button should increase amount of existing data , not add new data)
                if (rowID > 0) {
                    // Insert was successful
                    Toast.makeText(getApplicationContext(), "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle conflict (adding duplicate button should increase the amount of existing data, not add new data)
                    ProductEntity existingProduct = productDB.getProductDao().getProduct(productCP.getId_CP());
                    if (existingProduct != null) {
                        existingProduct.setAmount(existingProduct.getAmount() + 1);
                        productDB.getProductDao().updateProduct(existingProduct);
                        Toast.makeText(getApplicationContext(), "محصول تکراری، تعداد افزایش یافت", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void getData(String input){
        String url = "https://api.digikala.com/";
        Retrofit retrofitCP = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebService webService = retrofitCP.create(WebService.class);
        webService.getChosenProductData(receivedID).enqueue(new Callback<ChosenPModel>() {
            @Override
            public void onResponse(Call<ChosenPModel> call, Response<ChosenPModel> response) {
                attributesCPList.clear();
                imageCPList.clear();
                chosenPModel = response.body();
                productCP = chosenPModel.getDataCP().getProductCP();
                attributesCPList.addAll(productCP.getSpecificationsCP().get(0).getAttributesCPList());
                imageCPList.addAll(productCP.getImagesCP().getListCPList());
                vPagerAdapter.notifyDataSetChanged();
                specsAdapter.notifyDataSetChanged();
                txtProductName.setText(productCP.getTitle_fa_CP());
                txtPrice.setText(formatPrice(productCP.getDefaultVariantCP().getPriceCP().getSelling_price_CP()));
                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<ChosenPModel> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }
    //show price with persian digits
    private String formatPrice(int number){
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("fa"));
        return numberFormat.format(number / 10);
    }
    private void initDatabase(){
        productDB = Room.databaseBuilder(this, ProductDatabase.class
                , "MyProductDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}