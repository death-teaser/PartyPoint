package com.example.root.partypoint;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class restaurant_deatils extends AppCompatActivity {
    ImageView imv;
    TextView name;
    TextView address;
    TextView cuisines;
    TextView cost;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_deatils);
        imv = (ImageView)findViewById(R.id.img_2);
        name = (TextView)findViewById(R.id.res_name);
        address = (TextView)findViewById(R.id.res_add);
        cuisines = (TextView)findViewById(R.id.res_cui);
        cost = (TextView)findViewById(R.id.res_cost);
        btn = (Button)findViewById(R.id.button7);

        final restaurantInfo restaurant = (restaurantInfo)getIntent().getSerializableExtra("details");
        Picasso.with(this).load(restaurant.getPhotos_url()).placeholder(R.drawable.placeholder).into(imv);
        name.setText(restaurant.getName());
        address.setText(restaurant.getLocation());
        cuisines.setText(restaurant.getCuisines());
        cost.setText(restaurant.getAverage_cost_for_two());

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(restaurant.getUrl()));
                startActivity(myWebLink);
            }
        });

    }
}
