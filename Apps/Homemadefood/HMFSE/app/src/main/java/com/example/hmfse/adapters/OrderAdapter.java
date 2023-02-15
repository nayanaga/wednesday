package com.example.hmfse.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hmfse.OrderDetailsActivity;
import com.example.hmfse.R;
import com.example.hmfse.data_models.OrderDataType;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    Context context;
    ArrayList<OrderDataType> ex;

    public OrderAdapter(Context context, ArrayList<OrderDataType> ex) {

        this.context = context;
        this.ex = ex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_order_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderDataType dt = ex.get(position);

        holder.tvOrderID.setText(dt.getOrderID());
        holder.tvODate.setText(dt.getODate());
        holder.tvBillAmount.setText(dt.getBillAmount());
        holder.tvStatus.setText(dt.getStatus());

        holder.btnItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, OrderDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID", dt.getOrderID());
                context.startActivity(i);
                //   Toast.makeText(context.getApplicationContext(), "Click on button", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        // Toast.makeText(context.getApplicationContext(), String.valueOf(ex.size()), Toast.LENGTH_LONG).show();
        return ex.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderID, tvODate,tvBillAmount,tvStatus;
        Button btnItems;

        public ViewHolder(View view) {
            super(view);

            tvOrderID = (TextView) view.findViewById(R.id.tvOrderID);
            tvODate = (TextView) view.findViewById(R.id.tvODate);
            tvBillAmount=(TextView)view.findViewById(R.id.tvBillAmount);
            tvStatus=(TextView)view.findViewById(R.id.tvStatus);

            btnItems = (Button) view.findViewById(R.id.btnItems);

        }
    }
}
