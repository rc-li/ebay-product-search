package com.example.ebaysearch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String cardID;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                cardID = null;
            } else {
                cardID = extras.getString("cardID");
            }
        } else {
            cardID = (String) savedInstanceState.getSerializable("url");
        }
        Log.d(TAG, "onCreate: the cardID received is: " + cardID);

        String url = makeURL(cardID);
        mQueue = Volley.newRequestQueue(this);
        getJson(url);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    private String makeURL(String cardID) {
        String url = "";
        url += "http://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=JSON" +
                "&appid=RayLi-exp-PRD-d2eb6beb6-1a4f60ed&siteid=0&version=967" +
                "&ItemID=" + cardID +
                "&IncludeSelector=Description,Details,ItemSpeciGics";
        return url;
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

    private ArrayList<Card> parseJSON(JSONObject myJSON) {
        ArrayList<Card> cards = new ArrayList<Card>();
        Log.d(TAG, "onCreate: Started making tabs");
        Log.d(TAG, "parseJSON: JSON data: \n" + myJSON);
//        ListView mListView = findViewById(R.id.listView);
//        try {
//            JSONArray items = myJSON.getJSONObject("searchResult").getJSONArray("item");
//            for (int i = 0; i < items.length(); i++) {
//                String galleryURL = items.getJSONObject(i).getJSONArray("galleryURL").getString(0);
//                String itemTitle = items.getJSONObject(i).getJSONArray("title").getString(0);
//                String itemCondition = items.getJSONObject(i).getJSONArray("condition").getJSONObject(0).getJSONArray("conditionDisplayName").getString(0);
//                String isTopRated = items.getJSONObject(i).getJSONArray("topRatedListing").getString(0);
//                String shippingCost = items.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0).getJSONArray("shippingServiceCost").getJSONObject(0).getString("__value__");
//                String price = items.getJSONObject(i).getJSONArray("sellingStatus").getJSONObject(0).getJSONArray("convertedCurrentPrice").getJSONObject(0).getString("__value__");
//                String itemID = items.getJSONObject(i).getJSONArray("itemId").getString(0);
//                Log.d(TAG, "parseJSON: itemID is " + itemID);
//                cards.add(new Card(galleryURL,itemTitle,itemCondition,isTopRated,shippingCost,price, itemID));
////                setCards(cards);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return cards;
    }
}