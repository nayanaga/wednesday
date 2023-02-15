package com.example.hmfsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hmfsu.adapters.ItemAdapter;
import com.example.hmfsu.data_models.ItemDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemsListActivity extends NavigationDrawerBaseActivity {
    ConnectionClass connectionClass = new ConnectionClass();

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<ItemDataType> ex;

    Button btnMoveCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Items List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        btnMoveCart=(Button) findViewById(R.id.btnMoveCart);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else
            {
                String Mobile=null;
                Intent intent = getIntent();
                if(intent != null) {
                    Mobile = intent.getExtras().getString("Mobile");
                }

                String query="SELECT * from tblItem where EntrepreneurID='" + Mobile + "'";

                PreparedStatement stmt = conn.prepareStatement(query);
                rss = stmt.executeQuery();
                ex = new ArrayList<>();
                while(rss.next()) {
                    //Log.d("ResultSet", rs.getString("ID"));
                    ItemDataType dt = new ItemDataType();
                    dt.setID(rss.getString("ID").toString());
                    dt.setItem(rss.getString("Item").toString());
                    dt.setPrice(rss.getString("Price").toString());
                    dt.setImage(rss.getString("Pic").toString());
                    dt.setEntrepreneurID(rss.getString("EntrepreneurID").toString());
                    ex.add(dt);
                }
                ItemAdapter eadapters = new ItemAdapter(getApplicationContext(),ex);
                recyclerView.setAdapter(eadapters);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }

        btnMoveCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),CartListActivity.class));
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