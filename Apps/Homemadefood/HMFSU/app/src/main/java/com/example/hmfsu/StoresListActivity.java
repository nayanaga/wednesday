package com.example.hmfsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hmfsu.adapters.StoreAdapter;
import com.example.hmfsu.data_models.StoreDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StoresListActivity extends NavigationDrawerBaseActivity {
    ConnectionClass connectionClass = new ConnectionClass();

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<StoreDataType> ex;

    String LatAdU=null,LongAdU=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores_list);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Stores List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                //fetching value from shared preference
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                String user_id = sharedPreferences.getString("user_id", "");

                String query1 = "Select * from tblUsers where Mobile='" + user_id + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    LatAdU=rs.getString("LatAd").toString();
                    LongAdU=rs.getString("LongAd").toString();
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

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else
            {
                       /* String query = "SELECT ID, FuelStation, ROUND(6371 * ACOS(COS(RADIANS('" + txtLatitude.getText().toString() + "')) * COS(RADIANS(LatAd)) * COS(RADIANS(LongAd) - RADIANS('" + txtLongitude.getText().toString() + "')) + SIN(RADIANS('" + txtLatitude.getText().toString() + "')) * SIN(RADIANS(LatAd))), 2) AS distance  " +
                                "FROM     tblFuelStation ORDER BY distance"; */

                String query="SELECT ID, Entrepreneur, Mobile, distance " +
                        "FROM     (SELECT TOP (100) PERCENT ID, Entrepreneur, Mobile, ROUND(6371 * ACOS(COS(RADIANS('" + LatAdU + "')) * COS(RADIANS(LatAd)) * COS(RADIANS(LongAd) - RADIANS('" + LongAdU + "')) + SIN(RADIANS('" + LatAdU + "')) * SIN(RADIANS(LatAd))), 2) AS distance " +
                        "                  FROM      tblEntrepreneur " +
                        "                  ORDER BY distance) AS vt " +
                        "WHERE  (distance < 15) " +
                        "ORDER BY distance";

                PreparedStatement stmt = conn.prepareStatement(query);
                rss = stmt.executeQuery();
                ex = new ArrayList<>();
                while(rss.next()) {
                    //Log.d("ResultSet", rs.getString("ID"));
                    StoreDataType dt = new StoreDataType();
                    dt.setID(rss.getString("ID").toString());
                    dt.setEntrepreneur(rss.getString("Entrepreneur").toString());
                    dt.setDistance(rss.getString("distance").toString());
                    dt.setMobile(rss.getString("Mobile").toString());
                    ex.add(dt);
                }
                StoreAdapter eadapters = new StoreAdapter(getApplicationContext(),ex);
                recyclerView.setAdapter(eadapters);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }
    }
}