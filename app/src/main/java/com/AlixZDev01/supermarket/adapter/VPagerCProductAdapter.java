package com.AlixZDev01.supermarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.model.model_chosen_product.ListCP;
import com.bumptech.glide.Glide;

import java.util.List;

public class VPagerCProductAdapter extends RecyclerView.Adapter<VPagerCProductAdapter.VPagerCPViewHolder> {
    private Context context;
    private List<ListCP> imageCPList;

    public VPagerCProductAdapter(Context context, List<ListCP> imageCPList) {
        this.context = context;
        this.imageCPList = imageCPList;
    }

    @NonNull
    @Override
    public VPagerCPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vpager_product ,
                parent , false);
        return new VPagerCPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VPagerCPViewHolder holder, int position) {
        String webpUrl = imageCPList.get(position).getWebp_urlCPList().get(0);
        holder.setImageWithGlide(webpUrl , context);
    }

    @Override
    public int getItemCount() {
        return imageCPList.size();
    }

    public static class VPagerCPViewHolder extends RecyclerView.ViewHolder{
        ImageView imgvProduct;
        public VPagerCPViewHolder(@NonNull View itemView) {
            super(itemView);
            imgvProduct = itemView.findViewById(R.id.imgv_vpager_product);
        }
        private void setImageWithGlide(String url , Context context){
            Glide.with(context)
                    .load(url)
                    .into(imgvProduct);
        }
    }
}
