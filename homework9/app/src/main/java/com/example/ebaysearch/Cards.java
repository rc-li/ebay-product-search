package com.example.ebaysearch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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

public class Cards extends AppCompatActivity {
    String TAG = "user Cards";
    private RequestQueue mQueue;
    private JSONObject myJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String url;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                url = null;
            } else {
                url = extras.getString("url");
            }
        } else {
            url = (String) savedInstanceState.getSerializable("url");
        }

        Log.d(TAG, "onCreate: the url received is: " + url);
        mQueue = Volley.newRequestQueue(this);
        getJson(url);

        ListView list = findViewById(R.id.listView);
        ArrayList<Card> names = new ArrayList<Card>();
        for (int i = 0; i < 50; i++) {
            names.add(new Card());
        }
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.adapter_view_layout , names);
//        list.setAdapter(adapter);
        CardListAdapter adapter = new CardListAdapter(this, R.layout.adapter_view_layout, names);
        list.setAdapter(adapter);

    }

    private ArrayList<Card> parseJSON(JSONObject myJSON) {
        ArrayList<Card> cards = new ArrayList<Card>();
        Log.d(TAG, "onCreate: Started making cards");
        ListView mListView = findViewById(R.id.listView);
        try {
            JSONArray items = myJSON.getJSONObject("searchResult").getJSONArray("item");
            for (int i = 0; i < items.length(); i++) {
                String galleryURL = items.getJSONObject(i).getJSONArray("galleryURL").getString(0);
                String itemTitle = items.getJSONObject(i).getJSONArray("title").getString(0);
                String itemCondition = items.getJSONObject(i).getJSONArray("condition").getJSONObject(0).getJSONArray("conditionDisplayName").getString(0);
                String isTopRated = items.getJSONObject(i).getJSONArray("topRatedListing").getString(0);
                String shippingCost = items.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0).getJSONArray("shippingServiceCost").getJSONObject(0).getString("__value__");
                String price = items.getJSONObject(i).getJSONArray("sellingStatus").getJSONObject(0).getJSONArray("convertedCurrentPrice").getString(0);
                cards.add(new Card(galleryURL,itemTitle,itemCondition,isTopRated,shippingCost,price));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private void getJson(String url) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: got response: " + response);
                        ProgressBar progressBar = findViewById(R.id.progressBar);
                        progressBar.setVisibility(View.GONE);
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
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