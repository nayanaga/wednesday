package com.example.hmfsu.adapters;

import android.content.Context;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hmfsu.ConnectionClass;
import com.example.hmfsu.R;
import com.example.hmfsu.data_models.ItemDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        holder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(context.getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        //fetching value from shared preference
                        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("LK", 0);
                        String user_name = sharedPreferences.getString("user_name", "");
                        String user_id = sharedPreferences.getString("user_id", "");

                        String query1 = "Delete from tblCart where UserID='" + user_id + "' and EntrepreneurID!='" + dt.getEntrepreneurID().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        preparedStatement2.executeUpdate();

                        int intID=0;
                        query1 = "Select ID from tblCart order by 1 Desc";
                        preparedStatement2 = conn.prepareStatement(query1);
                        ResultSet rs = preparedStatement2.executeQuery();
                        if (rs.next()) {
                            String inID = rs.getString("ID");
                            intID = Integer.parseInt(inID.toString());
                            intID = intID + 1;
                        } else {
                            intID=1;
                        }

                        int amt=Integer.parseInt(holder.tvPrice.getText().toString())* Integer.parseInt(holder.etQty.getText().toString());

                        String query2 = "Insert into tblCart (ID,Item,Price,Qty,Amount,EntrepreneurID," +
                                "UserID) values ('" + intID + "','" + dt.getItem() + "'," +
                                "'" + dt.getPrice() + "','" + holder.etQty.getText().toString() + "'," +
                                "'" + amt  + "','" + dt.getEntrepreneurID().toString() + "'," +
                                "'" + user_id + "')";
                        preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(context.getApplicationContext(), "Added Successfully.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(context.getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }

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
        Button btnAddCart;
        EditText etQty;

        ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvPrice=(TextView)view.findViewById(R.id.tvPrice);
            imageView=(ImageView) view.findViewById(R.id.imageView);
            btnAddCart=(Button) view.findViewById(R.id.btnAddCart);

            etQty=(EditText) view.findViewById(R.id.etQty);
        }
    }
}
