package com.example.root.partypoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class place_list extends AppCompatActivity {
    ArrayList<restaurantInfo> restaurantList;
    RestaurantAdapter adapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        Intent i = getIntent();
        final String entity_id = i.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        final String entity_type = i.getStringExtra(MainActivity.EXTRAM);

        Toast.makeText(this, "entity_type "+entity_id, Toast.LENGTH_SHORT).show();

        lv = (ListView) findViewById(R.id.list_1);

        restaurantList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonrequest = new JsonObjectRequest(
                Request.Method.GET,"https://developers.zomato.com/api/v2.1/search?entity_id="+entity_id+"&entity_type="+ entity_type, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resList = response.getJSONArray("restaurants");
                    for (int i = 0; i < resList.length(); i++) {
                        JSONObject x = resList.getJSONObject(i);
                        JSONObject a = x.getJSONObject("restaurant");

                        String name = a.getString("name");
                        String url = a.getString("url");
                        String cuisines = a.getString("cuisines");
                        String cost = a.getString("average_cost_for_two");
                        String photos_url;
                        try{
                            photos_url = a.getString("thumb");
                        }
                        catch (Exception e){
                            photos_url = "http://treliorestaurant.com/wp-content/uploads/2017/08/food2.jpg";
                        }
                        Log.i("mytag url",photos_url);
                        JSONObject l = a.getJSONObject("location");
                        String location = l.getString("address");

                        restaurantInfo restaurant = new restaurantInfo();
                        restaurant.setName(name);
                        restaurant.setUrl(url);
                        restaurant.setCuisines(cuisines);
                        restaurant.setLocation(location);
                        restaurant.setAverage_cost_for_two(cost);
                        restaurant.setPhotos_url(photos_url);
                        restaurantList.add(restaurant);
//                        Log.i("mytag name",name);
//                        Log.i("mytag url",url);
                    }
                    adapter = new RestaurantAdapter(place_list.this,restaurantList);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            restaurantInfo restaurant = restaurantList.get(i);
                            Intent in = new Intent(place_list.this,restaurant_deatils.class);
                            in.putExtra("details",restaurant);
                            startActivity(in);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(place_list.this, "Succesfull: "+restaurantList.size(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(place_list.this, "failure", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User-Agent", "curl/7.30.0");
                params.put("Accept", "application/json");
                params.put("user_key", "fbaf72a2a54efe84d17c617d811ff818");

                return params;
            }
        };
        queue.add(jsonrequest);

    }
}
