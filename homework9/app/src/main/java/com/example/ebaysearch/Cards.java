package com.example.ebaysearch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Cards extends AppCompatActivity {
    String TAG = "user Cards";
    RequestQueue mQueue = Volley.newRequestQueue(this);
    JSONObject myJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
//        getActionBar().setTitle("Hello world App");
//        getSupportActionBar().setTitle("Hello world App");  // provide compatibility to all the versions

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String url;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                url= null;
            } else {
                url= extras.getString("url");
            }
        } else {
            url= (String) savedInstanceState.getSerializable("url");
        }

        Log.d(TAG, "onCreate: the url received is: " + url);

        JSONObject myJSON = new JSONObject();
        try {
            myJSON.put("hello","hi");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate: before fetching JSON: " + myJSON);
        myJSON = getJson(url);
        Log.d(TAG, "onCreate: after fetching JSON: " + myJSON);
    }

    private JSONObject getJson(String url) {
//        final JSONObject[] myJSON = new JSONObject[1];

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        myJSON = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        return myJSON;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;

//        finish();

//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
    }
}