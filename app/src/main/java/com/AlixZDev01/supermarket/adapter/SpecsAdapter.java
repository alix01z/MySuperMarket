package com.AlixZDev01.supermarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.database.cart_db.ProductDatabase;
import com.AlixZDev01.supermarket.model.model_chosen_product.AttributesCP;
import com.AlixZDev01.supermarket.model.model_chosen_product.ListCP;

import java.util.List;

public class SpecsAdapter extends RecyclerView.Adapter<SpecsAdapter.SpecsViewHolder> {
    private Context context;
    private List<AttributesCP> attributesCPList;
    private ProductDatabase productDB;

    public SpecsAdapter(Context context, List<AttributesCP> attributesCPList , ProductDatabase productDB) {
        this.context = context;
        this.attributesCPList = attributesCPList;
        this.productDB = productDB;
    }

    @NonNull
    @Override
    public SpecsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_specs_recyclerv ,
                parent , false);
        return new SpecsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecsViewHolder holder, int position) {
        AttributesCP attributesCP = attributesCPList.get(position);
        holder.setDataInSpecsAdapter(attributesCP , context);

    }

    @Override
    public int getItemCount() {
        return attributesCPList.size();
    }

    public static class SpecsViewHolder extends RecyclerView.ViewHolder{
        TextView txtSpecsKind;
        TextView txtSpecsDescription;
        public SpecsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSpecsDescription = itemView.findViewById(R.id.txt_specs_description);
            txtSpecsKind = itemView.findViewById(R.id.txt_specs_kind);
        }
        public void setDataInSpecsAdapter(AttributesCP attributesCP , Context context){
            txtSpecsKind.setText(attributesCP.getTitleCP());
            txtSpecsDescription.setText(attributesCP.getValuesCP().get(0));
        }
    }
}
