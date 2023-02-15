package com.example.hmfse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmfse.adapters.OrderItemAdapter;
import com.example.hmfse.data_models.OrderItemDataType;
import com.example.hmfse.locations.GpsTracker1;

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

    String UserID=null;

    TextView tvUserName,tvAddressLine1,tvAddressLine2,tvTaluk,tvDistrict,tvMobile,tvEmailID,tvLatAd,tvLongAd;
    Button btnFetchLocation,btnViewLocation;

    TextView tvCLatAd,tvCLongAd;

    GpsTracker1 gpsTracker;

    Button btnAccept,btnReject;


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

        tvUserName=(TextView) findViewById(R.id.tvUserName);
        tvAddressLine1=(TextView) findViewById(R.id.tvAddressLine1);
        tvAddressLine2=(TextView) findViewById(R.id.tvAddressLine2);
        tvTaluk=(TextView) findViewById(R.id.tvTaluk);
        tvDistrict=(TextView) findViewById(R.id.tvDistrict);
        tvMobile=(TextView) findViewById(R.id.tvMobile);
        tvEmailID=(TextView) findViewById(R.id.tvEmailID);
        tvLatAd=(TextView) findViewById(R.id.tvLatAd);
        tvLongAd=(TextView) findViewById(R.id.tvLongAd);

        btnFetchLocation=(Button) findViewById(R.id.btnFetchLocation);
        btnViewLocation=(Button) findViewById(R.id.btnViewLocation);

        tvCLatAd=(TextView) findViewById(R.id.tvCLatAd);
        tvCLongAd=(TextView) findViewById(R.id.tvCLongAd);

        btnAccept=(Button) findViewById(R.id.btnAccept);
        btnReject=(Button) findViewById(R.id.btnReject);

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
                    UserID = rs.getString("UserID").toString();
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

                String query1 = "Select * from tblUsers where Mobile='" + UserID + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvUserName.setText(rs.getString("UserName").toString());
                    tvAddressLine1.setText(rs.getString("AddressLine1").toString());
                    tvAddressLine2.setText(rs.getString("AddressLine2").toString());
                    tvTaluk.setText(rs.getString("Taluk").toString());
                    tvDistrict.setText(rs.getString("District").toString());
                    tvMobile.setText(rs.getString("Mobile").toString());
                    tvEmailID.setText(rs.getString("EmailID").toString());
                    tvLatAd.setText(rs.getString("LatAd").toString());
                    tvLongAd.setText(rs.getString("LongAd").toString());
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

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query2 = "Update tblOrders set Status='Delivered' where OrderID='" + tvOrderID.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Updated Successfully. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(OrderDetailsActivity.this, OrdersListActivity.class);
                        finish();
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query2 = "Update tblOrders set Status='Rejected' where OrderID='" + tvOrderID.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Updated Successfully. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(OrderDetailsActivity.this, OrdersListActivity.class);
                        finish();
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

        btnFetchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double latitude=0;
                double longitude=0;
                try {
                    if
                    (ContextCompat.checkSelfPermission(getApplicationContext(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            !=
                            PackageManager.PERMISSION_GRANTED ) {
                        ActivityCompat.requestPermissions(OrderDetailsActivity.this, new

                                String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                gpsTracker = new GpsTracker1(OrderDetailsActivity.this);
                if(gpsTracker.canGetLocation()){
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();
                    tvCLatAd.setText(String.valueOf(latitude));
                    tvCLongAd.setText(String.valueOf(longitude));
                }else {
                    gpsTracker.showSettingsAlert();
                }

                if(latitude==0.0  && longitude==0.0)
                {
                    btnViewLocation.setVisibility(View.INVISIBLE);
                    btnFetchLocation.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "IF", Toast.LENGTH_LONG).show();
                }
                else {
                    btnFetchLocation.setVisibility(View.INVISIBLE);
                    btnViewLocation.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "Else", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sdestination = tvLatAd.getText().toString().trim()+ ", " + tvLongAd.getText().toString();
                String  ssource = tvCLatAd.getText().toString().trim() + ", "+ tvCLongAd.getText().toString();


                if (ssource.equals("") && sdestination.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter both location", Toast.LENGTH_SHORT).show();
                } else
                {
                    DisplayTrack(ssource, sdestination);
                }

            }
        });


        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DisplayTrack(String ssource, String sdestination)
    {
        try
        {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + ssource + "/" + sdestination);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //   String URI= String.format(Locale.ENGLISH, "http://google.com/maps?q=loc:%f ,%f", 75.909032,14.432432);
            //  Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse(URI));
            //   startActivity(intent);
            Uri URI= Uri.parse("http://maps.googleapis.com/maps/api/geocode/json?latlng=lat,long&sesor=true");
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e)
        {
            Uri uri = Uri.parse("https://play.google.com/store/apps/dtails?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),OrdersListActivity.class));
    }
}