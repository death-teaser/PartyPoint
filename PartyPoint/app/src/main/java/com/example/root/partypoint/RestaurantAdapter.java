package com.example.root.partypoint;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 30/9/17.
 */

public class RestaurantAdapter extends BaseAdapter {
    Context context;
    ArrayList<restaurantInfo> restaurantList;

    public RestaurantAdapter(Context place_list, ArrayList<restaurantInfo> restaurantList) {
        this.context = place_list;
        this.restaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public restaurantInfo getItem(int i) {
        return restaurantList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = View.inflate(context, R.layout.restaurant_list_layout,null);
        }
        restaurantInfo restaurant = restaurantList.get(i);
        ImageView imv = (ImageView)view.findViewById(R.id.img_1);
        TextView tv = (TextView)view.findViewById(R.id.text_name);
        Picasso.with(context).load(restaurant.getPhotos_url()).placeholder(R.drawable.placeholder).into(imv);
        tv.setText(restaurant.getName());
        return view;
    }
}
