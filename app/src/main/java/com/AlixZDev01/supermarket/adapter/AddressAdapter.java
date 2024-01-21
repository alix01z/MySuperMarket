package com.AlixZDev01.supermarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AlixZDev01.supermarket.R;
import com.AlixZDev01.supermarket.database.address_db.AddressDatabase;
import com.AlixZDev01.supermarket.database.address_db.AddressEntity;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    private Context context;
    private List<AddressEntity> addressList;
    private AddressDatabase addressDB;
    public AddressAdapter(List<AddressEntity> addressList, Context context , AddressDatabase addressDB) {
        this.addressList = addressList;
        this.context = context;
        this.addressDB = addressDB;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address_recyclerv ,
                parent , false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        AddressEntity addressE = addressList.get(position);
        holder.txtID.setText(String.valueOf(addressE.getAddressId()));
        holder.txtFormattedAddress.setText(addressE.getFormatted_address());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressDB.getAddressDAO().deleteAddress(addressE);
                int adapterPositionAfterDelete = holder.getAdapterPosition();
                addressList.remove(adapterPositionAfterDelete);
                notifyItemRemoved(adapterPositionAfterDelete);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    static class AddressViewHolder extends RecyclerView.ViewHolder{
        TextView txtID;
        TextView txtFormattedAddress;
        ImageButton btnDelete;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.text_address_id);
            txtFormattedAddress = itemView.findViewById(R.id.text_address_formatted_address);
            btnDelete = itemView.findViewById(R.id.btn_address_delete);
        }
    }
}
