package com.example.hmfse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddItemActivity extends NavigationDrawerBaseActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    Button uploadpic;
    ImageView imagebox;
    ProgressBar progressBar;

    Spinner spinnerCategory,spinnerSubCategory;
    EditText etPrice,etRemarks,etItem;

    byte[] byteArray;
    String encodedImage;

    ConnectionClass connectionClass = new ConnectionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Post Order");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uploadpic = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imagebox = (ImageView) findViewById(R.id.imageView);

        progressBar.setVisibility(View.GONE);

        spinnerCategory=(Spinner) findViewById(R.id.spinnerCategory);
        spinnerSubCategory=(Spinner) findViewById(R.id.spinnerSubCategory);
        etPrice=(EditText) findViewById(R.id.etPrice);
        etRemarks=(EditText) findViewById(R.id.etRemarks);
        etItem=(EditText) findViewById(R.id.etItem);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {
                String query = "SELECT distinct(Category) FROM tblSubCategory";
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

                String SCategory = spinnerCategory.getSelectedItem().toString();

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query = "SELECT distinct(SubCategory) From tblSubCategory where Category='" + SCategory + "'";
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

        uploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !Environment.getExternalStorageState().equals(Environment.MEDIA_CHECKING)) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                } else {
                    Toast.makeText(AddItemActivity.this, "No activity found to perform this task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            progressBar.setVisibility(View.VISIBLE);
            Bitmap originBitmap = null;
            Uri selectedImage = data.getData();
            Toast.makeText(AddItemActivity.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
            InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
                originBitmap = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage().toString());
            }
            if (originBitmap != null) {
                this.imagebox.setImageBitmap(originBitmap);
                Log.w("Image Setted in", "Done Loading Image");
                try {
                    Bitmap image = ((BitmapDrawable) imagebox.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                    byteArray = byteArrayOutputStream.toByteArray();
                    encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    UploadImage uploadImage = new UploadImage();
                    uploadImage.execute("");

                } catch (Exception e) {
                    Log.w("OOooooooooo", "exception");
                }
                Toast.makeText(AddItemActivity.this, "Conversion Done", Toast.LENGTH_SHORT).show();
            }

        } else {
            System.out.println("Error Occured");
        }
    }

    public class UploadImage extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPostExecute(String r)
        {

            progressBar.setVisibility(View.GONE);
            Toast.makeText(AddItemActivity.this , "Image Inserted Succesfully" , Toast.LENGTH_LONG).show();

        }
        @Override
        protected String doInBackground(String... params) {

            String msg = "unknown";
            try {
                Connection conn = connectionClass.CONN(); //Connection Object


                if (conn == null) {
                    Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    return "";
                }

                int intID;
                String query1 = "Select ID from tblItem order by 1 Desc";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()){
                    String inID=rs.getString("ID");
                    intID=Integer.parseInt(inID);
                    intID=intID+1;

                }else{
                    intID=1;
                }

                //fetching value from shared preference
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                String user_id = sharedPreferences.getString("user_id", "");


               String query = "insert into tblItem (ID,Item,Category,SubCategory,Price,Pic,Remarks,EntrepreneurID) " +
                        "values (" + intID + ",'" + etItem.getText().toString() + "'," +
                        "'" + spinnerCategory.getSelectedItem().toString() + "'," +
                        "'" + spinnerSubCategory.getSelectedItem().toString() + "'," +
                        "" + etPrice.getText().toString() + ",'" + encodedImage + "'," +
                        "'" + etRemarks.getText().toString() + "','" + user_id + "')";


                PreparedStatement preStmt = conn.prepareStatement(query);
                preStmt.executeUpdate();
                msg = "Inserted Successfully";
            } catch (SQLException ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 1:", msg );
            } catch (IOError ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 2:", msg );
            } catch (AndroidRuntimeException ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 3:", msg );
            } catch (NullPointerException ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 4:", msg );
            } catch (Exception ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 5:", msg );
            }
            System.out.println( msg );
            return "";

        }
    }
    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),ItemsListActivity.class));
    }

}