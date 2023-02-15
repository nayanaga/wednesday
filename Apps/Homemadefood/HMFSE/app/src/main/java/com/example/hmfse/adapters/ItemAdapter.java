package com.example.hmfse.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hmfse.EditItemActivity;
import com.example.hmfse.R;
import com.example.hmfse.data_models.ItemDataType;


import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    Context context;
    ArrayList<ItemDataType> ex;

    public ItemAdapter(Context context, ArrayList<ItemDataType> ex) {
        this.context = context;
        this.ex = ex;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_item_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemDataType dt = ex.get(position);

        holder.tvID.setText(dt.getID());
        holder.tvItem.setText(dt.getItem());
        holder.tvPrice.setText(dt.getPrice());

        byte[] decodeString = Base64.decode(dt.getImage(), Base64.DEFAULT);
        Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        holder.imageView.setImageBitmap(decodebitmap);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditItemActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID", dt.getID());
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

        TextView tvID, tvItem,tvPrice;
        Button btnEdit;
        EditText etQty;

        ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvPrice=(TextView)view.findViewById(R.id.tvPrice);
            imageView=(ImageView) view.findViewById(R.id.imageView);
            btnEdit=(Button) view.findViewById(R.id.btnEdit);

        }
    }
}
