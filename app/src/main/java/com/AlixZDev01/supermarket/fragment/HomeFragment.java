package com.AlixZDev01.supermarket.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.adapter.OfferAdapter;
import com.AlixZDev01.supermarket.adapter.VPagerHomeAdapter;
import com.AlixZDev01.supermarket.model.ImageModel;
import com.AlixZDev01.supermarket.model.model_search_products.ProductM;
import com.AlixZDev01.supermarket.model.model_search_products.ProductSearchM;
import com.AlixZDev01.supermarket.network.WebService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private SearchFragment searchFragment = new SearchFragment();
    private SelectedCategoryFragment selectedCategoryFragment = new SelectedCategoryFragment();
    private Bundle bundleSendData;
    private FragmentTransaction ft;
    private List<ImageModel> imgList;
    private RelativeLayout relativeSearch;
    private ViewPager2 viewPagerHome;
    private WormDotsIndicator wormDotsIndicator;
    private BottomNavigationView bNavigationView;
    private RecyclerView recyclerOffer;
    private List<ProductM> productMList;
    private OfferAdapter offerAdapter;
    private ImageView imgHomeAdds2_1;
    private ImageView imgHomeAdds2_2;
    private ImageView imgHomeAdds2_3;
    private ImageView imgHomeAdds2_4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewHome = inflater.inflate(R.layout.fragment_home , container , false);
        initViews(viewHome);
        getOfferData();
        return viewHome;
    }
    private void initViews(View v){

        viewPagerHome = v.findViewById(R.id.viewpager_home);
        wormDotsIndicator = v.findViewById(R.id.worm_dots_indicator_home);
        recyclerOffer = v.findViewById(R.id.recyclerv_home_offer);
        relativeSearch = v.findViewById(R.id.relative_gotosearch);
        imgHomeAdds2_1 = v.findViewById(R.id.imgv_homeadds2_1);
        imgHomeAdds2_2 = v.findViewById(R.id.imgv_homeadds2_2);
        imgHomeAdds2_3 = v.findViewById(R.id.imgv_homeadds2_3);
        imgHomeAdds2_4 = v.findViewById(R.id.imgv_homeadds2_4);
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.VISIBLE);
        //initialize ViewPager & Adapter & Contents
        initViewPagerHome();
        //attach dotIndicator to viewpager
        wormDotsIndicator.attachTo(viewPagerHome);
        //init Recyclerview
        productMList = new ArrayList<>();
        offerAdapter = new OfferAdapter(getContext() , productMList);
        recyclerOffer.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , true));
        recyclerOffer.setAdapter(offerAdapter);
        //init Listeners
        relativeSearch.setOnClickListener(this);
        imgHomeAdds2_1.setOnClickListener(this);
        imgHomeAdds2_2.setOnClickListener(this);
        imgHomeAdds2_3.setOnClickListener(this);
        imgHomeAdds2_4.setOnClickListener(this);
    }
    private void getOfferData(){
        String url = "https://api.digikala.com/v1/categories/food-beverage/";
        Retrofit retrofitS = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebService webService = retrofitS.create(WebService.class);
        webService.getSearchResults("قهوه").enqueue(new Callback<ProductSearchM>() {
            @Override
            public void onResponse(Call<ProductSearchM> call, Response<ProductSearchM> response) {
                productMList.clear();
                ProductSearchM product = response.body();
                productMList.addAll(product.getDataModelM().getProductslist());
                offerAdapter.notifyDataSetChanged();
                Log.d("tagR", "onResponse: ");

            }

            @Override
            public void onFailure(Call<ProductSearchM> call, Throwable t) {
                Log.d("tagR", "onFailure: " + t.getMessage());
            }
        });
    }
    private void initViewPagerHome(){
        imgList = new ArrayList<>();
        imgList.add(new ImageModel(R.drawable.home_adds1_1 , "home-hygiene"));
        imgList.add(new ImageModel(R.drawable.home_adds1_2 , "protein-foods"));
        imgList.add(new ImageModel(R.drawable.home_adds1_3 , "clothes-detergents"));
        imgList.add(new ImageModel(R.drawable.home_adds1_4 , "breakfast"));
        viewPagerHome.setAdapter(new VPagerHomeAdapter(imgList , getContext() , requireActivity().getSupportFragmentManager()));
    }
    //init Listeners
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.relative_gotosearch) {
            ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, searchFragment, "SearchF")
                    .addToBackStack(null)
                    .commit();
        }
        else if (v.getId() == R.id.imgv_homeadds2_1){
            sendDataToCategoryFragment("baby-and-mother");
        }
        else if (v.getId() == R.id.imgv_homeadds2_2){
            sendDataToCategoryFragment("snacks");
        }
        else if (v.getId() == R.id.imgv_homeadds2_3){
            sendDataToCategoryFragment("diary");
        }
        else if (v.getId() == R.id.imgv_homeadds2_4){
            sendDataToCategoryFragment("personal-hygiene");
        }

    }
    private void sendDataToCategoryFragment(String content){
        selectedCategoryFragment = new SelectedCategoryFragment();
        bundleSendData = new Bundle();
        bundleSendData.putString("ClickedItem" , content);
        selectedCategoryFragment.setArguments(bundleSendData);
        ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container , selectedCategoryFragment , "SelectedCategoryF")
                .addToBackStack(null)
                .commit();
    }
}
