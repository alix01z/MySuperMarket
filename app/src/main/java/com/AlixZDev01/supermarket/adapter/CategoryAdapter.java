package com.AlixZDev01.supermarket.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.fragment.SelectedCategoryFragment;
import com.AlixZDev01.supermarket.model.CategoryItemModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<CategoryItemModel> categorylist;
    private Context context;
    private FragmentManager fragmentManager;
    private SelectedCategoryFragment selectedCategoryFragment;
    private Bundle bundleOnClickSendData;

    public CategoryAdapter(List<CategoryItemModel> categorylist, Context context, FragmentManager fragmentManager) {
        this.categorylist = categorylist;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_recyclerv ,
                parent , false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryItemModel cateModel = categorylist.get(position);
        holder.imgvCate.setImageResource(cateModel.getImagCate());
        holder.txtCateName.setText(cateModel.getTxtCate_fa());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCategoryFragment = new SelectedCategoryFragment();
                bundleOnClickSendData = new Bundle();
                bundleOnClickSendData.putString("ClickedItem" , cateModel.getTxtCate_en());
                selectedCategoryFragment.setArguments(bundleOnClickSendData);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container , selectedCategoryFragment , "SelectedCategoryF")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{
        CardView cardvCate;
        ImageView imgvCate;
        TextView txtCateName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cardvCate = itemView.findViewById(R.id.cv_category);
            imgvCate = itemView.findViewById(R.id.imgv_cv_category);
            txtCateName = itemView.findViewById(R.id.textv_cv_category);
        }
    }
}
