package com.example.hmfse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterActivity extends AppCompatActivity {

    EditText etEntrepreneur,etOwnerName,etAddressLine1,etAddressLine2,etTaluk,etDistrict,etMobile,etEmailID;

    ConnectionClass connectionClass = new ConnectionClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEntrepreneur=(EditText)findViewById(R.id.etEntrepreneur);
        etOwnerName=(EditText)findViewById(R.id.etOwnerName);
        etAddressLine1=(EditText)findViewById(R.id.etAddressLine1);
        etAddressLine2=(EditText)findViewById(R.id.etAddressLine2);
        etTaluk=(EditText)findViewById(R.id.etTaluk);
        etDistrict=(EditText)findViewById(R.id.etDistrict);
        etMobile=(EditText)findViewById(R.id.etMobile);
        etEmailID=(EditText)findViewById(R.id.etEmailID);



        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setValidation())
                    return;

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String UserID = "";
                        String query1 = "Select ID from tblEntrepreneur order by 1 Desc";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        ResultSet rs = preparedStatement2.executeQuery();
                        if (rs.next()) {
                            String inID = rs.getString("ID");
                            int intID;
                            intID = Integer.parseInt(inID.substring(inID.indexOf("-") + 1).toString());
                            intID = intID + 1;

                            String unpadded = Integer.toString(intID);
                            String padded = "0000".substring(unpadded.length()) + unpadded;

                            UserID = "Entr-" + padded;

                        } else {
                            UserID = "Entr-0001";
                        }

                        String query2 = "Insert into tblEntrepreneur (ID,Entrepreneur,OwnerName,AddressLine1,AddressLine2,Taluk,District," +
                                "Mobile,EmailID,Status,LatAd,LongAd) values ('" + UserID + "','" + etEntrepreneur.getText().toString().trim() + "'," +
                                "'" + etOwnerName.getText().toString().trim() + "'," +
                                "'" + etAddressLine1.getText().toString().trim() + "','" + etAddressLine2.getText().toString().trim() + "'," +
                                "'" + etTaluk.getText().toString().trim()  + "','" + etDistrict.getText().toString().trim() + "'," +
                                "'" + etMobile.getText().toString().trim()  + "','" + etEmailID.getText().toString().trim()  + "'," +
                                "'New','','')";
                        preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();


                        Toast.makeText(getApplicationContext(), "Registeration Request is accepted Successfully.", Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
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
        findViewById(R.id.login1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    private boolean setValidation() {

        String username = etEntrepreneur.getText().toString().trim();
        String addressline1 = etAddressLine1.getText().toString().trim();
        String taluk = etTaluk.getText().toString().trim();
        String district = etDistrict.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String emailid = etEmailID.getText().toString().trim();

        boolean isUserName, isAddressLine1,isAddressLine2,isTaluk,isDistrict,isMobile,isEmailID;

        if (username.isEmpty()) {
            etEntrepreneur.setError("Name can't be empty");
            isUserName = false;
        } else {
            etEntrepreneur.setError(null);
            isUserName = true;
        }

        if (addressline1.isEmpty()) {
            etAddressLine1.setError("Address Line1 can't be empty");
            isAddressLine1 = false;
        } else {
            etAddressLine1.setError(null);
            isAddressLine1 = true;
        }

        if (taluk.isEmpty()) {
            etTaluk.setError("City can't be empty");
            isTaluk = false;
        } else {
            etTaluk.setError(null);
            isTaluk = true;
        }

        if (district.isEmpty()) {
            etDistrict.setError("State can't be empty");
            isDistrict = false;
        } else {
            etDistrict.setError(null);
            isDistrict = true;
        }

        if (mobile.isEmpty()) {
            etMobile.setError("Mobile can't be empty");
            isMobile= false;
        }
        else if (mobile.length()!=10) {
            etMobile.setError("Invalid Mobile number");
            isMobile= false;
        }
        else {
            etMobile.setError(null);
            isMobile= true;
        }

        if (emailid.isEmpty()) {
            etEmailID.setError("Email ID can't be empty");
            isEmailID = false;
        } else {
            etEmailID.setError(null);
            isEmailID = true;
        }


        if(isUserName==true && isAddressLine1==true && isTaluk==true && isDistrict==true && isMobile==true && isEmailID==true)
            return true;
        else
            return false;
    }
}