package com.AlixZDev01.supermarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.activity.ChosenProductActivity;
import com.AlixZDev01.supermarket.model.model_search_products.ProductM;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private Context context;
    private List<ProductM> productList;

    public OfferAdapter(Context context, List<ProductM> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0){
            view = LayoutInflater.from(context).inflate(R.layout.item_offer_fixed_recycler ,
                    parent , false);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.item_offer_recyclerv ,
                    parent , false);
        }
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        if (position == 0){
            holder.imgvFIXED.setImageResource(R.drawable.offer_img);
        }
        else {
            ProductM productM = productList.get(position - 1);
            holder.setDataInOfferAdapter(productM , context);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gotoCPIntent = new Intent(context , ChosenProductActivity.class);
                    gotoCPIntent.putExtra("idTransfer" , productM.getId());
                    context.startActivity(gotoCPIntent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return productList.size() + 1;
    }

    static class OfferViewHolder extends RecyclerView.ViewHolder{
        ImageView imgvFIXED;
        TextView txtFIXED;
        CardView cardvOffer;
        ImageView imgvOffer;
        TextView txtOfferName;
        TextView txtOfferPrice;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            imgvFIXED = itemView.findViewById(R.id.imgv_fixed_offer_item);
            txtFIXED = itemView.findViewById(R.id.txt_fixed_offer_item);
            cardvOffer = itemView.findViewById(R.id.cardv_home_offer);
            imgvOffer = itemView.findViewById(R.id.imgv_offer_item);
            txtOfferName = itemView.findViewById(R.id.txt_offer_item_name);
            txtOfferPrice = itemView.findViewById(R.id.txt_offer_item_price);
        }
        private void setDataInOfferAdapter(ProductM productM , Context context){
            txtOfferPrice.setText(formatPrice(productM.getDefaultVariantM().getPriceM().getSelling_price()));
            txtOfferName.setText(productM.getTitle_fa());
            Glide.with(context)
                    .load(productM.getImagesM().getMain().getWebp_urlList().get(0))
                    .into(imgvOffer);
        }
        private String formatPrice(int number){
            NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("fa"));
            return numberFormat.format(number / 10);
        }
    }
}
