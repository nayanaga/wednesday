package com.example.hmfsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmfsu.adapters.OrderItemAdapter;
import com.example.hmfsu.data_models.OrderItemDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDetailsActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();
    TextView tvOrderID,tvODate,tvBillAmount,tvStatus;

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<OrderItemDataType> ex;


    TextView etEntrepreneur,etOwnerName,etAddressLine1,etAddressLine2,etTaluk,etDistrict,etMobile,etEmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Order Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvOrderID=(TextView) findViewById(R.id.tvOrderID);
        tvODate=(TextView) findViewById(R.id.tvODate);
        tvBillAmount=(TextView) findViewById(R.id.tvBillAmount);
        tvStatus=(TextView) findViewById(R.id.tvStatus);

        etEntrepreneur=(TextView)findViewById(R.id.etEntrepreneur);
        etOwnerName=(TextView)findViewById(R.id.etOwnerName);
        etAddressLine1=(TextView)findViewById(R.id.etAddressLine1);
        etAddressLine1=(TextView)findViewById(R.id.etAddressLine1);
        etAddressLine2=(TextView)findViewById(R.id.etAddressLine2);
        etTaluk=(TextView)findViewById(R.id.etTaluk);
        etDistrict=(TextView)findViewById(R.id.etDistrict);
        etMobile=(TextView)findViewById(R.id.etMobile);
        etEmailID=(TextView)findViewById(R.id.etEmailID);


        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                Intent intent = getIntent();
                if(intent != null) {
                    tvOrderID.setText(intent.getExtras().getString("ID"));
                } else {
                    tvOrderID.setText("Data not available");
                }

                Toast.makeText(getApplicationContext(), tvOrderID.getText().toString().trim(), Toast.LENGTH_LONG).show();

                String query1 = "Select * from tblOrders where OrderID='" + tvOrderID.getText().toString().trim() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvODate.setText(rs.getString("ODate").toString());
                    tvBillAmount.setText(rs.getString("BillAmount").toString());
                    tvStatus.setText(rs.getString("Status").toString());
                    etMobile.setText(rs.getString("EntrepreneurID").toString());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else
            {
                String query="SELECT * from tblOrdersItems where OrderID='" + tvOrderID.getText().toString() + "'";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rss = stmt.executeQuery();
                ex = new ArrayList<>();
                while(rss.next()) {
                    //Log.d("ResultSet", rs.getString("ID"));
                    OrderItemDataType dt = new OrderItemDataType();
                    dt.setItem(rss.getString("Item").toString());
                    dt.setPrice(rss.getString("Price").toString());
                    dt.setQty(rss.getString("Qty").toString());
                    dt.setAmount(rss.getString("Amount").toString());
                    ex.add(dt);
                }
                OrderItemAdapter eadapters = new OrderItemAdapter(getApplicationContext(),ex);
                recyclerView.setAdapter(eadapters);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }


        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                String query1 = "Select * from tblEntrepreneur where Mobile='" + etMobile.getText().toString() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    etEntrepreneur.setText(rs.getString("Entrepreneur").toString());
                    etOwnerName.setText(rs.getString("OwnerName").toString());
                    etAddressLine1.setText(rs.getString("AddressLine1").toString());
                    etAddressLine2.setText(rs.getString("AddressLine2").toString());
                    etTaluk.setText(rs.getString("Taluk").toString());
                    etDistrict.setText(rs.getString("District").toString());
                    etMobile.setText(rs.getString("Mobile").toString());
                    etEmailID.setText(rs.getString("EmailID").toString());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),OrdersListActivity.class));
    }
}