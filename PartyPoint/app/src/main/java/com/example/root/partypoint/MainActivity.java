package com.example.root.partypoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText et;
    public static final String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRAM = "com.example";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onsearch(View v){
        et = (EditText)findViewById(R.id.query);
        String query = et.getText().toString();

        if(query.equals("")){
            Toast.makeText(this, "Please Insert any city name first", Toast.LENGTH_SHORT).show();
        }

        else{

            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonrequest = new JsonObjectRequest(
                    Request.Method.GET, "https://developers.zomato.com/api/v2.1/locations?query="+query, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray contacts = response.getJSONArray("location_suggestions");
                        JSONObject c = contacts.getJSONObject(0);
                        String entity_id = c.getString("entity_id");
                        String entity_type = c.getString("entity_type");
                        Intent i = new Intent(MainActivity.this,place_list.class);
                        i.putExtra(EXTRA_MESSAGE1,entity_id);
                        i.putExtra(EXTRAM,entity_type);
                        startActivity(i);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "request succesfull", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
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

}
