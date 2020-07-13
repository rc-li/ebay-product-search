package com.example.ebaysearch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private RequestQueue mQueue;
    private ActionBar actionBar;
    public JSONObject data;
    private String cardID;
    private Card card;

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TabItem productTab;
    TabItem sellerInfoTab;
    TabItem shippingTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabLayout);
        productTab = findViewById(R.id.productTab);
        sellerInfoTab = findViewById(R.id.sellerInfoTab);
        shippingTab = findViewById(R.id.shippingTab);
        viewPager = findViewById(R.id.viewPager);

        pagerAdapter = new PageAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        String cardID;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                cardID = null;
            } else {
                cardID = extras.getString("cardID");
            }
        } else {
            cardID = (String) savedInstanceState.getSerializable("cardID");
        }
        Log.d(TAG, "onCreate: the cardID received is: " + cardID);

        Intent i = getIntent();
        Card card = (Card) i.getSerializableExtra("card");
        this.card = card;
        this.cardID = cardID;
        actionBar.setTitle(card.getItemTitle());

        mQueue = Volley.newRequestQueue(this);
    }

    public Card getCard() {
        return card;
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        if (id == R.id.mybutton) {
            Intent browserIntent = null;
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(card.getViewItemURL()));
            startActivity(browserIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    //todo call it on server side
    public String makeURL() {
        String url = "";
//        url += "http://10.0.2.2:3000/single-q?cardID=" + cardID;
        url += "http://ebay-8.wl.r.appspot.com/single-q?cardID=" + cardID;
        return url;
    }

}