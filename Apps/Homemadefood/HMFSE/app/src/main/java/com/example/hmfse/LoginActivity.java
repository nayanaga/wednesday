package com.example.hmfse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;

    EditText txtPassword,txtUserID;
    ConnectionClass connectionClass = new ConnectionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserID=findViewById(R.id.txtUserID);
        txtPassword = findViewById(R.id.txtPassword);

        findViewById(R.id.registering).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        imageView = findViewById(R.id.login);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setValidation())
                    return;

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    }
                    else {
                        String query1 = "Select * from tblLogin where UserID='" + txtUserID.getText().toString() + "' and Password='" + txtPassword.getText().toString() + "' and UserType='Entrepreneur'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        ResultSet rs = preparedStatement2.executeQuery();
                        if (rs.next()){

                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user_id", rs.getString("UserID").toString());
                            editor.putString("user_name", rs.getString("UserName").toString());
                            editor.commit();

                            Toast.makeText(getApplicationContext(), "logged in successfully", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Invalid UserID or Password", Toast.LENGTH_LONG).show();
                        }
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
        System.exit(0);
    }

    private boolean setValidation() {
        String userid = txtUserID.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        boolean isUserID, isPassword;

        if (userid.isEmpty()) {
            txtUserID.setError("User ID cannot be empty");
            isUserID = false;
        } else {
            txtUserID.setError(null);
            isUserID = true;
        }

        if (password.isEmpty()) {
            txtPassword.setError("Password cannot be empty");
            isPassword = false;
        } else {
            txtPassword.setError(null);
            isPassword = true;
        }

        if(isUserID==true && isPassword==true)
            return true;
        else
            return false;
    }
}