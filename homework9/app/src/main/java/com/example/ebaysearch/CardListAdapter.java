package com.example.ebaysearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends ArrayAdapter<DualCard> {
    private static final String TAG = "CardListAdapter";

    private Context mContext;
    int mResource;

    public CardListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DualCard> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the card information
        String imgURL = getItem(position).card1.getItemImgURL();
        String itemTitle = getItem(position).card1.getItemTitle();
        String itemCondition = getItem(position).card1.getItemCondition();
        String isTopRated = getItem(position).card1.getIsTopRated();
        String shippingCost = getItem(position).card1.getShippingCost();
        String price = getItem(position).card1.getPrice();

        //Create the card object with the information
        // todo

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView imageView1 = convertView.findViewById(R.id.productImg1);
        Picasso.get().load(imgURL).into(imageView1);


        return convertView;

    }
}
