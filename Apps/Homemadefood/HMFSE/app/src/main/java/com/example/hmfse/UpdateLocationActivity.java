package com.example.hmfse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmfse.locations.GpsTracker;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateLocationActivity extends NavigationDrawerBaseActivity {

    GpsTracker gpsTracker;

    TextView tvLatitude,tvLongitude;

    Button btnFetchLocation,btnUpdate;
    ConnectionClass connectionClass = new ConnectionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);

        super.OnCreateDrawer();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Update Location Activity");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvLatitude=(TextView)findViewById(R.id.tvLatitude);
        tvLongitude=(TextView)findViewById(R.id.tvLongitude);

        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnFetchLocation=(Button)findViewById(R.id.btnFetchLocation);
    }

    public void fetchLocation(View view)
    {
        double latitude=0;
        double longitude=0;
        try {
            if
            (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    !=
                    PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new

                        String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        gpsTracker = new GpsTracker(UpdateLocationActivity.this);
        if(gpsTracker.canGetLocation()){
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            tvLatitude.setText(String.valueOf(latitude));
            tvLongitude.setText(String.valueOf(longitude));
        }else {
            gpsTracker.showSettingsAlert();
        }

        if(latitude==0.0  && longitude==0.0)
        {
            Toast.makeText(getApplicationContext(), "IF", Toast.LENGTH_LONG).show();

            btnUpdate.setVisibility(View.INVISIBLE);
            btnFetchLocation.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(getApplicationContext(), "Else", Toast.LENGTH_LONG).show();
            btnUpdate.setVisibility(View.VISIBLE);
            btnFetchLocation.setVisibility(View.INVISIBLE);
        }
    }


    public void saveClickOrder(View view)
    {
        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {
                String UserID="";

                //fetching value from shared preference
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                String user_id = sharedPreferences.getString("user_id", "");

                String query2 = "Update tblEntrepreneur set LatAd='" + tvLatitude.getText().toString() + "'," +
                        "LongAd='" + tvLongitude.getText().toString() + "' where Mobile='" + user_id + "'";

                PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                preparedStatement2.executeUpdate();

                Toast.makeText(getApplicationContext(), "Updated Successfully.", Toast.LENGTH_LONG).show();

                btnUpdate.setVisibility(View.INVISIBLE);
                btnFetchLocation.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
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
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}