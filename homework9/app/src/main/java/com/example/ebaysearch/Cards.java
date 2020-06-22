package com.example.ebaysearch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class Cards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
//        getActionBar().setTitle("Hello world App");
//        getSupportActionBar().setTitle("Hello world App");  // provide compatibility to all the versions

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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