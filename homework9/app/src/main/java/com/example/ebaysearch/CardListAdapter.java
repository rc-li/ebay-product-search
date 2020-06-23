package com.example.ebaysearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends ArrayAdapter<Card> {
    private static final String TAG = "CardListAdapter";

    private Context mContext;
    int mResource;

    public CardListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Card> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the card information
        // todo

        //Create the card object with the information
        // todo

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        return convertView;

    }
}
