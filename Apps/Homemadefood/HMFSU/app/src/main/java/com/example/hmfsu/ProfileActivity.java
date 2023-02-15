package com.example.hmfsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    EditText etUserName,etAddressLine1,etAddressLine2,etTaluk,etDistrict,etMobile,etEmailID;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registered Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etUserName=(EditText)findViewById(R.id.etUserName);
        etAddressLine1=(EditText)findViewById(R.id.etAddressLine1);
        etAddressLine1=(EditText)findViewById(R.id.etAddressLine1);
        etAddressLine2=(EditText)findViewById(R.id.etAddressLine2);
        etTaluk=(EditText)findViewById(R.id.etTaluk);
        etDistrict=(EditText)findViewById(R.id.etDistrict);
        etMobile=(EditText)findViewById(R.id.etMobile);
        etEmailID=(EditText)findViewById(R.id.etEmailID);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);


        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                //fetching value from shared preference
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                String user_name = sharedPreferences.getString("user_name", "");
                String user_id = sharedPreferences.getString("user_id", "");

                String query1 = "Select * from tblUsers where Mobile='" + user_id + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    etUserName.setText(rs.getString("UserName").toString());
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

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!setValidation())
                    return;

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query2 = "Update tblUsers set UserName='" + etUserName.getText().toString() + "'," +
                                "AddressLine1='" + etAddressLine1.getText().toString() + "'," +
                                "AddressLine2='" + etAddressLine2.getText().toString() + "'," +
                                "Taluk='" + etTaluk.getText().toString() + "'," +
                                "District='" + etDistrict.getText().toString() + "'," +
                                "EmailID='" + etEmailID.getText().toString().trim() + "' " +
                                "where Mobile='" + etMobile.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Updated Successfully. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
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
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }

    private boolean setValidation() {
        String username = etUserName.getText().toString().trim();
        String addressline1 = etAddressLine1.getText().toString().trim();
        String taluk = etTaluk.getText().toString().trim();
        String district = etDistrict.getText().toString().trim();

        boolean isUserName, isAddressLine1,isTaluk,isDistrict,isEmailID;

        if (username.isEmpty()) {
            etUserName.setError("Farmer Name can't be empty");
            isUserName = false;
        } else {
            etUserName.setError(null);
            isUserName = true;
        }

        if (addressline1.isEmpty()) {
            etAddressLine1.setError("AddressLine1 can't be empty");
            isAddressLine1 = false;
        } else {
            etAddressLine1.setError(null);
            isAddressLine1 = true;
        }

        if (taluk.isEmpty()) {
            etTaluk.setError("City can't be empty");
            isTaluk= false;
        } else {
            etTaluk.setError(null);
            isTaluk=true;
        }

        if (district.isEmpty()) {
            etDistrict.setError("State can't be empty");
            isDistrict= false;
        } else {
            etDistrict.setError(null);
            isDistrict=true;
        }

        if(isUserName==true && isAddressLine1==true && isTaluk==true && isDistrict==true)
            return true;
        else
            return false;
    }
}