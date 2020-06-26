package com.example.ebaysearch;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the card 1 information
        String imgURL1 = getItem(position).card1.getItemImgURL();
        String itemTitle1 = getItem(position).card1.getItemTitle();
        String itemCondition1 = getItem(position).card1.getItemCondition();
        String isTopRated1 = getItem(position).card1.getIsTopRated();
        String shippingCost1 = getItem(position).card1.getShippingCost();
        String price1 = getItem(position).card1.getPrice();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView imageView1 = convertView.findViewById(R.id.productImgView1);
        if (!imgURL1.equals("https://thumbs1.ebaystatic.com/pict/04040_0.jpg")) {
            Picasso.get().load(imgURL1).into(imageView1);
        }
        else {
            imageView1.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ebay_default));
        }
        TextView title1 = convertView.findViewById(R.id.productTitleView1);
        title1.setText(itemTitle1);
        TextView shipCost1 = convertView.findViewById(R.id.shipCostView1);
        if (isTopRated1.equals("false"))
            shipCost1.setText("Ships for $" + shippingCost1);
        else
            shipCost1.setText("Ships for $" + shippingCost1 + "/nTop Rated Listing");
        TextView priceView1 = convertView.findViewById(R.id.priceView1);
        priceView1.setText(price1);
        TextView conditionView1 = convertView.findViewById(R.id.conditionView1);
        conditionView1.setText(itemCondition1);

        // setting card 2
        if (getItem(position).card2.isEmpty) {
            CardView cardView = convertView.findViewById(R.id.card2);
            cardView.setVisibility(View.GONE);
        }
        else {
            String imgURL2 = getItem(position).card2.getItemImgURL();
            String itemTitle2 = getItem(position).card2.getItemTitle();
            String itemCondition2 = getItem(position).card2.getItemCondition();
            String isTopRated2 = getItem(position).card2.getIsTopRated();
            String shippingCost2 = getItem(position).card2.getShippingCost();
            String price2 = getItem(position).card2.getPrice();

            ImageView imageView2 = convertView.findViewById(R.id.productImgView2);
            if (!imgURL2.equals("https://thumbs1.ebaystatic.com/pict/04040_0.jpg")) {
                Picasso.get().load(imgURL2).into(imageView2);
            }
            else {
                imageView2.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ebay_default));
            }
            TextView title2 = convertView.findViewById(R.id.productTitleView2);
            title2.setText(itemTitle2);
            TextView shipCost2 = convertView.findViewById(R.id.shipCostView2);
            if (isTopRated2.equals("false"))
                shipCost2.setText("Ships for $" + shippingCost2);
            else
                shipCost2.setText("Ships for $" + shippingCost2 + "/nTop Rated Listing");
            TextView priceView2 = convertView.findViewById(R.id.priceView2);
            priceView2.setText(price2);
            TextView conditionView2 = convertView.findViewById(R.id.conditionView2);
            conditionView2.setText(itemCondition2);
        }

        final CardView card1 = convertView.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("cardID", getItem(position).card1.getItemID());
                intent.putExtra("card", getItem(position).card1);
                mContext.startActivity(intent);
            }
        });

        final CardView card2 = convertView.findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("cardID", getItem(position).card2.getItemID());
                intent.putExtra("card", getItem(position).card2);
                mContext.startActivity(intent);
            }
        });

        return convertView;

    }
}
