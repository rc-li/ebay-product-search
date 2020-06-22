package com.example.ebaysearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Cards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
//        getActionBar().setTitle("Hello world App");
//        getSupportActionBar().setTitle("Hello world App");  // provide compatibility to all the versions
    }
}