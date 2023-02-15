package com.example.hmfse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hmfse.adapters.ItemAdapter;
import com.example.hmfse.data_models.ItemDataType;

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

    Button btnAddItem;
    Spinner spinnerCategory,spinnerSubCategory;

    String SCategory=null;
    String SSubCategory=null;

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


        btnAddItem=(Button) findViewById(R.id.btnAddItem);

        spinnerCategory=(Spinner) findViewById(R.id.spinnerCategory);
        spinnerSubCategory=(Spinner) findViewById(R.id.spinnerSubCategory);

        //fetching value from shared preference
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
        String user_id = sharedPreferences.getString("user_id", "");

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {
                String query = "SELECT distinct(Category) FROM tblItem where EntrepreneurID='" + user_id + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query);
                ResultSet rs = preparedStatement2.executeQuery();
                ArrayList<String> data = new ArrayList<String>();

                String Category;

                while (rs.next()){
                    Category = rs.getString("Category");
                    data.add(Category);
                }
                String[] array = data.toArray(new String[0]);
                ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
                spinnerCategory.setAdapter(NoCoreAdapter);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SCategory = spinnerCategory.getSelectedItem().toString();

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query = "SELECT distinct(SubCategory) From tblItem where Category='" + SCategory + "' and EntrepreneurID='" + user_id + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query);
                        ResultSet rs = preparedStatement2.executeQuery();
                        ArrayList<String> data1 = new ArrayList<String>();

                        String SubCategory;

                        while (rs.next()){
                            SubCategory = rs.getString("SubCategory");
                            data1.add(SubCategory);
                        }
                        String[] array = data1.toArray(new String[0]);
                        ArrayAdapter NoCoreAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data1);
                        spinnerSubCategory.setAdapter(NoCoreAdapter);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SSubCategory = spinnerSubCategory.getSelectedItem().toString();

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {

                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else
                    {
                        //fetching value from shared preference
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                        String user_id = sharedPreferences.getString("user_id", "");

                        String query="SELECT * from tblItem where EntrepreneurID='" + user_id + "' and Category='" + SCategory + "' and SubCategory='" + SSubCategory + "'";

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ItemsListActivity.this,AddItemActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        Intent intent=new Intent(ItemsListActivity.this,HomeActivity.class);
        finish();
        startActivity(intent);
    }
}