package com.example.hmfse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditItemActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    TextView tvID,tvItem,tvCategory,tvSubCategory,tvRemarks;
    EditText etPrice;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registered Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvItem=(TextView) findViewById(R.id.tvItem);
        tvID=(TextView) findViewById(R.id.tvID);
        tvCategory=(TextView) findViewById(R.id.tvCategory);
        tvSubCategory=(TextView) findViewById(R.id.tvSubCategory);
        tvRemarks=(TextView) findViewById(R.id.tvRemarks);

        etPrice=(EditText) findViewById(R.id.etPrice);

        btnUpdate=(Button) findViewById(R.id.btnUpdate);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                Intent intent = getIntent();
                if(intent != null) {
                    tvID.setText(intent.getExtras().getString("ID"));
                } else {
                    tvID.setText("Data not available");
                }

                String query1 = "Select * from tblItem where ID='" + tvID.getText().toString() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvItem.setText(rs.getString("Item").toString());
                    tvCategory.setText(rs.getString("Category").toString());
                    tvSubCategory.setText(rs.getString("SubCategory").toString());
                    etPrice.setText(rs.getString("Price").toString());
                    tvRemarks.setText(rs.getString("Remarks").toString());
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

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query2 = "Update tblItem set Price='" + etPrice.getText().toString() + "' " +
                                "where ID='" + tvID.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Updated Successfully. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(EditItemActivity.this, ItemsListActivity.class);
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
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),ItemsListActivity.class));
    }
}