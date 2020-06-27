package com.example.ebaysearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String TAG = "user MainActivity";
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.optionBar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String makeURL() {
        String url = "http://10.0.2.2:3000/q?";
//        String url = "http://ebay-8.wl.r.appspot.com/q?";

        EditText keyEdit = findViewById(R.id.keywordEdit);
        String keyword = keyEdit.getText().toString();
        this.keyword = keyword;

        EditText lowPriceEdit = findViewById(R.id.lowPrice);
        String lowPriceStr = lowPriceEdit.getText().toString();
        double lowPrice;
        try {
            lowPrice = Double.parseDouble(lowPriceStr);
        }
        catch (Exception e) {
            lowPrice = 0.0;
        }

        EditText highPriceEdit = findViewById(R.id.highPrice);
        String highPriceStr = highPriceEdit.getText().toString();
        double highPrice;
        try {
            highPrice = Double.parseDouble(highPriceStr);
        }
        catch (Exception e) {
            highPrice = Double.MAX_VALUE;
        }

        CheckBox isNew = findViewById(R.id.isNew);
        CheckBox isUsed = findViewById(R.id.isUsed);
        CheckBox isUnspecified = findViewById(R.id.isUnspecified);

        Spinner optionBar = findViewById(R.id.optionBar);
        String option = optionBar.getSelectedItem().toString();

        url += "keyword=" + keyword;
        url += "&lowPrice=" + lowPrice;
        url += "&highPrice=" + highPrice;
        if (isNew.isChecked())
            url += "&isNew=on";
        if (isUsed.isChecked())
            url += "&isUsed=on";
        if (isUnspecified.isChecked())
            url += "&isUnspecified=on";
        url+= "&Sort+by%3A+=" + option;

//        url = "https://ebay-8.wl.r.appspot.com/q?" + encodeValue(url);
//        url = "http://localhost:3000/q?" + encodeValue(url);
//        url = "http://10.0.2.2:3000/q?" + encodeValue(url);


        Log.d(TAG, "makeURL: the url is: " + url);

        return url;
    }

//    private static String encodeValue(String value) {
//        try {
//            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
//        } catch (UnsupportedEncodingException ex) {
//            throw new RuntimeException(ex.getCause());
////        }
//    }

    public void searchClicked(View view) {
        if (valueCheck()) {
            String url = makeURL();
            Intent intent = new Intent(this, CardsActivity.class);
            intent.putExtra("url",url);
            intent.putExtra("keyword", this.keyword);
            startActivity(intent);
        }

    }

    public boolean valueCheck() {
        boolean passValidation = true;

        EditText keyEdit = findViewById(R.id.keywordEdit);
        String keyword = keyEdit.getText().toString();
        TextView emptyKeyWarning = findViewById(R.id.emptyKeyWarning);
        if (keyword.equals("")) {
            emptyKeyWarning.setVisibility(View.VISIBLE);
            passValidation = false;
        }
        else {
            emptyKeyWarning.setVisibility(View.GONE);
        }

        EditText lowPriceEdit = findViewById(R.id.lowPrice);
        String lowPriceStr = lowPriceEdit.getText().toString();
        double lowPrice;
        try {
            lowPrice = Double.parseDouble(lowPriceStr);
        }
        catch (Exception e) {
            lowPrice = 0.0;
        }
        EditText highPriceEdit = findViewById(R.id.highPrice);
        String highPriceStr = highPriceEdit.getText().toString();
        double highPrice;
        try {
            highPrice = Double.parseDouble(highPriceStr);
        }
        catch (Exception e) {
            highPrice = Double.MAX_VALUE;
        }
        Log.d(TAG, "searchClicked: " + "lowPrice is " + lowPrice + "1.23 and highPrice is " + highPrice);
        TextView priceWarning = findViewById(R.id.priceRangeWarning);
        if (highPrice < lowPrice) {
            priceWarning.setVisibility(View.VISIBLE);
            passValidation = false;
        }
        else {
            priceWarning.setVisibility(View.GONE);
        }

        if (!passValidation) {
            Toast.makeText(this, "Please fix all fields with errors", Toast.LENGTH_SHORT).show();
        }

        return passValidation;
    }

    public void clearClicked(View view) {
        EditText keyword = findViewById(R.id.keywordEdit);
        keyword.setText("");
        EditText lowPrice = findViewById(R.id.lowPrice);
        lowPrice.setText("");
        EditText highPrice = findViewById(R.id.highPrice);
        highPrice.setText("");

        CheckBox isNew = findViewById(R.id.isNew);
        isNew.setChecked(false);
        CheckBox isUsed = findViewById(R.id.isUsed);
        isUsed.setChecked(false);
        CheckBox isUnspecified = findViewById(R.id.isUnspecified);
        isUnspecified.setChecked(false);

        Spinner spinner = findViewById(R.id.optionBar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView keyWordWarning = findViewById(R.id.emptyKeyWarning);
        keyWordWarning.setVisibility(View.GONE);
        TextView priceRangeWarning = findViewById(R.id.priceRangeWarning);
        priceRangeWarning.setVisibility(View.GONE);
    }
}