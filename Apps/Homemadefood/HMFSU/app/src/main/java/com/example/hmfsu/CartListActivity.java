package com.example.hmfsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmfsu.adapters.CartAdapter;
import com.example.hmfsu.data_models.CartDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CartListActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<CartDataType> ex;

    TextView tvStoreName,tvStoreID,tvBillAmount;

    Button btnOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Items List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        tvStoreID=(TextView) findViewById(R.id.tvStoreID);
        tvStoreName=(TextView) findViewById(R.id.tvStoreName);
        tvBillAmount=(TextView) findViewById(R.id.tvBillAmount);

        btnOrder=(Button) findViewById(R.id.btnOrder);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else
            {
                //fetching value from shared preference
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                String user_id = sharedPreferences.getString("user_id", "");

                String query="SELECT c.EntrepreneurID,s.Entrepreneur,sum(c.Amount) as Amount from tblCart c,tblEntrepreneur s where c.UserID='" + user_id + "' and c.EntrepreneurID=s.Mobile group by c.UserID,c.EntrepreneurID,s.Entrepreneur";

                PreparedStatement stmt = conn.prepareStatement(query);
                rss = stmt.executeQuery();
                if(rss.next()) {
                    tvStoreID.setText(rss.getString("EntrepreneurID").toString());
                    tvStoreName.setText(rss.getString("Entrepreneur").toString());
                    tvBillAmount.setText(rss.getString("Amount").toString());
                }


                //fetching value from shared preference
                sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                user_id = sharedPreferences.getString("user_id", "");

                query="SELECT * from tblCart where UserID='" + user_id + "' and EntrepreneurID='" + tvStoreID.getText().toString() + "'";

                stmt = conn.prepareStatement(query);
                rss = stmt.executeQuery();
                ex = new ArrayList<>();
                while(rss.next()) {
                    //Log.d("ResultSet", rs.getString("ID"));
                    CartDataType dt = new CartDataType();
                    dt.setItem(rss.getString("Item").toString());
                    dt.setPrice(rss.getString("Price").toString());
                    dt.setQty(rss.getString("Qty").toString());
                    dt.setAmount(rss.getString("Amount").toString());
                    ex.add(dt);
                }
                CartAdapter eadapters = new CartAdapter(getApplicationContext(),ex);
                recyclerView.setAdapter(eadapters);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {

                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                        String user_id = sharedPreferences.getString("user_id", "");

                        String UserID = "";
                        String query1 = "Select OrderID from tblOrders order by 1 Desc";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        ResultSet rs = preparedStatement2.executeQuery();
                        if (rs.next()) {
                            String inID = rs.getString("OrderID");
                            int intID;
                            intID = Integer.parseInt(inID.substring(inID.indexOf("-") + 1).toString());
                            intID = intID + 1;

                            String unpadded = Integer.toString(intID);
                            String padded = "0000".substring(unpadded.length()) + unpadded;

                            UserID = "Order-" + padded;

                        } else {
                            UserID = "Order-0001";
                        }
                        String odate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());

                        String query2 = "Insert into tblOrders (OrderID,ODate,BillAmount,Status,UserID,EntrepreneurID) " +
                                "values ('" + UserID + "','" + odate + "'," +
                                "'" + tvBillAmount.getText().toString() + "','New'," +
                                "'" + user_id  + "','" + tvStoreID.getText().toString() + "')";
                        preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();


                        String query="SELECT * from tblCart where UserID='" + user_id + "' and EntrepreneurID='" + tvStoreID.getText().toString() + "'";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        ResultSet rss = stmt.executeQuery();
                        while(rss.next()) {

                            int intID=0;
                            query1 = "Select ID from tblOrdersItems order by 1 Desc";
                            preparedStatement2 = conn.prepareStatement(query1);
                            rs = preparedStatement2.executeQuery();
                            if (rs.next()) {
                                String inID = rs.getString("ID");

                                intID = Integer.parseInt(inID);
                                intID = intID + 1;
                            } else {
                                intID =1;
                            }

                            query2 = "Insert into tblOrdersItems (ID,OrderID,Item,Price,Qty,Amount) " +
                                    "values (" + intID + ",'" + UserID + "'," +
                                    "'" + rss.getString("Item").toString() + "'," +
                                    "'" + rss.getString("Price").toString() + "'," +
                                    "'" + rss.getString("Qty").toString() + "'," +
                                    "'" + rss.getString("Amount").toString() + "')";
                            preparedStatement2 = conn.prepareStatement(query2);
                            preparedStatement2.executeUpdate();
                        }

                        query2 = "delete from tblCart where UserID='" + user_id + "' and EntrepreneurID='" + tvStoreID.getText().toString() + "'";
                        preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}