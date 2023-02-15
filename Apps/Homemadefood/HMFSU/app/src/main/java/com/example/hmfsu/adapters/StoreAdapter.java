package com.example.hmfsu.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hmfsu.ItemsListActivity;
import com.example.hmfsu.R;
import com.example.hmfsu.StoreDetailsActivity;
import com.example.hmfsu.data_models.StoreDataType;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder>{

    Context context;
    ArrayList<StoreDataType> ex;

    public StoreAdapter(Context context, ArrayList<StoreDataType> ex) {
        this.context = context;
        this.ex = ex;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_store_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StoreDataType dt = ex.get(position);

        holder.tvID.setText(dt.getID());
        holder.tvStoreName.setText(dt.getEntrepreneur());
        holder.tvDistance.setText(dt.getDistance());

        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StoreDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Mobile", dt.getMobile());
                context.startActivity(i);

                //   Toast.makeText(context.getApplicationContext(), "Click on button", Toast.LENGTH_LONG).show();
            }
        });

        holder.btnItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ItemsListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Mobile", dt.getMobile());
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

        TextView tvID, tvStoreName,tvDistance;
        Button btnInfo,btnItems;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvStoreName = (TextView) view.findViewById(R.id.tvStoreName);
            tvDistance=(TextView)view.findViewById(R.id.tvDistance);

            btnInfo = (Button) view.findViewById(R.id.btnInfo);
            btnItems = (Button) view.findViewById(R.id.btnItems);
        }
    }
}
