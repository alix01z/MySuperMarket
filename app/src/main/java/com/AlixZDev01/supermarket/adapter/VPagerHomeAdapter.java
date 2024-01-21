package com.AlixZDev01.supermarket.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.fragment.SelectedCategoryFragment;
import com.AlixZDev01.supermarket.model.ImageModel;

import java.util.List;

public class VPagerHomeAdapter extends RecyclerView.Adapter<VPagerHomeAdapter.VPagerHomeViewHolder> {
    private List<ImageModel> imageList;
    private Context context;
    private SelectedCategoryFragment selectedCategoryFragment;
    private Bundle bundleOnClickSendData;
    private FragmentManager fragmentManager;

    public VPagerHomeAdapter(List<ImageModel> imageList, Context context , FragmentManager fragmentManager) {
        this.imageList = imageList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public VPagerHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vpager_home,
                parent , false);
        return new VPagerHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VPagerHomeViewHolder holder, int position) {
        ImageModel imgModel = imageList.get(position);
        holder.imgV.setImageResource(imgModel.getImageRes());
        holder.imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCategoryFragment = new SelectedCategoryFragment();
                bundleOnClickSendData = new Bundle();
                bundleOnClickSendData.putString("ClickedItem" , imgModel.getId());
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
        return imageList.size();
    }

    static class VPagerHomeViewHolder extends RecyclerView.ViewHolder{
        ImageView imgV;

        public VPagerHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgV = itemView.findViewById(R.id.imgv_vpager_home);
        }
    }
}

