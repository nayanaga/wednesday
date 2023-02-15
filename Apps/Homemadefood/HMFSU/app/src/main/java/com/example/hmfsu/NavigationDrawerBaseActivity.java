package com.example.hmfsu;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmfsu.adapters.NavigationDrawerAdapter;

public class NavigationDrawerBaseActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public ListView drawerList;
    public String[] layers;

    protected void OnCreateDrawer() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_draw_open, R.string.navigation_draw_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(NavigationDrawerBaseActivity.this, "CLOSED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(NavigationDrawerBaseActivity.this, "OPENED", Toast.LENGTH_SHORT).show();
            }
        };

        //Setting the actionbarToggle to drawer layout
        if(actionBarDrawerToggle != null)
            drawerLayout.setDrawerListener(actionBarDrawerToggle);

        layers = new String[7];
        layers[0] = "Home";
        layers[1] = "Orders";
        layers[2] = "Stores";
        layers[3] = "Location";
        layers[4] = "Profile";
        layers[5] = "About Us";
        layers[6] = "Logout";

        drawerList = (ListView) findViewById(R.id.navigation_drawer_items_list);
        View header = getLayoutInflater().inflate(R.layout.navigation_drawer_header_layout, null);
        ImageView profile_image = (ImageView) header.findViewById(R.id.profile_image);

        TextView nav_username_textview = (TextView) header.findViewById(R.id.nav_username);

        TextView nav_user_mobile_number_textview = (TextView) header.findViewById(R.id.nav_user_mobile_number);

        //fetching value from shared preference
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
        nav_username_textview.setText(sharedPreferences.getString("user_name", ""));
        nav_user_mobile_number_textview.setText(sharedPreferences.getString("user_id", ""));

        drawerList.addHeaderView(header, null, false);
        drawerList.setAdapter(new NavigationDrawerAdapter(this, layers));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle != null && actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if(actionBarDrawerToggle != null)
            actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        if(actionBarDrawerToggle != null)
            actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

}