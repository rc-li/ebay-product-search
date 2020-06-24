package com.example.ebaysearch;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
    private String itemImgURL;
    private String itemTitle;
    private String itemCondition;
    private String isTopRated;
    private String shippingCost;
    private String price;
    private String itemID;
    public boolean isEmpty;

    public Card(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public Card() { }

    public Card(String itemImgURL, String itemTitle, String itemCondition, String isTopRated, String shippingCost, String price, String itemID) {
        this.itemImgURL = itemImgURL;
        this.itemTitle = itemTitle;
        this.itemCondition = itemCondition;
        this.isTopRated = isTopRated;
        this.shippingCost = shippingCost;
        this.price = price;
        this.itemID = itemID;
    }

    protected Card(Parcel in) {
        itemImgURL = in.readString();
        itemTitle = in.readString();
        itemCondition = in.readString();
        isTopRated = in.readString();
        shippingCost = in.readString();
        price = in.readString();
        itemID = in.readString();
        isEmpty = in.readByte() != 0;
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemImgURL() {
        return itemImgURL;
    }

    public void setItemImgURL(String itemImgURL) {
        this.itemImgURL = itemImgURL;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getIsTopRated() {
        return isTopRated;
    }

    public void setIsTopRated(String isTopRated) {
        this.isTopRated = isTopRated;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemImgURL);
        dest.writeString(itemTitle);
        dest.writeString(itemCondition);
        dest.writeString(isTopRated);
        dest.writeString(shippingCost);
        dest.writeString(price);
        dest.writeString(itemID);
        dest.writeByte((byte) (isEmpty ? 1 : 0));
    }
}
