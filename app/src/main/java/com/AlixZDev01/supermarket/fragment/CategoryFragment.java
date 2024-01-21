package com.AlixZDev01.supermarket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.adapter.CategoryAdapter;
import com.AlixZDev01.supermarket.model.CategoryItemModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private CategoryAdapter cateAdapter;
    private List<CategoryItemModel> categorylist;
    private BottomNavigationView bNavigationView;
    private RecyclerView recyclerCate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewCate = inflater.inflate(R.layout.fragment_category , container , false);
        initViews(viewCate);
        return viewCate;
    }
    private void initViews(View v){
        bNavigationView = getActivity().findViewById(R.id.bottom_navigation_view);
        bNavigationView.setVisibility(View.VISIBLE);
        //init Recyclerview
        categorylist = new ArrayList<>();
        cateAdapter = new CategoryAdapter(categorylist , getContext() , requireActivity().getSupportFragmentManager());
        recyclerCate = v.findViewById(R.id.recyclerv_category);
        recyclerCate.setAdapter(cateAdapter);
        recyclerCate.setLayoutManager(new GridLayoutManager(getContext() , 2));

        initListModel(categorylist);
        cateAdapter.notifyDataSetChanged();
    }
    private void initListModel(List<CategoryItemModel> categorylist){
        int[] imageCate = {R.drawable.cate_1_groceries , R.drawable.cate_2_diary , R.drawable.cate_3_proteinfoods ,
        R.drawable.cate_4_breakfast , R.drawable.cate_5_beverages , R.drawable.cate_6_readyfood_cannedmade ,
        R.drawable.cate_7_driedfruit_nuts , R.drawable.cate_8_baby_mother , R.drawable.cate_9_homehygiene ,
        R.drawable.cate_10_personalhygiene , R.drawable.cate_11_dietary_medicinal_supplements , R.drawable.cate_12_naturalflowers ,
        R.drawable.cate_13_snacks , R.drawable.cate_14_fruits_vegetables , R.drawable.cate_15_warmdrinks ,
        R.drawable.cate_16_salt_pickles , R.drawable.cate_17_frozenfood , R.drawable.cate_18_condiments};

        String[] cateTitleArr_Fa = getResources().getStringArray(R.array.category_item_names_fa);
        String[] cateTitleArr_En = getResources().getStringArray(R.array.category_item_names_en);
        for (int i = 0; i < cateTitleArr_Fa.length; i++){
            CategoryItemModel itemModel = new CategoryItemModel(imageCate[i] , cateTitleArr_Fa[i] , cateTitleArr_En[i]);
            categorylist.add(itemModel);
        }
    }
}
