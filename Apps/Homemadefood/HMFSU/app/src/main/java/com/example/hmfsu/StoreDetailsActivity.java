package com.example.hmfsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StoreDetailsActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    TextView tvStoreName,tvOwnerName,tvAddressLine1,tvAddressLine2,tvTaluk,tvDistrict,tvMobile,tvEmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registered Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvStoreName=(TextView) findViewById(R.id.tvStoreName);
        tvOwnerName=(TextView)findViewById(R.id.tvOwnerName);
        tvAddressLine1=(TextView)findViewById(R.id.tvAddressLine1);
        tvAddressLine2=(TextView)findViewById(R.id.tvAddressLine2);
        tvTaluk=(TextView)findViewById(R.id.tvTaluk);
        tvDistrict=(TextView)findViewById(R.id.tvDistrict);
        tvMobile=(TextView)findViewById(R.id.tvMobile);
        tvEmailID=(TextView)findViewById(R.id.tvEmailID);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                Intent intent = getIntent();
                if(intent != null) {
                    tvMobile.setText(intent.getExtras().getString("Mobile"));
                } else {
                    tvMobile.setText("Data not available");
                }

                String query1 = "Select * from tblEntrepreneur where Mobile='" + tvMobile.getText().toString() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvStoreName.setText(rs.getString("Entrepreneur").toString());
                    tvOwnerName.setText(rs.getString("OwnerName").toString());
                    tvAddressLine1.setText(rs.getString("AddressLine1").toString());
                    tvAddressLine2.setText(rs.getString("AddressLine2").toString());
                    tvTaluk.setText(rs.getString("Taluk").toString());
                    tvDistrict.setText(rs.getString("District").toString());
                    tvMobile.setText(rs.getString("Mobile").toString());
                    tvEmailID.setText(rs.getString("EmailID").toString());
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
}