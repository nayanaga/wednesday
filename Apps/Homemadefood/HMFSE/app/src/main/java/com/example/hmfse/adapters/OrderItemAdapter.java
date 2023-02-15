package com.example.hmfse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hmfse.R;
import com.example.hmfse.data_models.OrderItemDataType;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder>{

    Context context;
    ArrayList<OrderItemDataType> ex;

    public OrderItemAdapter(Context context, ArrayList<OrderItemDataType> ex) {
        this.context = context;
        this.ex = ex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_order_item_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderItemDataType dt = ex.get(position);

        holder.tvItem.setText(dt.getItem());
        holder.tvPrice.setText(dt.getPrice());
        holder.tvQty.setText(dt.getQty());
        holder.tvAmount.setText(dt.getAmount());

    }

    @Override
    public int getItemCount() {
        // Toast.makeText(context.getApplicationContext(), String.valueOf(ex.size()), Toast.LENGTH_LONG).show();
        return ex.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem,tvPrice,tvQty,tvAmount;

        public ViewHolder(View view) {
            super(view);
            tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvPrice=(TextView)view.findViewById(R.id.tvPrice);
            tvQty=(TextView) view.findViewById(R.id.tvQty);
            tvAmount=(TextView) view.findViewById(R.id.tvAmount);
        }
    }
}
