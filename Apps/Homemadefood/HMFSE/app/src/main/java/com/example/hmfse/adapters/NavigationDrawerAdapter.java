package com.example.hmfse.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmfse.AboutUsActivity;
import com.example.hmfse.HomeActivity;
import com.example.hmfse.ItemsListActivity;
import com.example.hmfse.LoginActivity;
import com.example.hmfse.OrdersListActivity;
import com.example.hmfse.ProfileActivity;
import com.example.hmfse.R;
import com.example.hmfse.UpdateLocationActivity;

public class NavigationDrawerAdapter extends BaseAdapter {
    private Context mContext = null;
    private String[] menuItemList = null;
    private LayoutInflater inflater = null;

    public NavigationDrawerAdapter(Context context, String[] list) {
        mContext = context;
        menuItemList = list;
    }
    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.navigation_drawer_item_view_layout, null);

        TextView textView = rootView.findViewById(R.id.nav_menu_item_title);
        textView.setText(menuItemList[position]);
        rootView.setId(position);

        rootView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = null;

                switch (menuItemList[v.getId()]) {

                    case "Home":
                        i = new Intent(mContext, HomeActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        break;

                    case "Orders":
                        i = new Intent(mContext, OrdersListActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        break;

                    case "Items":
                        i = new Intent(mContext, ItemsListActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        break;

                    case "Location":
                        i = new Intent(mContext, UpdateLocationActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        break;

                    case "Profile":
                        i = new Intent(mContext, ProfileActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        break;

                    case "About Us":
                        i = new Intent(mContext, AboutUsActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        break;


                    case "Logout":
                        i = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(i);
                        ((Activity) mContext).finish();
                        break;

                    default:
                        Toast.makeText(mContext, "Something Wrong", Toast.LENGTH_SHORT).show();
                        break;


                }

            }

        });
        return rootView;
    }
}
