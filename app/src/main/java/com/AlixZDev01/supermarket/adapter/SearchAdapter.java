package com.AlixZDev01.supermarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.activity.ChosenProductActivity;
import com.AlixZDev01.supermarket.database.cart_db.ProductDatabase;
import com.AlixZDev01.supermarket.database.cart_db.ProductEntity;
import com.AlixZDev01.supermarket.model.model_search_products.ProductM;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private Context context;
    private List<ProductM> productList;
    private ProductDatabase productDB;

    public SearchAdapter(Context context, List<ProductM> productList, ProductDatabase productDB) {
        this.context = context;
        this.productList = productList;
        this.productDB = productDB;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_recyclerv ,
                parent , false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        ProductM productM = productList.get(position);
        holder.setDataInSearchAdapter(productM , context , position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCPIntent = new Intent(context , ChosenProductActivity.class);
                gotoCPIntent.putExtra("idTransfer" , productM.getId());
                context.startActivity(gotoCPIntent);
            }
        });
        //Init Listener for adding product to Cart
        holder.imgbtnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long rowID = productDB.getProductDao().addProduct(new ProductEntity(productM.getId() ,
                        productM.getTitle_fa() , productM.getImagesM().getMain().getWebp_urlList().get(0) ,
                        productM.getDefaultVariantM().getPriceM().getSelling_price()));
                //Handle conflict ( adding duplicate button should increase amount of existing data , not add new data)
                if(rowID == -1){
                    ProductEntity existingProduct = productDB.getProductDao().getProduct(productM.getId());
                    if (existingProduct != null){
                        existingProduct.setAmount(existingProduct.getAmount() + 1);
                        productDB.getProductDao().updateProduct(existingProduct);
                    }
                }
                Toast.makeText(context, "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder{
        CardView cardvSearch;
        ImageView imgvSearch;
        TextView txtvSearchName;
        TextView txtvSearchPrice;
        ImageButton imgbtnAddToCart;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            cardvSearch = itemView.findViewById(R.id.cardv_search);
            imgvSearch = itemView.findViewById(R.id.imgv_search_product);
            txtvSearchName = itemView.findViewById(R.id.txt_search_product_name);
            txtvSearchPrice = itemView.findViewById(R.id.txt_search_product_price);
            imgbtnAddToCart = itemView.findViewById(R.id.imgbtn_addto_cart);
        }
        private void setDataInSearchAdapter(ProductM productM , Context context , int postition){
            txtvSearchName.setText(productM.getTitle_fa());
            txtvSearchPrice.setText(formatPrice(productM.getDefaultVariantM().getPriceM().getSelling_price()));
            Glide.with(context)
                    .load(productM.getImagesM().getMain().getWebp_urlList().get(0))
                    .into(imgvSearch);
        }
        private String formatPrice(int number){
            NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("fa"));
            return numberFormat.format(number / 10);
        }
    }
}
